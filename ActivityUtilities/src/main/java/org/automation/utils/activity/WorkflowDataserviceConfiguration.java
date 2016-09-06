package org.automation.utils.activity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkflowDataserviceConfiguration {
    private static final String WF_DAO_DATA_SERVICE = "wfDaoDataService";

    @Bean(name = "bookmasterDataService")
    public ProjectDataService bookmasterDataService() {
        return new ProjectDataService();
    }

    @Bean(name = "wfDataService")
    public WFDataService wfDataService() {
        return new WFDataService();
    }

    @Bean(name = WF_DAO_DATA_SERVICE)
    public DataService getDataService(@Qualifier(DataServiceConfiguration.DB_DATA_SOURCE) DataSource dataSource) throws Exception {
        final DataServiceConfiguration config = new DataServiceConfiguration(); 
        return config.getDataService(dataSource, WfDataServiceEnum.class, "/wf-mappings.xml", "/wf-data-model.xml");
    }

}
