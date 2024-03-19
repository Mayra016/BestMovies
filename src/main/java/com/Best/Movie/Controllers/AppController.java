package com.Best.Movie.Controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Best.Movie.Entities.Movie;
import com.Best.Movie.Services.MovieService;

@Controller
public class AppController {
	
	@Autowired
	MovieService movieService;

	
	@GetMapping("/menu")
	public String getMenu() {
		movieService.resetGame();
		return "menu";
	}
	
	@GetMapping("/level")
	public String getSongs(Model model) throws URISyntaxException {
		if ( movieService.isAvailableMoviesNull() ) {
			movieService.setRandomMovies();
		}		
		List<Movie> levelMovies = movieService.getLevel();
		int score = movieService.calculateScore();
		model.addAttribute("levelMovies", levelMovies);
		model.addAttribute("score", score);
		return "level";
	}
}
