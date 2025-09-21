package com.sentinel.ui.pages;

import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://adminlte.io/themes/v3/index.html");
    }
}
