package ru.karmanov.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.ArrayList

class MarketResultPage(
    var driver: WebDriver
) {
    init {
        PageFactory.initElements(driver, this)
    }

    // Опция "Покупка на Маркете" в блоке фильтров справа
    @FindBy(xpath = "//button/fieldset/legend[text()=\"Покупка на Маркете\"]/../..")
    private val filterBuyOnMarket: WebElement? = null

    // Ссылка на карточку третьего товара в результатах поиска
    @FindBy(xpath = "//div[@data-apiary-widget-name=\"@MarketNode/SearchResults\"]/div/div/div/article[3]/div/div/h3[@data-zone-name=\"title\"]/a")
    private val linkProductCard: WebElement? = null

    // Кликаем на опцию "Покупка на Маркете" и ждем до 10 сек обновление результатов
    fun checkedBuyOnMarket (){
        filterBuyOnMarket!!.click()
        val wait:WebDriverWait? = WebDriverWait (driver, 10)
        wait?.until(ExpectedConditions.visibilityOfElementLocated(
            By.ByCssSelector("div.cia-vs[data-zone-name=SearchResults] > div > div:not([data-zone-name=snippetList])")))
        wait?.until(ExpectedConditions.invisibilityOfElementLocated(
            By.ByCssSelector("div.cia-vs[data-zone-name=SearchResults] > div > div:not([data-zone-name=snippetList])")))
    }

    // Кликаем на ссылку товара для перехода в карточку и переключаемся на работу с новой вкладкой. Ожидаем отобраение кнопки добавить в корзину
    fun gotoProductPage(){
        linkProductCard!!.click()
        val tabs = ArrayList(driver.windowHandles)
        driver.switchTo().window(tabs[1])
        val wait:WebDriverWait? = WebDriverWait (driver, 20)
        wait?.until(ExpectedConditions.visibilityOfElementLocated(By.ByCssSelector("div[data-zone-name=cartButton] > button")))
    }
}