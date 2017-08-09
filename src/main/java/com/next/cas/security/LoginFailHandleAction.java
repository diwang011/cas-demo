package com.next.cas.security;

import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.execution.RequestContext;


public class LoginFailHandleAction {
	
	
	public final String generate(final RequestContext context) {
		
		Integer errorTimes = (Integer) WebUtils.getHttpServletRequest(context).getSession().getAttribute("errorTimes");
		if(errorTimes == null){
			errorTimes = 0;
		}
		errorTimes ++;
		WebUtils.getHttpServletRequest(context).getSession().setAttribute("errorTimes", errorTimes);

		return "handleAuthenticationFailure";
    }
}
