package com.aspacelife.test.repositories;


import com.aspacelife.test.model.Booking;
import com.aspacelife.test.model.Space;
import com.aspacelife.test.model.Users;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//using hashMap desing repository
@Repository
public class BookingRepository {
    //implementing project reactor
    private   final Map<String, Booking> bookings=  new ConcurrentHashMap<>();
    private static  final Map<String, Users> users=  new ConcurrentHashMap<>();
    private static  final Map<String, Space> spaces=  new ConcurrentHashMap<>();
    //using Flux adding pagination
    public Flux<Users> getAllUsers(){
        return  Flux.fromIterable(users.values());
    }
    public Flux<Space> getAllSpace(boolean filterAvailable ){
        return  Flux.fromIterable(spaces.values()).filter(space -> !filterAvailable ||
                space.isAvailable());
    }
//filtering booking by date range
    public Flux<Booking> getAllBookings(LocalDateTime startDate, LocalDateTime endDate, boolean filterByDateRange){
        return  Flux.fromIterable(bookings.values()).sort((Comparator.comparing(Booking::getStartTime).reversed())).
                filter(booking -> ! filterByDateRange || (booking.getStartTime().isAfter(startDate) && booking.getEndTime().isBefore(endDate)));


    }
    // save method using mono
    public Mono<Booking> save(Booking booking){
        bookings.put(booking.getId(), booking);
        return Mono.just(booking);

    }
 //updating booking by id
    public Mono<Booking> updateBooking(String bookingId, String status){
      Booking booking = bookings.get(bookingId);
      if(booking!= null){
          booking.setStatus(status);
          return Mono.just(booking);
      }
      return Mono.empty();

    }
    public Flux<Booking> getAllBookingByUser(String userId ){
        return  Flux.fromStream(bookings.values().stream().filter(booking -> booking.getUser().getId().equals(userId)));


    }
    public Flux<Booking> getAllBookingBySpace(String spaceId){
        return  Flux.fromStream(bookings.values().stream().filter(booking -> booking.getUser().getId().equals(spaceId)));


    }


    public Mono<Void> delete(String id){
        bookings.remove(id);
        return Mono.empty();

    }


}
