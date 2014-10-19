package dk.fitfit.campusfood.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:persistence.properties")
@EnableJpaRepositories(basePackages = {"dk.fitfit.campusfood.model", "dk.fitfit.campusfood.repository"})
public class PersistenceConfig {
	private static final Logger logger = LoggerFactory.getLogger(PersistenceConfig.class);
	
	@Autowired
	private Environment env;

	private final String packagesToScan = "dk.fitfit.campusfood.*";

	@Value("${jdbc.driverClassName}")
	private String snot;

	public PersistenceConfig() {
		logger.info("Persistence config loaded!");
		logger.info("psnot: {}", snot);
		logger.info("penv: {}", env);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();
		env = context.getEnvironment();

		logger.info("penv: {}", env);
		logger.info("penv.snot: {}", env.getProperty("jdbc.host"));
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan(packagesToScan);
		factory.setJpaVendorAdapter(jpaVendorAdapter());
//		factory.setJpaProperties(additionalProperties());
		return factory;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.H2);
		return hibernateJpaVendorAdapter;
	}

	// Ref.: http://www.baeldung.com/2011/12/26/transaction-configuration-with-jpa-and-spring-3-1/
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
