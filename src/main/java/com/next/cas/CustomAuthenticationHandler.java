package com.next.cas;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;

public class CustomAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler
{

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential transformedCredential)
            throws GeneralSecurityException, PreventedException
    {
        final String username = transformedCredential.getUsername();
        final String password = transformedCredential.getPassword();
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("username", username);
        return createHandlerResult(transformedCredential, this.principalFactory.createPrincipal(username, attributes), null);
    }

}
