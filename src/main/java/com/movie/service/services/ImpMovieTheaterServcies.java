package com.movie.service.services;

import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.AttributeNotFoundException;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movie.service.dto.BookingIdDto;
import com.movie.service.dto.GetPrice;
import com.movie.service.dto.MovieDto;
import com.movie.service.dto.MovieTheaterdto;
import com.movie.service.dto.TheaterDto;
import com.movie.service.entities.Cast;
import com.movie.service.entities.Movie;
import com.movie.service.entities.Theater;
import com.movie.service.exception.BussinessException;
import com.movie.service.repository.MovieRepository;
import com.movie.service.repository.TheaterRepository;

@Service
public class ImpMovieTheaterServcies implements MovieTheaterServices {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private MovieRepository movierepo;

	@Autowired
	private TheaterRepository theaterrepo;

	private MovieDto convertEntitytoDto(Movie movie) {
		MovieDto moviedto = new MovieDto();
		moviedto = modelmapper.map(movie, moviedto.getClass());
		return moviedto;
	}

	private Movie convertDtoToEntity(MovieDto moviedto) {
		Movie movie = new Movie();
		movie = modelmapper.map(moviedto, movie.getClass());
		return movie;
	}

	private TheaterDto convertTheaterEntitytoDto(Theater theater) {
		TheaterDto theaterdto = new TheaterDto();
		theaterdto = modelmapper.map(theater, theaterdto.getClass());
		return theaterdto;
	}

	private Theater convertTheaterDtoToEntity(TheaterDto theaterdto) {
		Theater theater = new Theater();
		theater = modelmapper.map(theaterdto, theater.getClass());
		return theater;
	}

	public void saveMovie(Movie movie) {
		movierepo.save(movie);
	}

	@Override
	public String saveMovieDetails(MovieDto moviedto) {
		Movie movie = convertDtoToEntity(moviedto);
		if (movie.getMoviename().isEmpty()) {
			throw new NullPointerException("Name should not be empty,please enter a name");
		}
		if (movie.getType().isEmpty()) {
			throw new NullPointerException("Name should not be empty, please enter a name");
		}
		try {
			movierepo.save(movie);
			return "saved";
		} catch (IllegalArgumentException e) {
			throw new BussinessException("602", "Given moviename is null" + e.getMessage());
		} catch (Exception e) {
			throw new BussinessException("603", "Someting went wrong in Service Layer" + e.getMessage());
		}

	}

	public List<MovieDto> getAllMovies() {

		List<MovieDto> movieDto = null;
		try {
			movieDto = movierepo.findAll().stream().map(this::convertEntitytoDto).collect(Collectors.toList());
		} catch (Exception e) {
			throw new BussinessException("605",
					"Something went wrong in the service layer while fetching details" + e.getMessage());
		}
		if (movieDto.isEmpty()) {
			throw new BussinessException("604", "Here The list is completely empty");
		}
		return movieDto;
	}

	@Override
	public String saveTheaterDetails(TheaterDto theaterdto) {
		Theater theater = convertTheaterDtoToEntity(theaterdto);
		if (theater.getTheatername().isEmpty()) {
			throw new NullPointerException("Name should not be empty,please give a name");
		}
		if (theater.getPrice() == 0) {
			throw new NullPointerException("price is null,please enter some value to it");
		}
		theaterrepo.save(theater);
		return "saved Theater details";
	}

