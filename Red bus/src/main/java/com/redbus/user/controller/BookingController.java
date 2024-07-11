package com.redbus.user.controller;

import com.itextpdf.text.DocumentException;
import com.redbus.user.payload.BookingDetailsDto;
import com.redbus.user.payload.PassengerDetails;
import com.redbus.user.service.BookingService;
import com.redbus.util.EmailService;
import com.redbus.util.PdfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final EmailService emailService;
    private final PdfService pdfService;

    public BookingController(BookingService bookingService, EmailService emailService, PdfService pdfService) {
        this.bookingService = bookingService;
        this.emailService = emailService;
        this.pdfService = pdfService;
    }

    @PostMapping
    public ResponseEntity<BookingDetailsDto> bookBus(
            @RequestParam("busId") String busId,
            @RequestParam("ticketId") String ticketId,
            @RequestBody PassengerDetails passengerDetails
    ) {
        BookingDetailsDto booking = bookingService.createBooking(busId, ticketId, passengerDetails);
        if (booking != null) {
            try {
                // Generate PDF
                byte[] pdfContent = pdfService.generatePdf(booking);

                // Send email with PDF attachment
                emailService.sendEmailWithAttachment(
                        passengerDetails.getEmail(),
                        "Booking Confirmed Id" + booking.getBookingId(),
                        "Your Booking is Confirmed \n Name: " + passengerDetails.getFirstName() + " " + passengerDetails.getLastName(),
                        "BookingDetails.pdf",
                        pdfContent
                );
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}
