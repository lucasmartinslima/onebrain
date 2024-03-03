package br.com.stoom.store.controller.response;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {

    private Long id;

    private String name;

    private boolean active;

    public CategoryResponse(){}
    public CategoryResponse(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.active = category.isActive();
    }

    public List<CategoryResponse> toCategoryResponseList(List<Category> categories){
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for(Category category: categories){
            CategoryResponse brandResponse = new CategoryResponse(category);
            categoryResponses.add(brandResponse);
        }
        return categoryResponses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
