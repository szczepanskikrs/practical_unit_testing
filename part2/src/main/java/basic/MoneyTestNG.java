package basic;

import java.util.Objects;

class MoneyTestNG {
    private final int amount;
    private final String currency;

    MoneyTestNG(int amount, String currency) {
        //throwing exceptions in constructor is bad
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
        MoneyTestNG moneyTestNG = (MoneyTestNG) o;
        return amount == moneyTestNG.amount &&
                Objects.equals(currency, moneyTestNG.currency);
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, currency);
    }
}
