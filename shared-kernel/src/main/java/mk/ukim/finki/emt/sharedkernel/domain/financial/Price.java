package mk.ukim.finki.emt.sharedkernel.domain.financial;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.valueObjects.Quality;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;


@Embeddable
@Getter
public class Price implements ValueObject {
    @Enumerated(value = EnumType.STRING)
    private final Currency currency;
    private final double amount;

    protected Price() {
        this.amount = 0;
        this.currency = null;
    }

    public Price(@NonNull Currency currency, @NonNull double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public static Price valueOf(Currency currency, int amount) {
        return new Price(currency, amount);
    }

    public Price addPrice(Price price) {
        assert currency != null;
        if (!currency.equals(price.currency)) {
            throw new IllegalArgumentException("Cannot add two Price objects with different currencies");
        }
        return new Price(currency, amount + price.amount);
    }

    public Price removePrice(Price price) {
        assert currency != null;
        if (!currency.equals(price.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Price(currency, amount - price.amount);
    }

    public Price multiply(int m) {
        return new Price(currency, amount * m);
    }

    public Price getPriceByQuality(Quality quality) {
        assert currency != null;
        return new Price(currency, amount * quality.getQualityAddition());
    }

    public boolean checkSufficientFunds(Price userBankAccount) {
        return this.amount >= userBankAccount.getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return amount == price.amount && currency == price.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }


}
