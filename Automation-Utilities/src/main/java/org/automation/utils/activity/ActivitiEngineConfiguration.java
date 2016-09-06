

import java.io.IOException;

import javax.sql.DataSource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringJobExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class ActivitiEngineConfiguration {

    public static final String BM_TASK_EXECUTOR = "bmTaskExecutor";

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws IOException {

        final SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setTransactionManager(transactionManager);
        processEngineConfiguration.setDataSource(dataSource).setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
                .setJdbcPingEnabled(true).setJdbcPingQuery("select 0 from dual").setJobExecutorActivate(true);

        return processEngineConfiguration;
    }

    @Bean
    public ProcessEngineFactoryBean processEngineFactoryBean(SpringProcessEngineConfiguration processEngineConfiguration,
            @Qualifier(BM_TASK_EXECUTOR) TaskExecutor taskExecutor) throws Exception {

        final ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration);
        SpringJobExecutor jobExecutor = new SpringJobExecutor();
        jobExecutor.setTaskExecutor(taskExecutor);
        processEngineConfiguration.setJobExecutor(jobExecutor);

        return processEngineFactoryBean;
    }

    @Bean
    public ProcessEngine processEngine(ProcessEngineFactoryBean processEngineFactoryBean) throws Exception {
        return processEngineFactoryBean.getObject();
    }

    @Bean
    public WF wf(ProcessEngine processEngine) {
        return new ActivitiWF(processEngine);
    }

    @Bean(name = "syncWfService")
    public WFService wfService(WF wf) {
        return new SyncWFService(wf);
    }

    @Bean(name = "wfService")
    public WFService errorWfService(ActionRepository repository, @Qualifier("syncWfService") WFService wfService) {
        return new ErrorWFService(repository, wfService);
    }

    @Bean(name = BM_TASK_EXECUTOR)
    public TaskExecutor getThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(20);
        return taskExecutor;
    }

}
