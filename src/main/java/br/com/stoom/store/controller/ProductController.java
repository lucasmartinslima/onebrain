package br.com.stoom.store.controller;

import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.controller.dto.PriceDTO;
import br.com.stoom.store.controller.request.ProductRequest;
import br.com.stoom.store.controller.response.ProductResponse;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.specification.ProductSpecifications;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductBO productService;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) List<String> category,
            Pageable pageable) {

        List<Product> p = new ArrayList<>();
        Specification<Product> spec = Specification.where(null);

        if(name != null)
            spec = spec.and(ProductSpecifications.hasNameLike(name));

        if(brand != null)
            spec = spec.and(ProductSpecifications.hasBrand(brand));

        if(category != null)
            spec = spec.and(ProductSpecifications.hasCategory(category));

        spec = spec.and(ProductSpecifications.productIsActive());

        p = productService.findAll(spec, pageable).getContent();

        if(!p.isEmpty()){
            List<ProductResponse> productResponses = new ProductResponse().toProductResponseList(p);
            return new ResponseEntity<>(productResponses, HttpStatus.OK);
        }  else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest productRequest) {
        Product product =  productRequest.toProductDomain(productRequest);
        product.setActive(true);
        ProductResponse productResponse = new ProductResponse(productService.save(product));
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/changeActive")
    public ResponseEntity<ProductResponse> updateIsActive(@PathVariable Long id) {
        ProductResponse productResponse = new ProductResponse(productService.updateActive(id));
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody ProductRequest productRequest, @PathVariable Long id) {
        Product product =  productRequest.toProductDomain(productRequest);
        ProductResponse productResponse = new ProductResponse(productService.update(product,id));
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

}
