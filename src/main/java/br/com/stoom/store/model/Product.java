package br.com.stoom.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ")
    @Column(name = "id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "sku")
    private String sku;

    @ManyToOne
    private Brand brand;

    @Column(name = "categories")
    @ManyToMany
    @JoinColumn(name = "product_id")
    private List<Category> categories;

    @Column(name = "prices")
    @OneToMany
    private List<Price> prices;

    private boolean isActive;

    public Product() {
    }

    public Product(String name, String description, String sku, Brand brand, List<Category> categories, List<Price> prices, boolean isActive) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.brand = brand;
        this.categories = categories;
        this.prices = prices;
        this.isActive = isActive;
    }



    public void setId(Long id) {
        this.id = id;
    }
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", brand=" + brand +
                ", categories=" + categories +
                ", prices=" + prices +
                ", isActive=" + isActive +
                '}';
    }
}