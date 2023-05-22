package mk.ukim.finki.emt.usermanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractEntity<UserId> {
    private String username;
    private String password;
    private Price bankAccount;

    public static User build(String username, String password, Price bankAccount) {
        User user = new User();
        user.username = username;
        user.password = password;
        user.bankAccount = bankAccount;
        return user;
    }
}
