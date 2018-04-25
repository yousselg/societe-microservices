package com.home.societeproxyservice.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulLogginFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(ZuulLogginFilter.class);

	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("Request", request);
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		// priority
		return 1;
	}

	@Override
	public String filterType() {
		// type may be : pre post error
		return null;
	}

}
