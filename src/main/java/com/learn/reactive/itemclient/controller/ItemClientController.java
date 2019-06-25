package com.learn.reactive.itemclient.controller;

import com.learn.reactive.itemclient.domain.Item;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ItemClientController {

    WebClient webClient = WebClient.create("http://localhost:8083");

    @GetMapping("/client/retrieve")
    public Flux<Item> getAllItemUsingRetrieve(){
        return webClient.get().uri("/v1/item")
                .retrieve()
                .bodyToFlux(Item.class)
                .log();
    }

//    @GetMapping("/client/retrieve")
//    public Flux<Item> getAllItemUsingExchange(){
//        return webClient.get().uri("/v1/item")
//                .exchange()
//                .flatMapMany( clientResponse -> clientResponse.bodyToFlux(Item.class))
//                .log();
//    }

    @GetMapping("/client/retrieve/singleItem")
    public Mono<Item> getSingleItemUsingRetrieve(){
        String id = "55";
        return webClient.get().uri("/v1/item/{id}", id)
                .retrieve()
                .bodyToMono(Item.class)
                .log();
    }

    @PostMapping("/client/create/singleItem")
    public Mono<Item> createSingleItemUsingRetrieve(@RequestBody Item item){
        Mono<Item> itemMono = Mono.just(item);
        return webClient.post().uri("/v1/item/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemMono, Item.class)
                .retrieve()
                .bodyToMono(Item.class)
                .log("Created Item: ");
    }

    @PutMapping("/client/update/singleItem/")
    public Mono<Item> updateSingleItemUsingRetrieve(@RequestBody Item item){
        Mono<Item> itemMono = Mono.just(item);
        String id = "55";
        return webClient.put().uri("/v1/item/{id}", id)
                .body(itemMono, Item.class)
                .retrieve()
                .bodyToMono(Item.class)
                .log("Update Item: ");
    }

    @DeleteMapping("/client/delete/singleItem/")
    public Mono<Item> udateSingleItemUsingRetrieve(){
        String id = "55";
        return webClient.delete().uri("/v1/item/{id}", id)
                .retrieve()
                .bodyToMono(Item.class)
                .log("Update Item: ");
    }
}
