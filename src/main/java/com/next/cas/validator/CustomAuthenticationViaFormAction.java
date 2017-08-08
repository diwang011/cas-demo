package com.next.cas.validator;

import org.jasig.cas.web.flow.AuthenticationViaFormAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.authentication.Credential;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.google.code.kaptcha.Constants;
import com.next.cas.constans.CustomConstants;
import com.next.cas.credential.CustomUsernamePasswordCredential;

public class CustomAuthenticationViaFormAction extends AuthenticationViaFormAction
{

    public final Event customValidator(RequestContext context, Credential credential, MessageContext messageContext)
    {

        HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        HttpSession session = request.getSession();

        Object loginObject = session.getAttribute(CustomConstants.LOGIN_FIRST);  
        if(loginObject == null || CustomConstants.FIRST.equals(String.valueOf(loginObject))){  
            session.setAttribute(CustomConstants.LOGIN_FIRST, CustomConstants.NOTFIRST);  
            return new Event(this, SUCCESS);  
        } 
        
        String captcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        CustomUsernamePasswordCredential cuCredential = (CustomUsernamePasswordCredential) credential;
        String submitCaptcha = cuCredential.getCaptcha();

        if (!StringUtils.hasText(submitCaptcha))
        {
            messageContext.addMessage(new MessageBuilder().error().code("login.required.captcha").build());
            return new Event(this, ERROR);
        }
        if (submitCaptcha.equals(captcha))
        {
            return new Event(this, SUCCESS);
        }
        messageContext.addMessage(new MessageBuilder().error().code("login.captcha.error").build());

        return new Event(this, ERROR);
    }

}
