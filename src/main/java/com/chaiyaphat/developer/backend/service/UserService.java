package com.chaiyaphat.developer.backend.service;

import com.chaiyaphat.developer.backend.entity.User;
import com.chaiyaphat.developer.backend.exception.UserException;
import com.chaiyaphat.developer.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);   //SecurityConfig
    }

    //1update user   //bad
    public User update(User user) {
        return repository.save(user);
    }

    //2update user  best practice
    public User updateName(String id, String name) throws UserException {
        Optional<User> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw UserException.notFound();
        }
        User user = opt.get();
        user.setName(name);
        return repository.save(user);
    }

    //delete service
    public void deleteById(String id){
        repository.deleteById(id);
    }



    public User create(String email, String password, String name) throws UserException {
        //validate
        if (Objects.isNull(email)) {
            //throw error email null
            throw UserException.createEmailNull();
        }

        if (Objects.isNull(name)) {
            //throw error name null
            throw UserException.createNameNull();
        }

        if (Objects.isNull(password)) {
            //throw error password null
            throw UserException.createPasswordNull();
        }

        //verify
        if (repository.existsByEmail(email)) {
            throw UserException.createEmailDuplicate();
        }

        //save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);
        return repository.save(entity);
    }
}
