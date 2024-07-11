package com.redbus.user.controller;

import com.redbus.user.payload.BusListDto;
import com.redbus.user.service.SearchBusesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class FindBusesController {
    private SearchBusesService searchBusesService;

    public FindBusesController(SearchBusesService searchBusesService) {
        this.searchBusesService = searchBusesService;
    }

    //http://localhost:8080/api/user/searchBuses?departureCity=CityA&arrivalCity=CityB&departureDate=15-12-2023
    @GetMapping("/searchBuses")
    public List<BusListDto> searchBuses(
            @RequestParam("departureCity") String departureCity,
            @RequestParam("arrivalCity") String arrivalCity,
            @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate
    ) {
        // Add some logging to trace the flow
        System.out.println("Searching for buses...");
        System.out.println("Departure City: " + departureCity);
        System.out.println("Arrival City: " + arrivalCity);
        System.out.println("Departure Date: " + departureDate);

        List<BusListDto> busListDtos = searchBusesService.searchBusBy(departureCity, arrivalCity, departureDate);

        // Log the results
        System.out.println("Found " + busListDtos.size() + " buses.");

        return busListDtos;
    }


}


