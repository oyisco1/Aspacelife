package com.aspacelife.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private String id = UUID.randomUUID().toString();
    private Users user;// this create a relationship witth USER
    private Space space;// this create a relationship witth Space
    private LocalDateTime startTime;
    private  LocalDateTime endTime;
    private String status; //this can be either Confirmed, Cancelled, Pending

}
