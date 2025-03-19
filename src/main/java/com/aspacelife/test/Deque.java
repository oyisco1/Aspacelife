package com.aspacelife.test;

import io.swagger.models.auth.In;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

public class Deque<T> {
    private final LinkedList<T> deque;

    public Deque() {
        this.deque = new  LinkedList<>();
    }



    //insert at front
    public void insertFront(T value){
        deque.addFirst(value);
    }
    //insert at rear
    public void insertRear(T value){
        deque.addLast(value);
    }
    // delete from the front
    public T  deleteFront(){
        if(deque.isEmpty()){
            throw new RuntimeException("Deque is empty");
        }
        return deque.removeFirst();
    }
    // delete from the front
    public T  deleteRear(){
        if(deque.isEmpty()){
            throw new RuntimeException("Deque is empty");
        }
        return deque.removeLast();
    }
    //get the  getFirst
    public T  getFirst(){
        if(deque.isEmpty()){
            throw new RuntimeException("Deque is empty");
        }
        return deque.getFirst();
    }
    //get the  getLast
    public T  getLast(){
        if(deque.isEmpty()){
            throw new RuntimeException("Deque is empty");
        }
        return deque.getLast();
    }

    //get the  size
    public int  size(){
        return deque.size();
    }

    @PostConstruct
    public void runDeque(){
        Deque<Integer> deque = new Deque<>();
        deque.insertFront(10);
        deque.insertRear(20);
        deque.insertFront(5);
        deque.insertRear(30);
        System.out.println("First element " + deque.getFirst());// this will output 5
        System.out.println("Last element " + deque.getFirst());// this will output 30
        System.out.println("Size " + deque.getFirst());// this will output 4

        System.out.println("delete front element " + deque.deleteFront());// this will output 5
        System.out.println("delete rear element " + deque.deleteRear());// this will output 30
        System.out.println("Size after deletion " + deque.size());// this will output 2

    }

}
