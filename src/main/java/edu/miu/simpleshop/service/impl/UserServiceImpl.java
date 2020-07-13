package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.User;
import edu.miu.simpleshop.repository.UserRepository;
import edu.miu.simpleshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User getByUserName(String username) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }


}
