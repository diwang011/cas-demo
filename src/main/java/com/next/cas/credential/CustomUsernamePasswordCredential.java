package com.next.cas.credential;

import org.jasig.cas.authentication.UsernamePasswordCredential;

public class CustomUsernamePasswordCredential extends UsernamePasswordCredential
{
    /**
     * 
     */
    private static final long serialVersionUID = -8397996732981246042L;

    private String captcha;

    public String getCaptcha()
    {
        return captcha;
    }

    public void setCaptcha(String captcha)
    {
        this.captcha = captcha;
    }

}
