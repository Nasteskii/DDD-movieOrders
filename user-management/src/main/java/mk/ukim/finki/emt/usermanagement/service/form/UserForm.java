package mk.ukim.finki.emt.usermanagement.service.form;

import javax.validation.constraints.Min;
import com.sun.istack.NotNull;
import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;

@Data
public class UserForm {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    @Min(0)
    private Price bankAccount;
}
