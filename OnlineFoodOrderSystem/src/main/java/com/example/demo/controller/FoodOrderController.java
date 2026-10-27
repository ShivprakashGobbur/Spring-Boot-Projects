package com.example.demo.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.FoodOrder;
import com.example.demo.service.FoodOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class FoodOrderController {
	 private final FoodOrderService service;

	    public FoodOrderController(FoodOrderService service) {
	        this.service = service;
	    }

	    // Add a new food order
	    @PostMapping
	    public ResponseEntity<FoodOrder> addOrder(
	            @Valid @RequestBody FoodOrder order) {

	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(service.addOrder(order));
	    }

	    // Get all food orders
	    @GetMapping
	    public List<FoodOrder> getAllOrders() {
	        return service.getAllOrders();
	    }

	    // Get an order by ID
	    @GetMapping("/{id}")
	    public FoodOrder getOrderById(@PathVariable Long id) {
	        return service.getOrderById(id);
	    }

	    // Update the complete order
	    @PutMapping("/{id}")
	    public FoodOrder updateOrder(
	            @PathVariable Long id,
	            @Valid @RequestBody FoodOrder order) {

	        return service.updateOrder(id, order);
	    }

	    // Partially update an order
	    @PatchMapping("/{id}")
	    public FoodOrder patchOrder(
	            @PathVariable Long id,
	            @RequestBody FoodOrder order) {

	        return service.patchOrder(id, order);
	    }

	    // Delete an order
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
	        return ResponseEntity.ok(service.deleteOrder(id));
	    }
}
