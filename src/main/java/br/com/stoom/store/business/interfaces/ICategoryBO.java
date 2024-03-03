package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.controller.request.CategoryRequest;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;

import java.util.List;

public interface ICategoryBO {

    List<Category> findAll();
    Category save(Category category);

    Category update(Long id, Category category);

    Category updateActive(Long id);

    List<Category> findAllInactives();

}
