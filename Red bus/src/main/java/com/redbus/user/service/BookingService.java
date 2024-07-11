package com.redbus.user.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.entity.TicketCost;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.operator.repository.TicketCostRepository;
import com.redbus.user.entity.Booking;
import com.redbus.user.payload.BookingDetailsDto;
import com.redbus.user.payload.PassengerDetails;
import com.redbus.user.repository.BookingRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BookingService {
    @Value("${stripe.secretKey}")
    private String stripeSecretKey;

    private final BusOperatorRepository busOperatorRepository;
    private final TicketCostRepository ticketCostRepository;
    private final BookingRepository bookingRepository;

    public BookingService(BusOperatorRepository busOperatorRepository, TicketCostRepository ticketCostRepository, BookingRepository bookingRepository) {
        this.busOperatorRepository = busOperatorRepository;
        this.ticketCostRepository = ticketCostRepository;
        this.bookingRepository = bookingRepository;
    }

    public BookingDetailsDto createBooking(
            String busId,
            String ticketId,
            PassengerDetails passengerDetails
    ) {
        BusOperator bus = busOperatorRepository.findById(busId).get();

        TicketCost ticketCost = ticketCostRepository.findById(ticketId).get();

        String chargeIntent = String.valueOf(charge(ticketCost.getCost()));
        if(chargeIntent!=null) {

            Booking booking = new Booking();
            String bookingId = UUID.randomUUID().toString();
            booking.setBookingId(bookingId);
            booking.setBusId(busId);
            booking.setTicketId(ticketId);
            booking.setToCity(bus.getArrivalCity());
            booking.setFromCity(bus.getDepartureCity());
            booking.setBusCompany(bus.getBusOperatorCompanyName());
            booking.setPrice(ticketCost.getCost());
            booking.setFirstName(passengerDetails.getFirstName());
            booking.setLastName(passengerDetails.getLastName());
            booking.setEmail(passengerDetails.getEmail());
            booking.setMobile(passengerDetails.getMobile());

            Booking ticketCreatedDetails = bookingRepository.save(booking);

            BookingDetailsDto dto = new BookingDetailsDto();
            dto.setBookingId(ticketCreatedDetails.getBookingId());
            dto.setFirstName(ticketCreatedDetails.getFirstName());
            dto.setLastName(ticketCreatedDetails.getLastName());
            dto.setPrice(ticketCreatedDetails.getPrice());
            dto.setEmail(ticketCreatedDetails.getEmail());
            dto.setMobile(ticketCreatedDetails.getMobile());
            dto.setBusCompany(ticketCreatedDetails.getBusCompany());
            dto.setFrom(ticketCreatedDetails.getFromCity());
            dto.setTo(ticketCreatedDetails.getToCity());
            dto.setMessage("Booking Confirmed");

            return dto;
        }
        else {
            System.out.println("Error!!");
        }
        return null;
    }

    public boolean charge(double amount) {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "usd");
        params.put("description", "Payment for order #123");

        try {
            Charge charge = Charge.create(params);
            // Payment successful
            return true;
        } catch (StripeException e) {
            // Payment failed
            e.printStackTrace();  // Handle or log the exception appropriately
            return false;
        }
    }


}


