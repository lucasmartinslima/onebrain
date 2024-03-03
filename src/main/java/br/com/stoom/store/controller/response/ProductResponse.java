package br.com.stoom.store.controller.response;


import br.com.stoom.store.controller.dto.PriceDTO;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.Product;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductResponse {

    private Long id;

    private String sku;

    private String name;

    private String description;

    private List<PriceDTO> prices = new ArrayList<>();

    private List<String> categories;

    private String brand;

    private boolean isActive;

    ModelMapper modelMapper = new ModelMapper();

    public ProductResponse(){}
    public ProductResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.sku = product.getSku();
        this.isActive = product.isActive();
        this.prices = product.getPrices()
                .stream()
                .map(e -> modelMapper.map(e, PriceDTO.class))
                .collect(Collectors.toList());
        this.categories = product.getCategories().stream().map(Category::getName).collect(Collectors.toList());
        this.brand = product.getBrand().getName();
    }

    public List<ProductResponse> toProductResponseList(List<Product> products){
        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product: products){
            ProductResponse productResponse = new ProductResponse(product);
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<PriceDTO> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceDTO> prices) {
        this.prices = prices;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
