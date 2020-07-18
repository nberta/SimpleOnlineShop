package edu.miu.simpleshop.builder;

import edu.miu.simpleshop.domain.Category;

public class CategoryBuilder {

	private Category category;

	public CategoryBuilder() {
		this.category = new Category();
	}

	public CategoryBuilder withName(String name) {
		this.category.setName(name);
		return this;
	}


	public Category build() {
		return category;
	}

}
