package br.com.stoom.store.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_sequence")
    @SequenceGenerator(name = "price_sequence", sequenceName = "PRICE_SEQ")
    @Column(name = "id")
    private Long id;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    public Price() {
    }

    public Price(Double price, Integer quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
