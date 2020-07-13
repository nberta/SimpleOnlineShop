package edu.miu.simpleshop.service.impl;


import edu.miu.simpleshop.domain.Admin;
import edu.miu.simpleshop.repository.AdminRepository;
import edu.miu.simpleshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin getById(Long id) {
        return adminRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin delete(Long id) {
        Admin admin = getById(id);
        adminRepository.delete(admin);
        return admin;
    }
}