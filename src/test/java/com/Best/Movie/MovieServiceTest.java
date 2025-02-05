package com.Best.Movie;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.Best.Movie.Controllers.APIController;
import com.Best.Movie.Entities.Movie;
import com.Best.Movie.Services.MovieService;


public class MovieServiceTest {
	
	@InjectMocks
	MovieService movieService;

	@InjectMocks
	APIController apiController;
	
	@BeforeEach
	public void init() {
	    MockitoAnnotations.openMocks(this);
	}

	
	@Test
	public void getRandomSongsTest() throws URISyntaxException {		
		movieService.setRandomMovies();
		assertEquals(false, movieService.isAvailableMoviesNull());
	}
	
	@Test
	public void getLevelTestNull() throws URISyntaxException {
		movieService.setRandomMovies();
		List<Movie> levelMovies = movieService.getLevel();
		assertEquals(false, levelMovies.isEmpty());	
	}
	
	@RepeatedTest(4)
	public void getLevelTestRepeated() throws URISyntaxException {
		movieService.setRandomMovies();
		List<Movie> levelMovies = movieService.getLevel();
		boolean repeated = levelMovies.get(0) == levelMovies.get(1) ? true : false;
		assertEquals(false, repeated);	
	}
	
	@Test
	public void resetGameTest() throws URISyntaxException {
		movieService.setRandomMovies();
		movieService.resetGame();
		assertEquals(true, movieService.isAvailableMoviesNull());		
	}
	
	@Test
	public void calculateScoreTest() {
		int score = movieService.calculateScore();
		assertEquals(0, score);
		
	}
	
	@Test
	public void checkAnswerTest() throws URISyntaxException {
		movieService.setRandomMovies();
		List<Movie> levelMovies = movieService.getLevel();
		Movie answer = levelMovies.get(0).getPopularity() > levelMovies.get(1).getPopularity() ? levelMovies.get(0) : levelMovies.get(1);
		System.out.println(apiController.checkAnswer(answer.getMovieName()));
	}
}
