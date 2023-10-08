package com.example.elasticsearch.request;

import com.example.elasticsearch.dto.TicketDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveTicketBulkIndexRequest {

    List<TicketDto> tickets;
}
