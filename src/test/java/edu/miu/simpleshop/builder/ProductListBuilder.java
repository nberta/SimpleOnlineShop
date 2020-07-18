package edu.miu.simpleshop.builder;

import edu.miu.simpleshop.domain.Product;

import java.util.Arrays;
import java.util.List;


public class ProductListBuilder {

	public ProductBuilder productBuilderOne = new ProductBuilder()

//            .withCategory(new CategoryBuilder()
//                    .withName("Sports")
//                   .build())
            .withDescription("An electronics")
            .withName("Laptop")
			.withQuantity(3)
			.withPrice(1400);


	public ProductBuilder productBuilderTwo = new ProductBuilder()
//			.withCategory(new CategoryBuilder()
//					.withName("Sports")
//					.build())
			.withDescription("An electronics")
			.withName("Mobile")
			.withQuantity(5)
			.withPrice(5000);

	public List<Product> build() {
		
	    Product first = productBuilderOne.build();	
	    Product second = productBuilderTwo.build();
 	    return (List<Product>) Arrays.asList(first, second);
	}
	
	public  ProductBuilder getProductBuilderOne() {
		return productBuilderOne;
	}

}
