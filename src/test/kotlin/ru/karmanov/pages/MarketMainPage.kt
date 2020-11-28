package ru.karmanov.pages

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class MarketMainPage (
    var driver: WebDriver
) {
    init {
        PageFactory.initElements(driver, this)
    }
    // Поле ввода для поиска по Я.Маркету
    @FindBy(xpath = "//input[@id=\"header-search\"]")
    private val searchField: WebElement? = null

    // Кнопка найти
    @FindBy(xpath = "//input[@name=\"text\"]/../../../../div/button[@type=\"submit\"]")
    private val searchBtn: WebElement? = null

    // Заполнение поля поиска
    fun inputSearch(searchText: String) {
        searchField!!.sendKeys(searchText)
    }

    fun clickSearchBtn() {
        searchBtn!!.click()
    }
}