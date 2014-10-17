package dk.fitfit.campusfood.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "dk.fitfit.campusfood.*")
public class ApplicationConfig {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

	public ApplicationConfig() {
		logger.info("Application config loaded!");

//		DataInitializer dataInitializer = new DataInitializer();
//		dataInitializer.initialize();
	}
}
