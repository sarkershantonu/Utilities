package org.automation.utils.activity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkflowToolsConfiguration {
    @Bean
    public EnumHelper enumHelper() {
        return new EnumHelper();
    }

    @Bean
    public Thrower thrower() {
        return new Thrower();
    }

    @Bean(name = "nodeService")
    public NodeService nodeService(ProjectDataService projectDataService) {
        return new NodeService(projectDataService);
    }

    @Bean(name = "workflowService")
    public WorkflowService workflowService(WFDataService wfDataService) {
        return new WorkflowService(wfDataService);
    }

    @Bean(name = "groupService")
    public GroupService groupService(WFDataService wfDataService) {
        return new GroupService(wfDataService);
    }

    @Bean(name = "notificationDelegate")
    public NotificationService notificationService() {
        return new NotificationService();
    }

}
