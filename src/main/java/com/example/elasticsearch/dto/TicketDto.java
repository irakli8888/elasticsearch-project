package com.example.elasticsearch.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
    private String ticket_no;
    private String bookRef;
    private String passengerId;
    private String passengerName;
}
