package com.example.tdd;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import com.example.tdd.model.BookingModel;
import com.example.tdd.repository.BookingRepository;
import com.example.tdd.service.BookingService;
import com.example.tdd.service.impl.BookingServiceImpl;

//@RunWith(SpringRunner.class) //use with JUnit 4.
@SpringBootTest //This annotation includes @ExtendWith. Use with JUnit 5.
public class BookingServiceImplTest {
	
	@TestConfiguration
	static class BookingServiceImplTestConfiguration {		
		@Bean
		public BookingService bookingService() {
			return new BookingServiceImpl();
		}
	}
	
	@Autowired
	BookingService bookingService;
	
	@MockBean
	BookingRepository bookingRepository;
	
	@BeforeEach
	public void setup() {
		LocalDate checkIn = LocalDate.parse("2021-06-02");
		LocalDate checkOut = LocalDate.parse("2021-06-12");
		BookingModel bookingModel = new BookingModel("1", "Kleber", checkIn, checkOut, 3);
		
		Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName()))
			.thenReturn(Optional.of(bookingModel));
	}
	
	@Test
	public void bookingTestServiceDaysCalculator() {
		String name = "Kleber";
		int days = bookingService.daysCalculator(name);
		
		Assertions.assertEquals(10, days);
	}
	
}
