
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackageClasses = ScanConfiguration.class)
public class Config {

    @Configuration
    @PropertySources({
            @PropertySource("./env/${" + ConfigConstants.ENVIRONMENT_SERVER + "}.properties"),
    })
    static class PropertyConfiguration {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }
}
