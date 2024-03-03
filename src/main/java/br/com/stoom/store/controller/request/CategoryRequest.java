package br.com.stoom.store.controller.request;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import com.sun.istack.NotNull;

import javax.persistence.*;

public class CategoryRequest {

    private String name;

    public Category toCategoryDomain(CategoryRequest categoryRequest){
        Category categoryDomain = new Category();
        categoryDomain.setName(categoryRequest.getName());
        return categoryDomain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
