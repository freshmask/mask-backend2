package com.mask.mask.service;

import com.mask.mask.dto.ProductSearchDTO;
import com.mask.mask.entity.Product;
import com.mask.mask.exception.DataNotFoundException;
import com.mask.mask.exception.OtherDataNotFoundException;
import com.mask.mask.repository.ProductRepository;
import com.mask.mask.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll(Sort.by("productName").ascending());
    }

    @Override
    public void addProduct(Product product) {
        product.setVersion(0);
        productRepository.save(product);
    }

    @Override
    public Page<Product> getProductsInPage(Pageable pageable, ProductSearchDTO productSearchDTO) {
        Specification<Product> productSpecification= ProductSpecification.getSpecification(productSearchDTO);
        return productRepository.findAll(productSpecification, pageable);
    }

    @Override
    public void editProduct(Product product) {
        if (!productRepository.existsById(product.getProductId())) {
            throw new DataNotFoundException(String.format(DataNotFoundException.DATA_NOT_FOUND, product.getClass(), product.getProductId()));
        } else {
            Product product1 = productRepository.findById(product.getProductId()).get();
            product.setVersion(product1.getVersion() + 1);
            productRepository.save(product);
        }

    }

    @Override
    public Product getProductById(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);;
        if (productOptional.isPresent()){
            return productOptional.get();
        }
        throw new OtherDataNotFoundException(String.format(OtherDataNotFoundException.NOT_FOUND_MESSAGE,Product.class,productId));
    }

    @Override
    public void deleteProductById(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getProductByisActive(String status) {
        List<Product> productList = productRepository.findProductByIsActive(status);
        return productList;
    }

    @Override
    public void setStatusProduct(String id) {
        Product product = productRepository.findById(id).get();
        String status = product.getIsActive();
        if (status.equalsIgnoreCase("false")){
            product.setIsActive("true");
        } else if (status.equalsIgnoreCase("true")){
            product.setIsActive("false");
        }
        product.setVersion(product.getVersion() + 1);
        productRepository.save(product);
    }
}
