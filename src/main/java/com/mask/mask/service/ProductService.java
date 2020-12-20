package com.mask.mask.service;

import com.mask.mask.dto.ProductSearchDTO;
import com.mask.mask.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public void addProduct(Product product);
    public Page<Product> getProductsInPage(Pageable pageable, ProductSearchDTO productSearchDTO);
    public List<Product> getAllProduct();
    public Product getProductById (String productId);
    public void editProduct(Product product);
    public void deleteProductById (String productId);

    public List<Product> getProductByisActive(String status);

    public void setStatusProduct(String id);

}
