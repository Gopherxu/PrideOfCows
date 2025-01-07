package com.example.PrideOfCows.service;


import com.example.PrideOfCows.exception.InvalidDataException;
import com.example.PrideOfCows.exception.SubscriptionNotFoundException;
import com.example.PrideOfCows.model.Subscription;
import com.example.PrideOfCows.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {
        validateSubscription(subscription);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found with ID: " + id));
    }
    public List<Subscription> getSubscriptionsByCustomerId(Long customerId) {
        return subscriptionRepository.findByCustomerId(customerId);
    }
    

    public Subscription updateSubscription(Long id, Subscription updatedSubscription) {
        Subscription existing = getSubscriptionById(id);
        validateSubscription(updatedSubscription);
        existing.setFrequency(updatedSubscription.getFrequency());
        existing.setQuantity(updatedSubscription.getQuantity());
        existing.setPricePerUnit(updatedSubscription.getPricePerUnit());
        existing.setStartDate(updatedSubscription.getStartDate());
        existing.setEndDate(updatedSubscription.getEndDate());
        return subscriptionRepository.save(existing);
    }

    public void deleteSubscription(Long id) {
        Subscription subscription = getSubscriptionById(id);
        subscriptionRepository.delete(subscription);
    }

    private void validateSubscription(Subscription subscription) {
        if (!(subscription.getFrequency().equals("daily") || subscription.getFrequency().equals("weekly"))) {
            throw new InvalidDataException("Frequency must be either 'daily' or 'weekly'.");
        }
        if (subscription.getQuantity() <= 0) {
            throw new InvalidDataException("Quantity must be a positive integer.");
        }
        if (subscription.getStartDate().isBefore(LocalDate.now())) {
            throw new InvalidDataException("Start date must be a future date.");
        }
    }
}
