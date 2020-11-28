package ru.karmanov.pages

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class MarketCartPage(
    var driver: WebDriver
) {
    init {
        PageFactory.initElements(driver, this)
    }

    // Текст с количеством товара в правом блоке (в одной строке с суммой товара)
    @FindBy(xpath = "//div[@data-apiary-widget-name=\"@marketplace/CartTotalPrice\"]/div/div[@data-auto=\"total-items\"]/span[@data-auto=\"count\"][text()]")
    private val countProducts: WebElement? = null

    // Текст с суммой товара в правом блоке (в одной строке с количеством товара)
    @FindBy(xpath = "//div[@data-apiary-widget-name=\"@marketplace/CartTotalPrice\"]/div/div[@data-auto=\"total-items\"]/span[@data-auto=\"value\"][text()]")
    private val sumPriceProduct: WebElement? = null

    fun getCountProducts ():String{
        return countProducts!!.text
    }

    fun getSumTotal():String{
        return sumPriceProduct!!.text
    }
}