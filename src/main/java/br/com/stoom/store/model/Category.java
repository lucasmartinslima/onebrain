package br.com.stoom.store.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    @SequenceGenerator(name = "category_sequence", sequenceName = "CATEGORY_SEQ")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(unique=true)
    private String name;

    @NotNull
    private boolean active = true;

    public Category() {
    }

    public Category(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public void setId(Long id) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
