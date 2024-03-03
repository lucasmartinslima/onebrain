package br.com.stoom.store.controller;

import br.com.stoom.store.business.CategoryBO;
import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.controller.request.CategoryRequest;
import br.com.stoom.store.controller.response.BrandResponse;
import br.com.stoom.store.controller.response.CategoryResponse;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryBO categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequest.toCategoryDomain(categoryRequest);
        CategoryResponse categoryResponse = new CategoryResponse(categoryService.save(category));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> categoryResponses = new CategoryResponse().toCategoryResponseList(categoryService.findAll());
        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<CategoryResponse>> findAllInactive() {
        List<CategoryResponse> categoryResponses = new CategoryResponse().toCategoryResponseList(categoryService.findAllInactives());
        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }

    @PatchMapping("/{id}/changeActive")
    public ResponseEntity<CategoryResponse> updateActive(@PathVariable Long id) {
        CategoryResponse categoryResponse = new CategoryResponse(categoryService.updateActive(id));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequest.toCategoryDomain(categoryRequest);
        CategoryResponse categoryResponse = new CategoryResponse(categoryService.update(id,category));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

}
