package com.spring.demo.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * The Class MultitenantConfiguration.
 */
@Configuration
public class MultitenantConfiguration {
	
	/** The default tenant schema. This tenant value will be set as default if X-TenantID is not provided in header */
	@Value("${defaultTenantSchema}")
	private String defaultTenantSchema;

	/**
	 * Data source.
	 *
	 * @return the data source
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	@ConfigurationProperties(prefix = "web")
	public DataSource dataSource() {
		File[] files = Paths.get("tenant-configs").toFile().listFiles();
		Map<Object, Object> resolvedDataSources = new HashMap<>();

		for (File propertyFile : files) {
			Properties tenantProperties = new Properties();
			DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
			try (FileInputStream fileInStream = new FileInputStream(propertyFile)){
				tenantProperties.load(fileInStream);
				String tenantId = tenantProperties.getProperty("tenantName");
				dataSourceBuilder.driverClassName(tenantProperties.getProperty("tenant.datasource.driver-class-name"));
				dataSourceBuilder.username(tenantProperties.getProperty("tenant.datasource.username"));
				dataSourceBuilder.password(tenantProperties.getProperty("tenant.datasource.password"));
				dataSourceBuilder.url(tenantProperties.getProperty("tenant.datasource.url"));
				resolvedDataSources.put(tenantId, dataSourceBuilder.build());
			} catch (IOException exp) {
				throw new RuntimeException("Error occurred while setting up datasource due to: " + exp.getMessage(), exp);
			}
		}

		AbstractRoutingDataSource dataSource = new MultitenantDataSource();
		dataSource.setDefaultTargetDataSource(resolvedDataSources.get(defaultTenantSchema));
		dataSource.setTargetDataSources(resolvedDataSources); //Based on X-TenantID
		dataSource.afterPropertiesSet();
		return dataSource;
	}
}
