package com.next.cas.security;

import javax.validation.constraints.NotNull;

import org.jasig.cas.CentralAuthenticationService;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * 缓存票据，用于退出登录时联动退出使用
 * @author log
 *
 */
public class CacheServiceTicketAction extends AbstractAction {

	@NotNull
	private CentralAuthenticationService centralAuthenticationService;

	@Override
	protected Event doExecute(RequestContext context) throws Exception {
		return null;
	}

	public void setCentralAuthenticationService(final CentralAuthenticationService centralAuthenticationService) {
		this.centralAuthenticationService = centralAuthenticationService;
	}

}
