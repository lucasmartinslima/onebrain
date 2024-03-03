package br.com.stoom.store.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    @SequenceGenerator(name = "brand_sequence", sequenceName = "BRAND_SEQ")
    @Column(name = "id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    private boolean isActive;

    public Brand(){}

    public Brand(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Brand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Brand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase().trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
