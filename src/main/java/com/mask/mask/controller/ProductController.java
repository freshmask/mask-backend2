package com.mask.mask.controller;

import com.mask.mask.dto.ProductSearchDTO;
import com.mask.mask.entity.Product;
import com.mask.mask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @GetMapping("/products")
    public Page<Product> searchProduct (@RequestParam(name="page", defaultValue = "0") Integer page,
                                        @RequestParam(name="size", defaultValue = "5") Integer sizePerPage,
                                        @RequestParam(name = "productName", required = false) String productName,
                                        @RequestParam(name = "isActive", required = false) String isActive){
        Pageable pageable= PageRequest.of(page,sizePerPage);
        ProductSearchDTO productSearchDTO = new ProductSearchDTO(productName, isActive);
        return productService.getProductsInPage(pageable, productSearchDTO);
    }

    @GetMapping("/products/list")
    public List<Product> getAllListProduct (){
        return productService.getAllProduct();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable(name = "productId") String productId){
        Product product = productService.getProductById(productId);
        return product;
    }

    @PutMapping("/product")
    public void editProduct(@RequestBody Product product){
        productService.editProduct(product);
    }

    @DeleteMapping("/product")
    public void deleteProductById (@RequestParam(name = "productId") String productId){
        productService.deleteProductById(productId);
    }
    
    @GetMapping("/productsStatus")
    public List<Product> getProductsBasedIsActive (@RequestParam(name = "status") String status){
        return productService.getProductByisActive(status);
    }

    @PutMapping("/productstatus/{id}")
    public void setStatusProduct (@PathVariable String id){
        productService.setStatusProduct(id);
    }
}
