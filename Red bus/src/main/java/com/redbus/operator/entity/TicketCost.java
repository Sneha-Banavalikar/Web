package com.redbus.operator.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_cost")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "ticket_id", unique = true)
    private String ticketId;

    private double cost;

    private String code;

    @Column(name = "discount_amount", unique = true)
    private double discountAmount;

    @OneToOne
    @JoinColumn(name = "bus_id", unique = true)
    private BusOperator busOperator;

    // Ensure proper setters and getters for fields
}
