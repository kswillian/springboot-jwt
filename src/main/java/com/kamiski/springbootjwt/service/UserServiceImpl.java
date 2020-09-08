package com.kamiski.springbootjwt.service;

import com.kamiski.springbootjwt.controller.form.UserForm;
import com.kamiski.springbootjwt.controller.form.UserFormUpdate;
import com.kamiski.springbootjwt.domain.Users;
import com.kamiski.springbootjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users create(UserForm userForm) {

        Users users = Users.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .dateRegister(LocalDateTime.now())
                .isCredentialsNonExpired(true)
                .isNonExpired(true)
                .isNonLocked(true)
                .isEnabled(true)
                .build();

        return userRepository.save(users);
    }

    @Override
    public Users update(UserFormUpdate userFormUpdate) {

        Users users = Users.builder()
                .name(userFormUpdate.getName())
                .email(userFormUpdate.getEmail())
                .password(userFormUpdate.getPassword())
                .profiles(userFormUpdate.getProfileList())
                .build();

        return userRepository.save(users);

    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
