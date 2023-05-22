package mk.ukim.finki.emt.usermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Price;
import mk.ukim.finki.emt.usermanagement.domain.model.User;
import mk.ukim.finki.emt.usermanagement.domain.repository.UserRepository;
import mk.ukim.finki.emt.usermanagement.service.UserService;
import mk.ukim.finki.emt.usermanagement.service.form.UserForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User register(UserForm userForm) {
        User newUser = User.build(userForm.getUsername(), passwordEncoder.encode(userForm.getPassword()), userForm.getBankAccount());
        return this.userRepository.save(newUser);
    }
}
