package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductBO {

    Page<Product> findAll(Pageable pageable);

    Product save(Product product);

    void delete(Product product);

    Product update(Product product, Long id);

    Product updateActive(Long id);
}
