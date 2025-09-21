package com.sentinel.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ContactsPage extends BasePage {

    @FindBy(css = "input[type='search']")
    private WebElement searchInput;

    @FindBy(css = "table.table tbody tr")
    private List<WebElement> rows;

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://adminlte.io/themes/v3/pages/examples/contacts.html");
    }

    public void search(String text) {
        try {
            searchInput.clear();
            searchInput.sendKeys(text);
            Thread.sleep(800); // wait for search to apply
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isUserPresentInTable(String expectedName) {
        for (WebElement r : rows) {
            if (r.getText().contains(expectedName)) {
                return true;
            }
        }
        return false;
    }

    public String readRow(int index) {
        if (rows.size() > index) return rows.get(index).getText();
        return null;
    }
}
