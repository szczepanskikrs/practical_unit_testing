package basic;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyJunit5Test {
    private SoftAssertions softlyAssert = new SoftAssertions();

    @DisplayName("Should construct money with correct currency and amount")
    @ParameterizedTest
    @CsvSource({"1, USD", "100,EUR", "450, CHF", "33, PLN", "47, CZE"})
    void shouldConstructMoneyWithCorrectCurrencyTypeAndAmount(int validAmount, String validCurrency) {
        //given
        MoneyJunit5 money = new MoneyJunit5(validAmount, validCurrency);
        //when
        softlyAssert.assertThat(money.getAmount()).isEqualTo(validAmount);
        softlyAssert.assertThat(money.getCurrency()).isEqualTo(validCurrency);
        //then
        softlyAssert.assertAll();
    }
}