package com.example.elasticsearch.repository;

import com.example.elasticsearch.document.Ticket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends ElasticsearchRepository<Ticket, String> {

    List<Ticket> findByPassengerNameContaining(String name);

}
