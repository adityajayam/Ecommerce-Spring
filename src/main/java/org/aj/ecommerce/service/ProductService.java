package org.aj.ecommerce.service;

import org.aj.ecommerce.model.Product;
import org.aj.ecommerce.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int productId) {
        return productRepo.findById(productId).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImage(imageFile.getBytes());
            product.setImageType(imageFile.getContentType());
        }
        return productRepo.save(product);
    }

    public Product updateProduct(Product product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImage(imageFile.getBytes());
            product.setImageType(imageFile.getContentType());
        }
        return productRepo.save(product);
    }

    public void deleteProduct(int productId) {
        productRepo.deleteById(productId);
    }

    public List<Product> searchProducts(String keyWord) {
        return productRepo.searchProducts(keyWord);
    }
}
