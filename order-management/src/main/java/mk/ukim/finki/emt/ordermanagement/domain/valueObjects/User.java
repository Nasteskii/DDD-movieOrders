package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;

@Getter
public class User implements ValueObject {
    private final UserId userId;
    private final String username;
    private final String password;
    private final Price bankAccount;

    protected User() {
        this.userId = UserId.randomId(UserId.class);
        this.username = "";
        this.password = "";
        this.bankAccount = Price.valueOf(Currency.MKD, 0);
    }

    @JsonCreator
    public User(UserId userId, String username, String password, Price bankAccount) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.bankAccount = bankAccount;
    }
}

