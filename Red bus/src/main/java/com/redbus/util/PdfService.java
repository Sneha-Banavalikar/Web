package com.redbus.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.redbus.user.payload.BookingDetailsDto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generatePdf(BookingDetailsDto bookingDetails) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

        document.open();

        addContent(document, bookingDetails);

        document.close();

        return outputStream.toByteArray();
    }

    private void addContent(Document document, BookingDetailsDto bookingDetails) throws DocumentException {
        document.add(new Paragraph("Booking Details"));
        document.add(new Paragraph("Booking ID: " + bookingDetails.getBookingId()));
        document.add(new Paragraph("From: " + bookingDetails.getFrom()));
        document.add(new Paragraph("To: " + bookingDetails.getTo()));
        document.add(new Paragraph("Bus Company: " + bookingDetails.getBusCompany()));
        document.add(new Paragraph("First Name: " + bookingDetails.getFirstName()));
        document.add(new Paragraph("Last Name: " + bookingDetails.getLastName()));
        document.add(new Paragraph("Email: " + bookingDetails.getEmail()));
        document.add(new Paragraph("Mobile: " + bookingDetails.getMobile()));
        document.add(new Paragraph("Price: " + bookingDetails.getPrice()));

        // You can add more details as needed
    }
}
