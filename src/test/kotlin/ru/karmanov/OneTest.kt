package ru.karmanov

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import ru.karmanov.pages.MarketCartPage
import ru.karmanov.pages.MarketMainPage
import ru.karmanov.pages.MarketProductPage
import ru.karmanov.pages.MarketResultPage
import kotlin.test.*

class OneTest {
    private var driver: WebDriver
    private val pageMarket: MarketMainPage
    private val pageResult: MarketResultPage
    private val pageProduct: MarketProductPage
    private val pageCart: MarketCartPage

    init {
        System.setProperty("webdriver.chrome.driver", "/QA/lib/chromedriver87.exe")
        driver = ChromeDriver()
        pageMarket = MarketMainPage (driver)
        pageResult = MarketResultPage (driver)
        pageProduct = MarketProductPage (driver)
        pageCart = MarketCartPage (driver)
    }

    @Test
    fun buyProductAndCheck() {
        driver.get("https://market.yandex.ru/")

        // Поиск по фразе iphone
        pageMarket.inputSearch("iphone")
        pageMarket.clickSearchBtn()

        assertTrue(driver.title.contains("«iphone»"),
            "Переход на страницу с результатами поиска выполнен")

        //Клик по Покупка на Маркете
        pageResult.checkedBuyOnMarket()
        assertTrue(driver.currentUrl.contains("&cpa=1&"), "Отметка 'Покупка на Маркете' поставлена")

        //Переход на страницу третьего продукта, добавление в корзину и переход в корзину
        pageResult.gotoProductPage()
        pageProduct.getPrice()
        pageProduct.addToCartBtn()
        pageProduct.gotoCart()

        //Проверка кол-ва товара и цены
        assertEquals(pageCart.getCountProducts(), "Товары (1)", "Число товаров")
        assertEquals(pageCart.getSumTotal(), pageProduct.price, "Сумма товаров")
    }

    @AfterTest
    fun closeDriver(){
        driver.quit()
    }

}