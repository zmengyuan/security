package com.imooc.security.core.properties;

public class BrowserProperties {

    /**登陆页*/
    private String loginPage = "/imooc-signIn.html";

    /**登录数据类型*/
    private LoginType loginType = LoginType.JSON;

    /**记住我有效时间*/
    private int rememberMeSeconds = 3600;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
