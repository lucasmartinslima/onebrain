package br.com.stoom.store.controller;

import br.com.stoom.store.controller.dto.PriceDTO;
import br.com.stoom.store.controller.request.BrandRequest;
import br.com.stoom.store.controller.request.CategoryRequest;
import br.com.stoom.store.controller.request.ProductRequest;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Price;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.repository.PriceRepository;
import br.com.stoom.store.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PriceRepository priceRepository;

    @Test
    @Order(1)
    @DisplayName("Deve criar um produto novo")
    void deveCriarProduto() throws Exception {
        ProductRequest productRequest = getProdutoRequest();
        String produtoRequestString = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produtoRequestString))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(2)
    @DisplayName("Deve retornar todos os produtos")
    void retornarTodosOsProdutos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products?name=SKOL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Order(3)
    @DisplayName("Deve alterar o estado do produto, se esta ativo ou não")
    void alteraActive() throws Exception {

        MvcResult mockResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/products/1/changeActive")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String resultPegarTodosOsProdutos = mockResult.getResponse().getContentAsString();
        Assertions.assertTrue(resultPegarTodosOsProdutos.contains("\"active\":false"));
    }

    private ProductRequest getProdutoRequest() {
        ProductRequest productRequest = new ProductRequest();

        productRequest.setName("CERVEJA SKOL LATA");
        productRequest.setDescription("CERVEJA SKOL 300ML");
        productRequest.setSku("0000000000000N-332");
        productRequest.setPrices(this.createPrice());
        productRequest.setBrandId(this.createBrand());
        productRequest.setCategories(this.createCategory());

        return productRequest;
    }

    private Long createBrand(){
        BrandRequest brandRequest = new BrandRequest();

        brandRequest.setName("AMBEV");
        brandRequest.setDescription("AMBEV LTDA");
        Brand brand = brandRequest.toBrandDomain(brandRequest);

        return brandRepository.save(brand).getId();
    }

    private List<Category> createCategory(){
        CategoryRequest categoryRequest = new CategoryRequest();

        categoryRequest.setName("BEBIDA");
        Category category = categoryRequest.toCategoryDomain(categoryRequest);

        List<Category> categories = new ArrayList<>();
        categories.add(categoryRepository.save(category));

        return categories;
    }

    private List<Price> createPrice(){
        Price price = new Price();

        price.setPrice(4.00);
        price.setQuantity(1);

        List<Price> prices = new ArrayList<>();
        prices.add(priceRepository.save(price));

        return prices;
    }

}