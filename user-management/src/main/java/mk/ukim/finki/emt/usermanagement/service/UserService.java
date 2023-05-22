package mk.ukim.finki.emt.usermanagement.service;

import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import mk.ukim.finki.emt.usermanagement.service.form.UserForm;

public interface UserService {
    User register(UserForm userForm);
}
