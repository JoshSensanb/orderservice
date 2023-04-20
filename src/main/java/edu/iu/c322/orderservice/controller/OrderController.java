package edu.iu.c322.orderservice.controller;


import edu.iu.c322.orderservice.model.Order;
import edu.iu.c322.orderservice.model.OrderItem;
import edu.iu.c322.orderservice.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@RequestBody Order order){

        for(int i = 0; i < order.getItems().size(); i++){
            OrderItem item = order.getItems().get(i);
            item.setOrder(order);
        }
        Order addedOrder = orderRepository.save(order);
        return addedOrder.getId();
    }

    @GetMapping("/{orderId}")
    public List<Order> findByCustomer(@PathVariable int orderId){
        return orderRepository.findByCustomerId(orderId);
    }

    @GetMapping("/order/{orderId}")
    public Optional<Order> findByOrderId(@PathVariable int orderId){
        return orderRepository.findById(orderId);
    }
    @DeleteMapping("/delete/{orderId}")
    public void delete(@PathVariable int orderId){
        Optional<Order> order = findByOrderId(orderId);
        Order orderDelete = order.get();
        orderRepository.delete(orderDelete);
    }

}


