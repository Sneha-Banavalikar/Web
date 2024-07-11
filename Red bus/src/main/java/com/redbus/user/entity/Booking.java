package com.redbus.user.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    private String bookingId;

    @Column(name="bus_id")
    private String busId;

    @Column(name="ticket_id")
    private String ticketId;

    @Column(name="to_city")
    private String toCity;

    @Column(name="from_city")
    private String fromCity;

    @Column(name="bus_company")
    private String busCompany;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="mobile")
    private String mobile;

    @Column(name="email")
    private String email;

    @Column(name="price")
    private double price;
}
