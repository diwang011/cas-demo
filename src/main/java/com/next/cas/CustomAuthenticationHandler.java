package com.next.cas;

import java.security.GeneralSecurityException;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;

import com.alibaba.fastjson.JSON;
import com.next.cas.db.model.User;
import com.next.cas.db.service.UserService;

public class CustomAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler
{
    private Logger logger = LogManager.getLogger(getClass());
    private UserService userService;

    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential transformedCredential)
            throws GeneralSecurityException, PreventedException
    {
        final String username = transformedCredential.getUsername();
        final String password = this.getPasswordEncoder().encode(transformedCredential.getPassword());
        logger.info("login info-> " + username + ":" + password);
        User user = userService.query(username);
        logger.info(JSON.toJSONString(user));
        if (user == null)
        {
            throw new AccountNotFoundException(username + " not found.");
        }
        if (!user.getPassword().equalsIgnoreCase(password))
        {
            throw new FailedLoginException();
        }
        return createHandlerResult(transformedCredential, this.principalFactory.createPrincipal(username), null);
    }

}
