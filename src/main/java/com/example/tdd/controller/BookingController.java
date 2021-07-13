package com.example.tdd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tdd.model.BookingModel;
import com.example.tdd.service.BookingService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping
	public ResponseEntity<List<BookingModel>> getAll() {
		List<BookingModel> bookings = bookingService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(bookings);
	}
	
	@PostMapping
	public ResponseEntity<BookingModel> save(@RequestBody BookingModel bookingModel) {
		BookingModel booking = bookingService.save(bookingModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(booking);
	}
	
	@PutMapping("/{bookingId}")
	public ResponseEntity<Object> update(@PathVariable String bookingId, @RequestBody BookingModel bookingModel) throws NotFoundException {
		bookingService.update(bookingId, bookingModel);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<Object> delete(@PathVariable String bookingId) throws NotFoundException {
		bookingService.delete(bookingId);
		return ResponseEntity.noContent().build();
	}
	
}
