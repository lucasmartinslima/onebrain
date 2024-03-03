package br.com.stoom.store.controller.request;

import br.com.stoom.store.controller.response.BrandResponse;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Product;
import com.sun.istack.NotNull;

import javax.persistence.Column;

public class BrandRequest {

    private String name;

    private String description;

    public Brand toBrandDomain(BrandRequest brandRequest){
        Brand brandDomain = new Brand();
        brandDomain.setDescription(brandRequest.getDescription());
        brandDomain.setName(brandRequest.getName());
        return brandDomain;
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
}
