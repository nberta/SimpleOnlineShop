package edu.miu.simpleshop.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    Product product;

    @Before
    public void setUp() {
        product = new Product();
    }

    @Test
    public void getName() {
        String name = "admin";
        product.setName(name);
        assertEquals(name, product.getName());


    }

}