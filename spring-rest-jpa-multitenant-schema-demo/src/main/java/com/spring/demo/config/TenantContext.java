package com.spring.demo.config;

/**
 * The Class TenantContext.
 */
public class TenantContext {

    /** The Constant CURRENT_TENANT. */
    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    /**
     * Gets the current tenant.
     *
     * @return the current tenant
     */
    public static String getCurrentTenant() {
        return CURRENT_TENANT.get();
    }

    /**
     * Sets the current tenant.
     *
     * @param tenant the new current tenant
     */
    public static void setCurrentTenant(String tenant) {
        CURRENT_TENANT.set(tenant);
    }
}