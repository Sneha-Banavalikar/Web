package com.redbus.operator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.redbus.operator.util.CustomDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.time.LocalTime;

@Entity
@Table(name="bus_operators")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BusOperator {
    @Id
    private String busId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "busOperator")
    private TicketCost ticketCost;

    @Column(name="bus_number")
    private String busNumber;

    @Column(name="bus_operator_company_name")
    private String busOperatorCompanyName;

    @Column(name="driver_name")
    private String driverName;

    @Column(name="support_staff")
    private String supportStaff;

    @Column(name="number_seats")
    private int numberSeats;

    @Column(name="departure_city")
    private String departureCity;

    @Column(name="arrival_city")
    private String arrivalCity;

    @Column(name="departure_time")
    private LocalTime departureTime;

    @Column(name="arrival_time")
    private LocalTime arrivalTime;


    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "departure_date")
    private Date departureDate;


    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name="total_travel_time")
    private double totalTravelTime;

    @Column(name="bus_Type")
    private String busType;

    @Column(name="amenities")
    private String amenities;

}
