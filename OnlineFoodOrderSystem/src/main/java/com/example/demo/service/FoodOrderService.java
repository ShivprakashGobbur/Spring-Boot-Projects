package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.model.FoodOrder;

@Service
public class FoodOrderService {
	 private final List<FoodOrder> orders = new ArrayList<>();
	    private Long nextOrderId = 1L;

	    // Add a food order
	    public FoodOrder addOrder(FoodOrder order) {
	        order.setOrderId(nextOrderId++);
	        orders.add(order);
	        return order;
	    }

	    // Get all orders
	    public List<FoodOrder> getAllOrders() {
	        return orders;
	    }

	    // Get order by ID
	    public FoodOrder getOrderById(Long orderId) {
	        return orders.stream()
	                .filter(order -> order.getOrderId().equals(orderId))
	                .findFirst()
	                .orElseThrow(() ->
	                    new OrderNotFoundException(
	                        "Order not found with ID: " + orderId));
	    }

	    // Update the complete order
	    public FoodOrder updateOrder(Long orderId, FoodOrder updatedOrder) {
	        FoodOrder existingOrder = getOrderById(orderId);

	        existingOrder.setCustomerName(updatedOrder.getCustomerName());
	        existingOrder.setFoodItem(updatedOrder.getFoodItem());
	        existingOrder.setQuantity(updatedOrder.getQuantity());
	        existingOrder.setPrice(updatedOrder.getPrice());
	        existingOrder.setDeliveryAddress(updatedOrder.getDeliveryAddress());
	        existingOrder.setPaymentMethod(updatedOrder.getPaymentMethod());

	        return existingOrder;
	    }

	    // Partially update an order
	    public FoodOrder patchOrder(Long orderId, FoodOrder updatedOrder) {
	        FoodOrder existingOrder = getOrderById(orderId);

	        if (updatedOrder.getCustomerName() != null) {
	            existingOrder.setCustomerName(updatedOrder.getCustomerName());
	        }

	        if (updatedOrder.getFoodItem() != null) {
	            existingOrder.setFoodItem(updatedOrder.getFoodItem());
	        }

	        if (updatedOrder.getQuantity() != null &&
	                updatedOrder.getQuantity() > 0) {
	            existingOrder.setQuantity(updatedOrder.getQuantity());
	        }

	        if (updatedOrder.getPrice() != null) {
	            existingOrder.setPrice(updatedOrder.getPrice());
	        }

	        if (updatedOrder.getDeliveryAddress() != null) {
	            existingOrder.setDeliveryAddress(
	                    updatedOrder.getDeliveryAddress());
	        }

	        if (updatedOrder.getPaymentMethod() != null) {
	            existingOrder.setPaymentMethod(updatedOrder.getPaymentMethod());
	        }

	        return existingOrder;
	    }

	    // Delete an order
	    public String deleteOrder(Long orderId) {
	        FoodOrder order = getOrderById(orderId);
	        orders.remove(order);
	        return "Order deleted successfully with ID: " + orderId;
	    }
}
