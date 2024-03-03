package br.com.stoom.store.controller.request;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.Product;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductRequest {

    private String sku;

    private String name;

    private String description;

    private List<Price> prices = new ArrayList<>();

    private List<Category> categories;

    private Long brandId;

    public Product toProductDomain(ProductRequest product){
        Product productDomain = new Product();
        productDomain.setName(product.getName());
        productDomain.setSku(product.getSku());
        productDomain.setBrand(new Brand(product.getBrandId()));
        productDomain.setPrices(product.getPrices());
        productDomain.setCategories(product.getCategories());
        productDomain.setDescription(product.getDescription());
        return productDomain;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
