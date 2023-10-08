package com.example.elasticsearch.reponse;

import com.example.elasticsearch.dto.TicketDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindByNameContainingResponse {
    List<TicketDto> tickets;


}
