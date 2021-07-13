package com.example.tdd.service;

import java.util.List;

import com.example.tdd.model.BookingModel;

import javassist.NotFoundException;

public interface BookingService {
	
	public int daysCalculator(String name);
	
	public List<BookingModel> getAll();
	
	public BookingModel save(BookingModel bookingModel);
	
	public BookingModel update(String bookingId, BookingModel bookingModel) throws NotFoundException;
	
	public Boolean delete(String bookingId) throws NotFoundException;
	
}
