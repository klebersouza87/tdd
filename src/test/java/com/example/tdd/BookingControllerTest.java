package com.example.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.tdd.model.BookingModel;
import com.example.tdd.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	BookingRepository bookingRepository;
	
	private BookingModel bookingModel;
	
	@BeforeEach
	public void setup() {
		LocalDate checkIn = LocalDate.parse("2021-03-11");
		LocalDate checkOut = LocalDate.parse("2021-03-21");		
		bookingModel =  new BookingModel("1", "Kleber", checkIn, checkOut, 3);
		
		List<BookingModel> bookings = new ArrayList<>();
		bookings.add(bookingModel);
		
		Mockito.when(bookingRepository.saveAndFlush(bookingModel)).thenReturn(bookingModel);
		Mockito.when(bookingRepository.findAll()).thenReturn(bookings);
		Mockito.when(bookingRepository.findById("1")).thenReturn(Optional.of(bookingModel));
	}
	
	@Test
	public void bookingTestGetAll() throws Exception {
		mockMvc.perform(get("/bookings"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void bookingTestSave() throws Exception {
		mockMvc.perform(post("/bookings")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(bookingModel)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void bookingTestUpdate() throws Exception {
		mockMvc.perform(put("/bookings/1")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(bookingModel)))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void bookingTestDelete() throws Exception {
		mockMvc.perform(delete("/bookings/1"))
				.andExpect(status().isNoContent());
	}
	
}
