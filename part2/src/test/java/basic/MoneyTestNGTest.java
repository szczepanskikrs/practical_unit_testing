package basic;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MoneyTestNGTest {
    private SoftAssert softlyAssert = new SoftAssert();

    @Test(dataProvider = "moneyProvider",
            dataProviderClass = MoneyDataProvider.class)
    public void shouldConstructMoneyWithCorrectCurrencyTypeAndAmount(MoneyTestNG moneyTestNG, int validAmount, String validCurrency) {
        //when
        softlyAssert.assertEquals(moneyTestNG.getAmount(), validAmount);
        softlyAssert.assertEquals(moneyTestNG.getCurrency(), validCurrency);
        //then
        softlyAssert.assertAll();

    }

    @Test(dataProvider = "incorrectAmountProvider",
            dataProviderClass = MoneyDataProvider.class,
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Amount can't be 0 or less")
    void shouldThrowExceptionWhenIncorrectAmountIsProvidedToConstructor(int invalidAmount, String validCurrency) {
        //when
        new MoneyTestNG(invalidAmount, validCurrency);
        //then exception should be thrown
    }

    @Test(dataProvider = "incorrectCurrencyProvider",
            dataProviderClass = MoneyDataProvider.class,
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Currency can't be null or empty")
    public void shouldTrowExceptionWhenIncorrectCurrencyIsProvidedToConstructor(int validAmount, String invalidCurrency) {
        //when
        new MoneyTestNG(validAmount, invalidCurrency);
        //then exception should be thrown
    }
}