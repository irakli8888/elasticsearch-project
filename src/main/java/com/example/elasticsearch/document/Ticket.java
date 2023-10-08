package com.example.elasticsearch.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "tickets")
@ToString
public class Ticket {
    @Id
    private String ticket_no;
    @Field(type = FieldType.Text, name = "book_ref")
    private String bookRef;
    @Field(type = FieldType.Text, name = "passenger_id")
    private String passengerId;
    @Field(type = FieldType.Text, name = "passenger_name")
    private String passengerName;
}
