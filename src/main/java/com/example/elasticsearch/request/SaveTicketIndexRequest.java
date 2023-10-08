package com.example.elasticsearch.request;

import com.example.elasticsearch.dto.TicketDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTicketIndexRequest {

    private TicketDto ticketDto;
}
