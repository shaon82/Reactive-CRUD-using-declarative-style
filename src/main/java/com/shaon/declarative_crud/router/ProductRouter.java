package com.shaon.declarative_crud.router;

import com.shaon.declarative_crud.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<?> route(ProductHandler productHandler){
        return RouterFunctions.route(RequestPredicates.GET("/get/all-product"),productHandler::getAllProduct)
                .andRoute(RequestPredicates.GET("/find/product/{id}"),productHandler::getProductById)
                .andRoute(RequestPredicates.POST("/save/product"),productHandler::saveProduct)
                .andRoute(RequestPredicates.PUT("/update/product/{id}"),productHandler::updateProduct)
                .andRoute(RequestPredicates.DELETE("/delete/product/{id}"),productHandler::deleteProduct);
    }



}
