
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;

public class DbUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbUtils.class);
    private static volatile DbUtils dbUtils;

    private String deactivateLatestCategoriesQuerry = "update attrib_vector_category set status_id = 37 WHERE " +
            "(attrib_type_id='2' or attrib_type_id='1') and status_id=36  and create_time > (CURRENT_TIMESTAMP - interval '30' day)";
    private String deactivateCategoryByName = "update attrib_vector_category set status_id = 37 WHERE " +
            "attrib_vector_cat_name = '%s'";

   private static final String QUERY_REJECT_PENDING_MO_BU = "update bulk_approval_history\n" +
           "set status_id=339, approval_date=current_date, approved_by='auto-tests', approver_comments='rejected_automatically'\n" +
           "where status_id=337\n" +
           "and entity_id='BLKUPLMO'";

    private static final String QUERY_REJECT_PENDING_THOR_ISIN_LU = "update lookup_request\n" +
            "set status_code='REJECT', approved_user='auto test', approved_time = current_date, approver_comments='rejected automatically'\n" +
            "where lookup_id=62\n" +
            "and status_code='SFAPST'" ;

    private ApplicationContext ctx;

    public DbUtils() {
        ctx = new ClassPathXmlApplicationContext("config/jdbc/datasource-context.xml");
    }

    public static DbUtils getInstance() {
        DbUtils localInstance = dbUtils;
        if (localInstance == null){
            synchronized (DbUtils.class){
                localInstance = dbUtils;
                if (localInstance == null){
                    dbUtils = localInstance = new DbUtils();
                }
            }
        }
        LOGGER.info(Config.ENV);
        return localInstance;
    }

    public JdbcTemplate getTemplate() {
        DataSource dataSource = ctx.getBean("oracleDataSource", DataSource.class);
        return new JdbcTemplate(dataSource);
    }

    public boolean isPresentExceptionBy(Long refId, Date fromDate) {
        String data = DateTimeUtils.format(fromDate, DateTimeUtils.dd_MM_yyyy);
        StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM AUDIT_ADMIN.EXCEPTION_AUDIT where EXCEPTION_AUDIT.ADDITIONAL_INFO like '%\"Position Id\":\"");
        sb.append(String.valueOf(refId))
                .append("\"%' AND EXCEPTION_STATUS = 'OPENST' AND create_time > to_timestamp('")
                .append(data)
                .append("', 'dd-mm-yyyy')");
        int exceptionsQty = getTemplate().queryForObject(sb.toString(), Integer.class);
        return exceptionsQty > 0;
    }

    public void deactivateRedundantCategories() {
        getTemplate().update(deactivateLatestCategoriesQuerry);
    }

    public void deactivateAttributeCategory(String categoryName) {
        getTemplate().update(String.format(deactivateCategoryByName, categoryName));
    }

    public void rejectBuMoViaDB(){getTemplate().update(QUERY_REJECT_PENDING_MO_BU); }
    
    public void rejectPendingThorIsinLuViaDB() {getTemplate().update(QUERY_REJECT_PENDING_THOR_ISIN_LU);}
}
