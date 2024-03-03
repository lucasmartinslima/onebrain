package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.exceptions.CategoryAlreadyExistsException;
import br.com.stoom.store.exceptions.CategoryNotFoundException;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBO implements ICategoryBO {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findByActiveTrue();
    }

    @Override
    public List<Category> findAllInactives() {
        return categoryRepository.findByActiveFalse();
    }

    @Override
    public Category save(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }else{
            return categoryRepository.save(category);
        }
    }

    @Override
    public Category updateActive(Long id) {
        Category categoryUpdate = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryUpdate.setActive(!categoryUpdate.isActive());
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public Category update(Long id, Category category) {
        Category categoryUpdate = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryUpdate.setActive(category.isActive());
        categoryUpdate.setName(category.getName());
        return categoryRepository.save(categoryUpdate);
    }
}
