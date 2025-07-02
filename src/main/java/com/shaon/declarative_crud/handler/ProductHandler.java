package com.shaon.declarative_crud.handler;


import com.shaon.declarative_crud.model.Product;
import com.shaon.declarative_crud.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

    private final ProductRepository productRepository;

    public ProductHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Mono<ServerResponse> getAllProduct(ServerRequest request){
        return ServerResponse.ok().body(productRepository.findAll(), Product.class);
    }


    public Mono<ServerResponse> getProductById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return productRepository.findById(id).flatMap(product -> ServerResponse.ok().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


    public Mono<ServerResponse> saveProduct(ServerRequest request){
        Mono<Product> productMono = request.bodyToMono(Product.class);
        return productMono.flatMap(productRepository::save).flatMap(savedProduct -> ServerResponse.ok().bodyValue(savedProduct));
    }


    public Mono<ServerResponse> updateProduct(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<Product> updatedProduct = request.bodyToMono(Product.class);
        return productRepository.findById(id).flatMap(existingProduct -> updatedProduct.flatMap(updatedProductMono-> {
            existingProduct.setProductName(updatedProductMono.getProductName());
            existingProduct.setProductCode(updatedProductMono.getProductCode());
            existingProduct.setProductPrice(updatedProductMono.getProductPrice());
            return productRepository.save(existingProduct);
        })).flatMap(product -> ServerResponse.ok().bodyValue(product)).switchIfEmpty(ServerResponse.notFound().build());
    }


    public Mono<ServerResponse> deleteProduct(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return productRepository.findById(id).flatMap(product -> productRepository.delete(product)).then(ServerResponse.noContent().build()).switchIfEmpty(ServerResponse.notFound().build());
    }
}
