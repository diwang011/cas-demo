package com.next.cas.security;

import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.execution.RequestContext;


public class LoginSuccessHandleAction {

	
	public final String generate(final RequestContext context) {
		
		Integer errorTimes =  0;
		WebUtils.getHttpServletRequest(context).getSession().setAttribute("errorTimes", errorTimes);
		return "sendTicketGrantingTicket";
    }
}
