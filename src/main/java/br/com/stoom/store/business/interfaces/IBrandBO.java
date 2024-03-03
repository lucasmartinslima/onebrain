package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Brand;

import java.util.List;

public interface IBrandBO {

    Brand save(Brand brand);

    List<Brand> findByIsActiveEnable();

    List<Brand> findByIsActiveFalse();

}
