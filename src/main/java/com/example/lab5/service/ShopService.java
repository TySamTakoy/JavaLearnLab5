package com.example.lab5.service;


import com.example.lab5.dto.CartDTO;
import com.example.lab5.dto.ProductDTO;
import com.example.lab5.entity.CartEntity;
import com.example.lab5.entity.ProductEntity;
import com.example.lab5.repository.ProductRepository;
import com.example.lab5.entity.UserEntity;
import com.example.lab5.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public ShopService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<ProductDTO> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.id = product.getId();
                    productDTO.name = product.getName();
                    productDTO.description = product.getDescription();
                    productDTO.price = product.getPrice();
                    productDTO.imageUrl = product.getImage();
                    return productDTO;
                })
                .collect(Collectors.toList());
    }

    public ProductDTO getProduct(Long id) {
        var productEntity = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        ProductDTO productDTO = new ProductDTO();
        productDTO.id = productEntity.getId();
        productDTO.name = productEntity.getName();
        productDTO.description = productEntity.getDescription();
        productDTO.price = productEntity.getPrice();
        productDTO.imageUrl = productEntity.getImage();
        return productDTO;
    }

    public void updateProduct(ProductDTO productDTO) {
        Optional<ProductEntity> productEntity;
        if (productDTO.id == null) {
            productEntity = Optional.of(new ProductEntity());
        } else {
            productEntity = productRepository.findById(productDTO.id);
        }

        if (productEntity.isEmpty()) {
            productEntity = Optional.of(new ProductEntity());
        }
        productEntity.get().setName(productDTO.name);
        productEntity.get().setDescription(productDTO.description);
        productEntity.get().setPrice(productDTO.price);
        productEntity.get().setImage(productDTO.imageUrl);
        productRepository.save(productEntity.get());
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public CartDTO getUserCart(Authentication authentication) {
        Optional<UserEntity> user = userRepository.findByFullName(authentication.getName());
        CartDTO cartDTO = new CartDTO();
        cartDTO.userId = user.map(UserEntity::getId).orElse(null);
        if (user.isPresent()) {
            cartDTO.items = user.get().getCartItems()
                    .stream()
                    .map(product -> {
                        CartDTO.CartItemDTO cartItemDTO = new CartDTO.CartItemDTO();
                        cartItemDTO.productId = product.getProduct().getId();
                        cartItemDTO.quantity = product.getQuantity();
                        return cartItemDTO;
                    })
                    .collect(Collectors.toList());
            return cartDTO;
        }
        return new CartDTO();
    }

    public void addToCart(Long productId, Integer quantity, Authentication authentication) {
        Optional<UserEntity> user = userRepository.findByFullName(authentication.getName());
        if (user.isPresent()) {
            var product = productRepository.findById(productId);
            if (product.isPresent()) {
                var cartItem = user.get().getCartItems()
                        .stream()
                        .filter(item -> item.getProduct().getId().equals(productId))
                        .findFirst();

                if (cartItem.isPresent()) {
                    cartItem.get().setQuantity(cartItem.get().getQuantity() + 1);
                } else {
                    CartEntity cartEntity = new CartEntity();
                    cartEntity.setUser(user.get());
                    cartEntity.setProduct(product.get());
                    cartEntity.setQuantity(quantity);
                    user.get().getCartItems().add(cartEntity);
                }
                userRepository.save(user.get());
            }
        }
    }

    public void removeFromCart(Long productId, Integer quantity, Authentication authentication) {
        Optional<UserEntity> user =  userRepository.findByFullName(authentication.getName());
        if (user.isPresent()) {
            var cartItem = user.get().getCartItems()
                    .stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst();

            if (cartItem.isPresent()) {
                if (cartItem.get().getQuantity() > quantity) {
                    cartItem.get().setQuantity(cartItem.get().getQuantity() - quantity);
                } else {
                    user.get().getCartItems().remove(cartItem.get());
                }
                userRepository.save(user.get());
            }
        }
    }

    public void clearCart(Authentication authentication) {
        Optional<UserEntity> user = userRepository.findByFullName(authentication.getName());
        if (user.isPresent()) {
            user.get().getCartItems().clear();
            userRepository.save(user.get());
        }
    }
}
