package com.example.TechnicalAssignment;

import com.example.TechnicalAssignment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderDB extends JpaRepository<Order, Long>{

}