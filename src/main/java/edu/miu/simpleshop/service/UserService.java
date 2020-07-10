package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.User;

public interface UserService {

    User getByUserName(String username);
    User delete(User user);

}
