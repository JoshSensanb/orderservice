package edu.iu.c322.orderservice.controller;


import edu.iu.c322.orderservice.model.Order;
import edu.iu.c322.orderservice.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private WebClient repository;

    public OrderController(WebClient.Builder webClientBuilder) {
        repository = webClientBuilder.baseUrl("https://localhost:8080").build();
    }


    @GetMapping("/getorder/{id}")
    Order getOrder(@Valid @PathVariable int id){
        Order order = repository.get().uri("/getorder/{id}",id).retrieve().bodyToMono(Order.class).block();
        return order;
    }


    @PostMapping("/create/")
    public int create(@Valid @RequestBody Order order) {
        Order order1 = (Order) repository.post().uri("/create/",order);
        return order.getOrderId();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        repository.delete().uri("/delete/{id}",id);

    }

}


