package com.redbus.operator.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.entity.TicketCost;
import com.redbus.operator.payload.BusOperatorDto;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.operator.repository.TicketCostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BusOperatorServiceImpl implements BusOperatorService {

    @Autowired
    private BusOperatorRepository busOperatorRepository;

    @Autowired
    private TicketCostRepository ticketCostRepository;

    @Autowired
    private ModelMapper mapper;

    public BusOperatorServiceImpl(BusOperatorRepository busOperatorRepository, TicketCostRepository ticketCostRepository, ModelMapper mapper) {
        this.busOperatorRepository = busOperatorRepository;
        this.ticketCostRepository = ticketCostRepository;
        this.mapper = mapper;
    }

    @Override
    public BusOperatorDto scheduleBus(BusOperatorDto busOperatorDto) {
        // Map BusOperatorDto to BusOperator entity
        BusOperator busOperator = mapToEntity(busOperatorDto);

        // Generate a unique busId using UUID
        String busId = UUID.randomUUID().toString();
        busOperator.setBusId(busId);

        // Create TicketCost instance
        TicketCost ticketCost = new TicketCost();
        ticketCost.setTicketId(busOperatorDto.getTicketCost().getTicketId());
        ticketCost.setCost(busOperatorDto.getTicketCost().getCost());
        ticketCost.setCode(busOperatorDto.getTicketCost().getCode());
        ticketCost.setDiscountAmount(busOperatorDto.getTicketCost().getDiscountAmount());
        ticketCost.setBusOperator(busOperator);

        // Set TicketCost to BusOperator
        busOperator.setTicketCost(ticketCost);

        // Save BusOperator entity to the repository
        BusOperator savedBusOperator = busOperatorRepository.save(busOperator);

        // Map the saved BusOperator entity back to DTO and return
        return mapToDto(savedBusOperator);
    }

    private BusOperator mapToEntity(BusOperatorDto busOperatorDto) {
        return mapper.map(busOperatorDto, BusOperator.class);
    }

    private BusOperatorDto mapToDto(BusOperator busOperator) {
        return mapper.map(busOperator, BusOperatorDto.class);
    }
}
