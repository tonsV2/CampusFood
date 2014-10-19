package dk.fitfit.campusfood.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import dk.fitfit.campusfood.utils.DataInitializer;


@Configuration
@ComponentScan(value = "dk.fitfit.campusfood.*")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

	@Autowired
	private Environment env;

	@Value("${snot:sniger}")
	private String snot;

	@Value("${jdbc.host:sniger}")
	private String snot2;

	public ApplicationConfig() {
		logger.info("Application config loaded!");
		logger.info("snot: {}", snot);
		logger.info("snot2: {}", snot2);

		// Ref.: http://stackoverflow.com/a/5880221/672009
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(DataInitializer.class);
		context.refresh();
		DataInitializer dataInitializer = context.getBean(DataInitializer.class);
		dataInitializer.initialize();

		// Ref.: http://stackoverflow.com/a/19423123/672009
		env = context.getEnvironment();

		logger.info("env: {}", env);
		logger.info("env.snot: {}", env.getProperty("jdbc.host"));
	}

//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}

}
