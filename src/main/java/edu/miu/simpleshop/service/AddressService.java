package edu.miu.simpleshop.service;

import edu.miu.simpleshop.domain.Address;

public interface AddressService {

    Address getCurrentShippingAddress(Long buyerId);
    Address getCurrentBillingAddress(Long buyerId);
}
