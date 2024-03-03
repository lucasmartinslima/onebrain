package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.exceptions.BrandNotFoundException;
import br.com.stoom.store.exceptions.CategoryNotFoundException;
import br.com.stoom.store.exceptions.ProductNotFoundException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.repository.PriceRepository;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBO implements IProductBO {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }


    public Page<Product> findAll(Specification<Product> specification, Pageable pageable){
        return productRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public Product save(Product product){
        List<Category> categories = new ArrayList<>();
        for(Category category : product.getCategories()){
            categories.add(categoryRepository.findById(category.getId()).orElseThrow(CategoryNotFoundException::new));
        }
        Brand brand = brandRepository.findById(product.getBrand().getId()).orElseThrow(BrandNotFoundException::new);
        product.getPrices().forEach(
                p ->  priceRepository.save(p)
        );
        product.setCategories(categories);
        product.setBrand(brand);
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product){
        productRepository.delete(product);
    }

    @Override
    public Product update(Product product, Long id){
        Product productToUpdate = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        Product productUpdate = new Product();
        productUpdate.setId(id);
        productUpdate.setBrand(product.getBrand());
        productUpdate.setSku(product.getSku());
        productUpdate.setName(product.getName());
        productUpdate.setPrices(product.getPrices());
        productUpdate.setCategories(product.getCategories());
        productUpdate.setDescription(product.getDescription());
        productUpdate.setActive(product.isActive());

        return this.save(productUpdate);
    }

    @Override
    public Product updateActive(Long id){
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setActive(!product.isActive());
        return productRepository.save(product);
    }


}
