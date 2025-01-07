package com.example.PrideOfCows.repository;



import com.example.PrideOfCows.model.Subscription;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("SELECT s FROM Subscription s WHERE s.customerId = :customerId")
    List<Subscription> findByCustomerId(@Param("customerId") Long customerId);

}
