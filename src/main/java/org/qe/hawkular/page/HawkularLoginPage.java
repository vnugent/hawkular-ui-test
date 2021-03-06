package org.qe.hawkular.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qe.hawkular.element.*;
import org.qe.hawkular.util.HawkularUtils;

public class HawkularLoginPage {
    public final WebDriver driver;

    public HawkularLoginPage(WebDriver driver) {

        this.driver = driver;
    }

    By usernameLocator = HawkularLoginPageConstants.usernameLocator;
    By passwordLocator = HawkularLoginPageConstants.passwordLocator;
    By loginButtonLocator = HawkularLoginPageConstants.loginButtonLocator;
    By registrationLink = HawkularLoginPageConstants.registrationLink;
    By backToLoginLink = HawkularRegistrationPageConstants.backToLoginLink;
    By invalidUsernamePasswordError = HawkularLoginPageConstants.invalidUsernamePasswordError;
    
    public HawkularLoginPage typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);

        return this;
    }

    public void navigateToRegistration(){
        HawkularUtils utils = new HawkularUtils(driver);
         utils.navigateTo(registrationLink);
    }
    
    public void navigateToLogin(){
        HawkularUtils utils = new HawkularUtils(driver);
         utils.navigateTo(backToLoginLink);
    }

    public HawkularLoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);

        return this;
    }

    public HawkularLoginPage submitLogin() {
        driver.findElement(loginButtonLocator).submit();
        return new HawkularLoginPage(driver);
    }

    public HawkularLoginPage submitLoginExpectingFailure() {
        driver.findElement(loginButtonLocator).submit();

        return new HawkularLoginPage(driver);
    }

    public HawkularLoginPage loginAs(String username, String password) {

        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }
    
    public HawkularLoginPage logout(){
        driver.findElement(HawkularLoginPageConstants.logoutDropdown).click();
        driver.findElement(HawkularLoginPageConstants.logoutLink).click();
	HawkularUtils util = new HawkularUtils(driver);
	util.waitForElementPresent(loginButtonLocator);
        return new HawkularLoginPage(driver);
    }
    
    public boolean verifyInvalidUsernameOrPassword(){
        HawkularUtils util = new HawkularUtils(driver);
        return util.waitForElementPresent(invalidUsernamePasswordError);
        
        
    }
}
