package basic;

import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class MoneyDataProvider {
    @DataProvider
    private static Object[][] moneyProvider() {
        return new Object[][]{
                {new MoneyTestNG(10, "EUR"), 10, "EUR"},
                {new MoneyTestNG(3340, "USD"), 3340, "USD"},
                {new MoneyTestNG(4207, "CHF"), 4207, "CHF"},
                {new MoneyTestNG(153, "GBP"), 153, "GBP"},
                {new MoneyTestNG(1041, "PLN"), 1041, "PLN"},
        };
    }

    @DataProvider
    private Iterator<Object[]> incorrectCurrencyProvider() {
        return new Iterator<Object[]>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < 20;
            }

            @Override
            public Object[] next() {
                counter++;
                if (counter % 2 == 0)
                    return new Object[]{counter, null};
                else return new Object[]{counter, ""};
            }
        };
    }

    @DataProvider
    private Iterator<Object[]> incorrectAmountProvider() {
        return new Iterator<Object[]>() {
            int counter = -20;

            @Override
            public boolean hasNext() {
                return counter < 0;
            }

            @Override
            public Object[] next() {
                counter++;
                return new Object[]{counter, "USD"};
            }
        };
    }
}
