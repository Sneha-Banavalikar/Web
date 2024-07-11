package com.redbus.operator.repository;

import com.redbus.operator.entity.TicketCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketCostRepository extends JpaRepository<TicketCost, String> {
}

