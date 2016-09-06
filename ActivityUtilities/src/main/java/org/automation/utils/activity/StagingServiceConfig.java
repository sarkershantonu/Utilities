package org.automation.utils.activity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StagingServiceConfig {

    @Bean
    public StagingService stagingService() {
        return new StagingServiceImpl();
    }

}