	public List<TheaterDto> getAllTheaters() {
		List<TheaterDto> theaterDto = null;
		try {
			theaterDto = theaterrepo.findAll().stream().map(this::convertTheaterEntitytoDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new BussinessException("612", "Something went wrong in the service layer");
		}
		if (theaterDto.isEmpty()) {
			throw new BussinessException("613", "Here No data in the database");
		}
		return theaterDto;

	}

	public String assignMovietoTheater(String moviename, String theatername) {

		List<Movie> setmovies = new ArrayList<>();

		Theater theater = theaterrepo.getBytheatername(theatername);
		Movie movie = movierepo.getBymoviename(moviename);

		setmovies = theater.getAssignedmovies();
		setmovies.add(movie);
		theater.setAssignedmovies(setmovies);
		theaterrepo.save(theater);
		return "Assigned Movies to Theaters";
	}

	@Override
	public String deleteMovie(String moviename) {

		if (movierepo.deleteBymoviename(moviename)) {
			return moviename + "deleted";
		} else {
			throw new NoSuchElementException("No such name is present ib db");
		}

	}

	@Override
	public List<TheaterDto> deleteTheater(String theatername) {

		try {
			theaterrepo.deleteBytheatername(theatername);
			return getAllTheaters();
		} catch (Exception e) {
			throw new BussinessException("613", "Something went wrong in the service layer" + e.getMessage());
		}
	}

	public MovieDto findbymoviename(String moviename) {
		// Movie movie = new Movie();
		try {
			Movie movie = movierepo.findBymoviename(moviename);
			MovieDto movieDto = convertEntitytoDto(movie);
			return movieDto;
		} catch (NoSuchElementException e) {
			throw new BussinessException("608", "Given wrong input,Please give the correct moviename" + e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new BussinessException("606",
					"Given moviename is null,please give some id to be searched" + e.getMessage());
		} catch (Exception e) {
			throw new BussinessException("607", "Sorry, Something went wrong in the service layer");
		}
//	if (movie.getMoviename().isEmpty()) {
//		throw new BussinessException("605", "Cannot find the moviename");
//	}
	}

	@Override
	public BookingIdDto checkingDetails(MovieTheaterdto movietheaterdto) {

		Random random = new Random();
		BookingIdDto bookingiddto = new BookingIdDto();
		Theater theater = theaterrepo.findByTheatername(movietheaterdto.getTheatername());
		TheaterDto theaterDto = convertTheaterEntitytoDto(theater);
		System.out.println("Saved" + theaterDto.getTheatername());
		int noofseats = movietheaterdto.getNoofseats();
		float price;
		List<MovieDto> movie = new ArrayList<>();
		movie = theaterDto.getAssignedmovies();
		System.out.println(noofseats);
		System.out.println(movie);
		try {
			for (MovieDto m : movie) {
				System.out.println(m.getMoviename());
				System.out.println(movietheaterdto.getMoviename());

				if (movietheaterdto.getMoviename().equalsIgnoreCase(m.getMoviename())) {
					System.out.println("...");
					price = theater.getPrice() * noofseats;
					int booking_id = 10000 + random.nextInt(90000);
					System.out.println(booking_id);
//	        	String booking_Id=Integer.toString(booking_id);
					bookingiddto.setBooking_Id(booking_id);
					bookingiddto.setTotal_price(price);
//					break;
					return bookingiddto;
				}
			}
			return null;
		} catch (Exception e) {
			throw new BussinessException("615",
					"Entered movie is not in the theater,please check properly" + e.getMessage());
		}

	}
//		System.out.println(bookingiddto.getBooking_Id());
//		System.out.println(bookingiddto.getTotal_price());
////	    return "Booking_id: "+bookingiddto.getBooking_Id()+"\n"+"Total_price: "+bookingiddto.getTotal_price();
//		

	@Override
	public List<Theater> showAllTheaters() {
		return theaterrepo.findAll();

	}

	@Override
	public GetPrice getprice(String theatername) {
		Theater theater = theaterrepo.getBytheatername(theatername);
		float price = theater.getPrice();
		GetPrice getprice = new GetPrice();
		getprice.setPrice(price);
		return getprice;
	}

	@Override
	public MovieDto getmovie(String moviename) {
		Movie movie = movierepo.getBymoviename(moviename);
		MovieDto moviedto = convertEntitytoDto(movie);
		return moviedto;
	}

}
