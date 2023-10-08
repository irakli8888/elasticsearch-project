package com.example.elasticsearch.controller;

import com.example.elasticsearch.document.Ticket;
import com.example.elasticsearch.reponse.FindByNameContainingResponse;
import com.example.elasticsearch.request.SaveTicketBulkIndexRequest;
import com.example.elasticsearch.request.SaveTicketIndexRequest;
import com.example.elasticsearch.service.TicketServiceWithNativeQuery;
import com.example.elasticsearch.service.TicketServiceWithRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketServiceWithRepo ticketServiceWithRepo;
    private final TicketServiceWithNativeQuery ticketServiceWithNativeQuery;

    @PostMapping("/repo")
    public ResponseEntity saveIndexWithRepo(@RequestBody SaveTicketIndexRequest saveTicketIndexRequest) {
        ticketServiceWithRepo.saveIndex(saveTicketIndexRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/repo/bulk")
    public ResponseEntity saveIndexWithRepo(@RequestBody SaveTicketBulkIndexRequest saveTicketBulkIndexRequest) {
        ticketServiceWithRepo.saveBulkIndex(saveTicketBulkIndexRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/repo")
    public ResponseEntity<FindByNameContainingResponse> findByNameContainingWithRepo
            (@RequestParam("name") String name) {
        FindByNameContainingResponse findByNameContainingResponse =
                ticketServiceWithRepo.findByNameContaining(name);
        return ResponseEntity.ok().body(findByNameContainingResponse);
    }

    @GetMapping("/native")
    @ResponseBody
    public List<Ticket> fetchByNameOrDesc(@RequestParam(value = "q", required = false)
                                                      String query) {
        List<Ticket> tickets = ticketServiceWithNativeQuery.processSearch(query) ;
        return tickets;
    }

    @GetMapping("/native/suggestions")
    @ResponseBody
    public List<String> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {
        List<String> suggests = ticketServiceWithNativeQuery.fetchSuggestions(query);
        return suggests;
    }

}
