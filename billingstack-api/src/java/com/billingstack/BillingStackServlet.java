package com.billingstack;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.groovy.grails.web.servlet.GrailsDispatcherServlet;

public class BillingStackServlet extends GrailsDispatcherServlet {
	
	/**
	 * Override the parent class implementation in order to intercept PATCH
	 * requests.
	 */
	
	private static final String PATCH = "PATCH";
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if (method.equalsIgnoreCase(BillingStackServlet.PATCH)) {
			super.processRequest(request, response);
		}
		else {
			super.service(request, response);
		}
		//throw new IOException(request.getMethod());
	}

}