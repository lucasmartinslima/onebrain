package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.exceptions.CategoryNotFoundException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandBO implements IBrandBO {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand save(Brand brand) {
        brand.setActive(true);
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> findByIsActiveEnable() {
        return brandRepository.findByIsActiveTrue();
    }

    @Override
    public List<Brand> findByIsActiveFalse() {
        return brandRepository.findByIsActiveFalse();
    }

    public Brand updateActive(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        brand.setActive(!brand.isActive());
        return brandRepository.save(brand);
    }
}
