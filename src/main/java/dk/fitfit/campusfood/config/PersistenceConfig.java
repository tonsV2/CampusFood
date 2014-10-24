package dk.fitfit.campusfood.config;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@PropertySource("classpath:persistence-${envTarget:default}.properties")
@EnableJpaRepositories(basePackages = {"dk.fitfit.campusfood.model", "dk.fitfit.campusfood.repository"})
public class PersistenceConfig {
	private static final Logger logger = LoggerFactory.getLogger(PersistenceConfig.class);

//	@Autowired
//	private DataInitializer dataInitializer;

	@Autowired
	private Environment env;

	private final String packagesToScan = "dk.fitfit.campusfood";


	public PersistenceConfig() throws ClassNotFoundException {
		logger.info("PersistenceConfig loaded!");
		Class.forName("org.postgresql.Driver");
	}

	@PostConstruct
	public void postConstructor()
	{
		logger.info("env.getActiveProfiles(): {}", env.getActiveProfiles());
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

//	@Bean
//	public DataSource dataSource() {
//		logger.info(">>>>>>>>>>>>>>>>>>>>>>PersistenceConfig.dataSource()");
//		return new EmbeddedDatabaseBuilder()
//			.setType(EmbeddedDatabaseType.H2)
//			.build();
//	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan(packagesToScan);
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		return factory;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabasePlatform(env.getRequiredProperty("jdbc.hibernate.dialect"));
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Profile("!openshift")
	@Configuration 
	public static class DefaultPersistenceConfig {
		@Bean
		public DataSource dataSource() {
			logger.info(">>>>>>>>>>>>>>>>>>>>>>DefaultPersistenceConfig.dataSource()");
			return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.build();
		}
	}

	// Activate this profile on openshift by running: rhc set-env JAVA_OPTS_EXT=-Dspring.profiles.active=openshift -a campusfood
	@Configuration
	@Profile("openshift")
	public static class OpenShiftPersistenceConfig {
		private static final Logger logger = LoggerFactory.getLogger(OpenShiftPersistenceConfig.class);

		private String driverClassName = "org.postgresql.Driver";
		private String database = "campusfood";
		private String url = System.getenv("OPENSHIFT_POSTGRESQL_DB_URL") + "/" + database;
//		private String url = env.getRequiredProperty("OPENSHIFT_POSTGRESQL_DB_URL") + "/" + database;

		@PostConstruct
		public void postConstructor()
		{
			logger.info("OpenShiftPersistenceConfig loaded!");
		}

		@Bean
		public DataSource dataSource()
		{
			logger.info(">>>>>>>>>>>>>>>>>>>>>>OpenShiftPersistenceConfig.dataSource()");
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driverClassName);
			dataSource.setUrl(url);
			return dataSource;
		}
	}
}
