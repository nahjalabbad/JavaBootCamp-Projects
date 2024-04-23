package com.example.project3.Repository;

import com.example.project3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepostitory extends JpaRepository<Customer, Integer> {
Customer findCustomerByCustomerId(Integer customerId);
}
