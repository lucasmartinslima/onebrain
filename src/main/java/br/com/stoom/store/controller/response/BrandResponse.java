package br.com.stoom.store.controller.response;

import br.com.stoom.store.controller.dto.PriceDTO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BrandResponse {

    private Long id;

    private String name;

    private String description;

    private boolean isActive;

    public BrandResponse(){}
    public BrandResponse(Brand brand){
        this.id = brand.getId();
        this.name = brand.getName();
        this.description = brand.getDescription();
        this.isActive = brand.isActive();
    }

    public List<BrandResponse> toBrandResponseList(List<Brand> brands){
        List<BrandResponse> brandResponses = new ArrayList<>();
        for(Brand brand: brands){
            BrandResponse brandResponse = new BrandResponse(brand);
            brandResponses.add(brandResponse);
        }
        return brandResponses;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
