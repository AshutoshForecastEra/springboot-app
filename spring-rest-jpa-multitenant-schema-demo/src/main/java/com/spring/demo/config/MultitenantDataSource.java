package com.spring.demo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * The Class MultitenantDataSource.
 */
public class MultitenantDataSource extends AbstractRoutingDataSource {

    /**
     * Determine current lookup key.
     *
     * @return the string
     */
    @Override
    protected String determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }
}