package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.User;

import java.util.List;

public interface UserService {

    User getByUserName(String username);
    User delete(User user);
    User save(User user);

}
