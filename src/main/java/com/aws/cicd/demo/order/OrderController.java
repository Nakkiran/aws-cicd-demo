package com.aws.cicd.demo.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	    @Autowired
	    private OrderService orderService;


	    @PostMapping(produces = "application/json", consumes = "application/json")
	    public ResponseEntity<Order> addOrder(@RequestBody Order o) {
	        orderService.addOrder(o);
	        return new ResponseEntity<>(o, HttpStatus.CREATED);
	    }

	    @GetMapping(produces = "application/json")
	    public ResponseEntity<List<Order>> getAllOrders() {
	        List<Order> orders = orderService.getAllOrders();
	        return new ResponseEntity<>(orders, HttpStatus.OK);
	    }

	    @GetMapping(value = "/{id}", produces = "application/json")
	    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
	        Optional<Order> order = orderService.getOrderById(id);
	        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order newOrder) {
	        boolean updated = orderService.updateOrder(id, newOrder);
	        if (updated) {
	            return new ResponseEntity<>(newOrder, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @DeleteMapping(value = "/{id}", produces = "application/json")
	    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
	        boolean deleted = orderService.deleteOrder(id);
	        if (deleted) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        
	    }
//
//	    @GetMapping("/health")
//	    public String healthCheck() {
//	        return "UP";
//	    }
//
//
//	    @GetMapping("/welcome")
//	    public String greetings() {
//	        return "Hello Techie , AWS CICD Example working fine !";
//	    }


	}


