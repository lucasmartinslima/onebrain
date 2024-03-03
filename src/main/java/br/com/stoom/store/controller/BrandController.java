package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.controller.request.BrandRequest;
import br.com.stoom.store.controller.response.BrandResponse;
import br.com.stoom.store.controller.response.ProductResponse;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/brands")
public class BrandController {

    @Autowired
    private BrandBO brandService;

    @PostMapping
    public ResponseEntity<BrandResponse> save(@RequestBody BrandRequest brandRequest) {
        Brand brand =  brandRequest.toBrandDomain(brandRequest);
        BrandResponse brandResponse = new BrandResponse(brandService.save(brand));
        return new ResponseEntity<>(brandResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> findAll() {
        List<BrandResponse> brandResponse = new BrandResponse().toBrandResponseList(brandService.findByIsActiveEnable());
        return new ResponseEntity<>(brandResponse, HttpStatus.OK);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<BrandResponse>> findAllInactive() {
        List<BrandResponse> brandResponse = new BrandResponse().toBrandResponseList(brandService.findByIsActiveFalse());
        return new ResponseEntity<>(brandResponse, HttpStatus.OK);
    }

    @PatchMapping("/{id}/changeActive")
    public ResponseEntity<BrandResponse> updateActive(@PathVariable Long id) {
        BrandResponse brandResponse = new BrandResponse(brandService.updateActive(id));
        return new ResponseEntity<>(brandResponse, HttpStatus.OK);
    }

}
