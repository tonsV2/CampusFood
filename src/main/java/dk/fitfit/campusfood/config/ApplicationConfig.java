package dk.fitfit.campusfood.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;


@Configuration
@ComponentScan(value = "dk.fitfit.campusfood")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

//	@Autowired
//	private DataInitializer dataInitializer;
	
	@Autowired
	private Environment env;

	public ApplicationConfig() {
		logger.info("Application config loaded!");
	}

	@PostConstruct
	public void postConstructor()
	{
//		dataInitializer.initialize();
		logger.info("env.getActiveProfiles(): {}", env.getActiveProfiles());
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
