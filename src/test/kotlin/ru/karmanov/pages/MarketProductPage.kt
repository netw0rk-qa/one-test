package ru.karmanov.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class MarketProductPage(
    var driver: WebDriver
) {
    init {
        PageFactory.initElements(driver, this)
    }
    // Строка цены товара из его карточки
    var price:String = ""

    // Кнопка Добавить в корзину
    @FindBy(xpath = "//div[@data-zone-name=\"cartButton\"]/button")
    private val addToCart: WebElement? = null

    // Тэг с ценой товара
    @FindBy(css = "div[data-zone-name=default-offer] > div > div > div > span[data-autotest-currency]")
    private val priceGood: WebElement? = null

    // Кнопка "Перейти в корзину" в модульном окне. Появляется после клика по Добавить в корзину из карточки товара
    @FindBy(xpath = "//div[text()=\"Товар успешно добавлен в корзину\"]/../div/a/span[contains(text(),'корзину')]/..")
    private val linkToCart: WebElement? = null

    // Клик по кнопке добавления товара в корзину сразу как она отображается
    fun addToCartBtn (){
        val wait = WebDriverWait(driver,5)
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[data-zone-name=cartButton] > button")
            )
        )
        addToCart!!.click()
    }

    // Сумма товара с пробелом и символом валюты
    fun getPrice(){
        this.price = priceGood!!.text
    }

    // Клик по кнопке перехода в корзину сразу как она отображается в модальном окне и ожидание её загрузки
    fun gotoCart(){
        val wait = WebDriverWait(driver,10)
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()=\"Товар успешно добавлен в корзину\"]/../div/a/span[contains(text(),'корзину')]/..")
            )
        )
        linkToCart!!.click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@data-apiary-widget-name=\"@marketplace/CartTotalPrice\"]")
        ))
    }
}