package com.aspacelife.test.controller;

import com.aspacelife.test.model.Booking;
import com.aspacelife.test.model.Space;
import com.aspacelife.test.model.Users;
import com.aspacelife.test.service.BookingService;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

@RestController
@RequestMapping("/api")
public class BookingController {
    //do dependency injection using constructor
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping("/users")
    public Flux<Users> getAllUsers(){
        return  this.bookingService.getAllUsers();
    }
    @GetMapping("/spaces")
    public Flux<Space> getSpaces(@RequestParam(required =  false , defaultValue = "false") boolean available){
        return  this.bookingService.getAllSpace(available);
    }


    //get booking
    @GetMapping(value = "/bookings", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Booking> getAllBookings(@RequestParam(required = false) LocalDate startDate,
                                        @RequestParam(required = false) LocalDate endDate
                                       ) {
        return bookingService.getAllBooking(startDate, endDate, startDate !=null && endDate !=null)
                .sort((Comparator<? super Booking>) Sort.by(Sort.Direction.DESC, "date"));
    }

    @PostMapping
    public Mono<Booking> createBooking(@RequestBody Booking  booking){
        return  this.bookingService.createBooking(booking);
    }
    @GetMapping("/bookings/{id}")
    public Mono<Booking> updateBooking(@PathVariable String id, @RequestParam String status){
        return  this.bookingService.updateBooking(id, status);
    }
    @GetMapping("/users/{id}")
    public Flux<Booking> getAllBookingByUser(@PathVariable String id){
        return  this.bookingService.getAllBookingByUser(id);
    }
    @GetMapping("/spaces/{id}")
    public Flux<Booking> getBookingBySpaceId(@PathVariable String id){
        return  this.bookingService.getAllBookingBySpace(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBooking(@PathVariable String id){
        return  this.bookingService.deleteBooking(id);
    }


}
