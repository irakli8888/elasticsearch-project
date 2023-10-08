package com.example.elasticsearch.dto.mapper;

import com.example.elasticsearch.document.Ticket;
import com.example.elasticsearch.dto.TicketDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketMapper {

    @Mapping(source = "ticket_no", target = "ticket_no")
    @Mapping(source = "bookRef", target = "bookRef")
    @Mapping(source = "passengerId", target = "passengerId")
    @Mapping(source = "passengerName", target = "passengerName")
    TicketDto ticketToTicketDto(Ticket ticket);

    @Mapping(source = "ticket_no", target = "ticket_no")
    @Mapping(source = "bookRef", target = "bookRef")
    @Mapping(source = "passengerId", target = "passengerId")
    @Mapping(source = "passengerName", target = "passengerName")
    Ticket ticketDtoToTicket(TicketDto ticketDto);
}
