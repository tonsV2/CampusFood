package dk.fitfit.campusfood.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
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
//@PropertySource("classpath:persistence-${envTarget}.properties")
@PropertySource("classpath:persistence-dev.properties")
@EnableJpaRepositories(basePackages = {"dk.fitfit.campusfood.model", "dk.fitfit.campusfood.repository"})
public class PersistenceConfig {
	private final Logger logger = LoggerFactory.getLogger(PersistenceConfig.class);

//	@Autowired
//	private DataInitializer dataInitializer;

	@Autowired
	private Environment env;

	private final String packagesToScan = "dk.fitfit.campusfood";

	public PersistenceConfig() throws ClassNotFoundException {
		logger.info("PersistenceConfig loaded!");
	}

	@PostConstruct
	public void postConstructor()
	{
		logger.info("env.getActiveProfiles(): {}", env.getActiveProfiles());
//		logger.info("jdbc.hibernate.dialect: {}", env.getRequiredProperty("jdbc.hibernate.dialect"));
//		dataInitializer.initialize();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		// http://stackoverflow.com/a/24251993
		// WARN : org.hibernate.ejb.HibernatePersistence - HHH015016: Encountered a deprecated javax.persistence.spi.PersistenceProvider [org.hibernate.ejb.HibernatePersistence]; use [org.hibernate.jpa.HibernatePersistenceProvider] instead.
		factory.setPersistenceProvider(new HibernatePersistenceProvider());
		factory.setDataSource(dataSource);
		factory.setPackagesToScan(packagesToScan);
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		factory.setJpaProperties(additionalProperties());
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

	@Bean
//	@Profile("!openshift")
//	@Primary
//	@Profile("dev")
	public DataSource dataSourceH2() {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>DefaultPersistenceConfig.dataSourceH2()");
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.build();
	}
/*
	// Activate this profile on openshift by running: rhc set-env JAVA_OPTS_EXT=-Dspring.profiles.active=openshift -a campusfood
	@Bean
//	@Profile("openshift")
	public DataSource dataSourcePostgresql()
	{
		logger.info(">>>>>>>>>>>>>>>>>>>>>>OpenShiftPersistenceConfig.dataSourcePostgre()");

		String driverClassName = "org.postgresql.Driver";
		String database = "campusfood";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String url = System.getenv("OPENSHIFT_POSTGRESQL_DB_URL") + "/" + database;
//		String url = System.getProperty("OPENSHIFT_POSTGRESQL_DB_URL") + "/" + database;
//		logger.info("url: {}", url);
//		logger.info("jdbc.hibernate.dialect from dataSourcePostgre: {}", env.getRequiredProperty("jdbc.hibernate.dialect"));


		String host = "127.0.0.1";
		String port = "5432";
		String url = "jdbc:postgresql://" + host + ":" + port + "/"+ database + "?autoReconnect=true";
		String username = "admindfbd4en";
		String password = "a4HyB-s8VD3Z";

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);

		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}
*/
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		// TODO: move this into properties file as well
//		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}

}
