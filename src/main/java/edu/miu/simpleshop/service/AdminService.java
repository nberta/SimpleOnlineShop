package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.Admin;

public interface AdminService {

    Admin getById(Long id);
    Admin save(Admin admin);
    Admin delete(Long id);
}