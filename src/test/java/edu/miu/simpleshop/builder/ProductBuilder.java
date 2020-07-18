package edu.miu.simpleshop.builder;

import edu.miu.simpleshop.domain.Category;
import edu.miu.simpleshop.domain.Product;

public class ProductBuilder {

     private Product product;
    
 	public ProductBuilder() {
		this.product = new Product();
	}

    public ProductBuilder withCategory(Category category) {
        this.product.setCategory(category);
        return this;
    }
    public ProductBuilder withName(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.product.setDescription(description);
        return this;
    }


    public ProductBuilder withQuantity(int quantity) {
        this.product.setQuantity(quantity);
        return this;
    }

    public ProductBuilder withPrice(int price) {
        this.product.setPrice(price);
        return this;
    }


    public Product build() {
        return product;
    }

	
}
