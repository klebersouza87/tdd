package com.example.tdd.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tdd.model.BookingModel;
import com.example.tdd.repository.BookingRepository;
import com.example.tdd.service.BookingService;

import javassist.NotFoundException;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Override
	public int daysCalculator(String name) {
		Optional<BookingModel> optionalBookingModel = bookingRepository.findByReserveName(name);
		LocalDate checkIn = optionalBookingModel.map(model -> model.getCheckIn()).orElse(LocalDate.now());
		LocalDate checkOut = optionalBookingModel.map(model -> model.getCheckOut()).orElse(LocalDate.now());
		
		return Period.between(checkIn, checkOut).getDays();
	}
	
	@Override
	public List<BookingModel> getAll() {
		return bookingRepository.findAll();
	}

	@Override
	public BookingModel save(BookingModel bookingModel) {
		return bookingRepository.saveAndFlush(bookingModel);
	}

	@Override
	public BookingModel update(String bookingId, BookingModel bookingModel) throws NotFoundException {
		Optional<BookingModel> recoveredBookingModel = bookingRepository.findById(bookingId);
		if (recoveredBookingModel.isPresent()) {
			BookingModel model = converter(recoveredBookingModel.get(), bookingModel);
			return bookingRepository.saveAndFlush(model);
		} else {
			throw new NotFoundException("Booking with this id: " + bookingId + ", was not found!");
		}
	}
	
	@Override
	public Boolean delete(String bookingId) throws NotFoundException {
		Optional<BookingModel> bookingModel = bookingRepository.findById(bookingId);
		if (bookingModel.isPresent()) {
			bookingRepository.deleteById(bookingId);
			return Boolean.TRUE;
		} else {
			throw new NotFoundException("Booking with this id: " + bookingId + ", was not found!");
		}
	}

	private BookingModel converter(BookingModel recoveredBookingModel, BookingModel newBookingModel) {
		recoveredBookingModel.setCheckIn(newBookingModel.getCheckIn());
		recoveredBookingModel.setCheckOut(newBookingModel.getCheckOut());
		recoveredBookingModel.setReserveName(newBookingModel.getReserveName());
		recoveredBookingModel.setNumberGuests(newBookingModel.getNumberGuests());
		
		return recoveredBookingModel;
	}

}
