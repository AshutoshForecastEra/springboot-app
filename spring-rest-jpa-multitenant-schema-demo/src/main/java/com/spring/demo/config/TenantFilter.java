package com.spring.demo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * The Class TenantFilter.
 */
@Component
@Order(1)
class TenantFilter implements Filter {

	/**
	 * Do filter.
	 *
	 * @param request the request
	 * @param response the response
	 * @param chain the chain
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String tenantName = req.getHeader("X-TenantID"); //Get the X-TenantID from header
		TenantContext.setCurrentTenant(tenantName);
		try {
			chain.doFilter(request, response);
		} finally {
			TenantContext.setCurrentTenant("");
		}
	}
}