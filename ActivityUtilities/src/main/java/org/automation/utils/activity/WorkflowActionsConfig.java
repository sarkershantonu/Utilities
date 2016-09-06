package org.automation.utils.activity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WorkflowActionsConfig {

    @Bean
    public ActionRepository actionRepository(WFDataService wfDataService, WF wf) {
        final ActionRepository actionRepository = new SingleActionRepository(null);
        return actionRepository;
    }
}
