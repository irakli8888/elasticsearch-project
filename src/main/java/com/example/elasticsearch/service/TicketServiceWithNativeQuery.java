package com.example.elasticsearch.service;

import com.example.elasticsearch.document.Ticket;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceWithNativeQuery {

    private static final String TICKET_INDEX = "tickets";
    private final ElasticsearchOperations elasticsearchOperations;

    public List<Ticket> processSearch(String passengerName) {
        QueryBuilder queryBuilder =
                QueryBuilders
                        .matchQuery("passenger_name", passengerName)
                        .fuzziness(Fuzziness.AUTO);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();

        SearchHits<Ticket> productHits =
                elasticsearchOperations
                        .search(searchQuery,
                                Ticket.class,
                                IndexCoordinates.of(TICKET_INDEX));
        List<Ticket> ticketMatches = new ArrayList<>();
        productHits.forEach(searchHit -> {
            ticketMatches.add(searchHit.getContent());
        });
        return ticketMatches;
    }

    public List<String> fetchSuggestions(String query) {
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("passenger_name", query+"*");

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();

        SearchHits<Ticket> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        Ticket.class,
                        IndexCoordinates.of(TICKET_INDEX));

        List<String> suggestions = new ArrayList<>();

        searchSuggestions.getSearchHits().forEach(searchHit->{
            suggestions.add(searchHit.getContent().getPassengerName());
        });
        return suggestions;
    }
}
