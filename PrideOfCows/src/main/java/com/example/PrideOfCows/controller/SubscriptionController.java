package com.example.PrideOfCows.controller;


import com.example.PrideOfCows.model.Subscription;
import com.example.PrideOfCows.service.SubscriptionService;

//import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscription));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(
            @PathVariable Long id, @RequestBody Subscription subscription) {
        return ResponseEntity.ok(subscriptionService.updateSubscription(id, subscription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.ok("Subscription deleted successfully.");
    }

    @GetMapping("/customer/{customerId}")
public ResponseEntity<?> getSubscriptionsByCustomerId(@PathVariable Long customerId) {
    List<Subscription> subscriptions = subscriptionService.getSubscriptionsByCustomerId(customerId);
    return ResponseEntity.ok(Map.of(
        "customer_id", customerId,
        "subscriptions", subscriptions
    ));
}

}

