package com.aspacelife.test.controller;

import com.aspacelife.test.model.Booking;
import com.aspacelife.test.model.Space;
import com.aspacelife.test.model.Users;
import com.aspacelife.test.service.BookingService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class BookingController {
    //do dependency injection using constructor
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @GetMapping("/users")
    public Flux<Users> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue =  "10") int size){
        return  this.bookingService.getAllUsers().skip((long) page * size).take(size);
    }
    @GetMapping("/spaces")
    public Flux<Space> getSpaces(@RequestParam(required =  false , defaultValue = "false") boolean available ,@RequestParam(defaultValue = "0")
            int page, @RequestParam(defaultValue =  "10") int size){
        return  this.bookingService.getAllSpace(available).skip((long) page * size).take(size);
    }
    //get booking
    @GetMapping("/bookings")
    public Flux<Booking> getAllBooking(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate,
    @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue =  "10") int size){
        return  this.bookingService.getAllBooking(startDate, endDate, startDate !=null && endDate !=null).skip((long) page * size).take(size);
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
    public Flux<Booking> getAllBookingByUser(@PathVariable String id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue =  "10") int size){
        return  this.bookingService.getAllBookingByUser(id).skip((long) page * size).take(size);
    }
    @GetMapping("/spaces/{id}")
    public Flux<Booking> getBookingBySpaceId(@PathVariable String id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue =  "10") int size){
        return  this.bookingService.getAllBookingBySpace(id).skip((long) page * size).take(size);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBooking(@PathVariable String id){
        return  this.bookingService.deleteBooking(id);
    }


}
