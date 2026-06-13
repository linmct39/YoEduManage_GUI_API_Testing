package pageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class managementEduDashboard {
    WebDriver driver;

    By iconMenu = By.xpath("//*[@data-testid='MenuIcon']");
    public managementEduDashboard (WebDriver driver){
        if(System.getProperty("osName").trim().toLowerCase().contains("windows")){
           this.driver=driver;
        }
    }

    public void funcOpenSidebarMenu(){
        try{
            if(System.getProperty("osName").trim().toLowerCase().contains("windows")){
                driver.findElement(iconMenu).click();

                Thread.sleep(500);
            }
        }catch(Exception e){
            Assert.fail("Loi mo sidebar"+ e.getMessage());
        }
    }

    public void funcClickMenuItem (String strMenuName ){
        try{
            if(System.getProperty("osName").trim().toLowerCase().contains("windows")){
                By btnMenuItem = By.xpath("//p[text()='" + strMenuName + "'] | //div[text()='" + strMenuName + "']");
                driver.findElement(btnMenuItem).click();
                Thread.sleep(Integer.parseInt(System.getProperty("timeSleep").trim()));
            }
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

}
