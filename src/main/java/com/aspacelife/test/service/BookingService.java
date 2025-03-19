package com.aspacelife.test.service;

import com.aspacelife.test.model.Booking;
import com.aspacelife.test.model.Space;
import com.aspacelife.test.model.Users;
import com.aspacelife.test.repositories.BookingRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class BookingService {
    //do dependency injection using constructor
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    public Flux<Users> getAllUsers(){
        return this.bookingRepository.getAllUsers();
    }

    public Flux<Space> getAllSpace(boolean filterAvailable){
        return  this.bookingRepository.getAllSpace(filterAvailable);
    }
    //get all booking using flux
    public Flux<Booking> getAllBooking(LocalDateTime startDate, LocalDateTime endDate, boolean filterByDateRange){
        return  this.bookingRepository.getAllBookings(startDate,endDate,filterByDateRange);
    }
    public Flux<Booking> getAllBookingByUser(String userId){
        return  this.bookingRepository.getAllBookingByUser(userId);
    }
    public Flux<Booking> getAllBookingBySpace(String spaceId){
        return  this.bookingRepository.getAllBookingBySpace(spaceId);
    }
//create function
    public Mono<Booking> createBooking(Booking booking){
        return  this.bookingRepository.save(booking);
    }
    public  Mono<Booking>  updateBooking(String bookingId, String status){
      return  this.bookingRepository.updateBooking(bookingId, status);
    }

    public Mono<Void> deleteBooking(String id){
        return  this.bookingRepository.delete(id);
    }
}
