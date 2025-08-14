package com.pharmacy.service;

import com.pharmacy.model.Product;
import com.pharmacy.repository.CategoryRepository;
import com.pharmacy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository; // Inject CategoryRepository for relationship handling

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        // Ensure the associated Category exists before saving the Product
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            categoryRepository.findById(product.getCategory().getId()).ifPresent(product::setCategory);
        }
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id).map(product -> {
            product.setCode(productDetails.getCode());
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setUnit(productDetails.getUnit());
            product.setWeight(productDetails.getWeight());

            // Update category if provided and exists
            if (productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
                categoryRepository.findById(productDetails.getCategory().getId())
                        .ifPresent(product::setCategory);
            } else if (productDetails.getCategory() == null) {
                // If category is set to null in details, remove association
                product.setCategory(null);
            }

            product.setPurchasePrice(productDetails.getPurchasePrice());
            product.setSalePrice(productDetails.getSalePrice());
            product.setImageUrl(productDetails.getImageUrl());
            product.setCurrentStock(productDetails.getCurrentStock());
            product.setDesiredStock(productDetails.getDesiredStock());
            return productRepository.save(product);
        });
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
