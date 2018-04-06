package basic;

import java.util.Objects;

public class MoneyJunit5 {
    private final int amount;
    private final String currency;

    MoneyJunit5(int amount, String currency) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount can't be 0 or less");
        }
        if (currency == null || currency.isEmpty()) {
            throw new IllegalArgumentException("Currency can't be null or empty");
        }

        this.amount = amount;
        this.currency = currency;
    }

    int getAmount() {
        return amount;
    }

    String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyJunit5 that = (MoneyJunit5) o;
        return amount == that.amount &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, currency);
    }
}
