package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.User;
import edu.miu.simpleshop.repository.UserRepository;
import edu.miu.simpleshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User getByUserName(String username) {
        return repository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    //I made this in order to get the username when they are logged in.
    @Override
    public User getSignedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.getByUserName(auth.getName());
    }


}
