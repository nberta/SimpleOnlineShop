package edu.miu.simpleshop.service;


import edu.miu.simpleshop.domain.BillingInfo;

public interface BillingInfoService {

    BillingInfo getById(Long id);
    BillingInfo getForOrderId(Long id);
    BillingInfo save(BillingInfo billingInfo);

}
