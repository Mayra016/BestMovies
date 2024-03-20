package com.Best.Movie.Controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.Best.Movie.Entities.Movie;
import com.Best.Movie.Services.MovieService;

@Controller
public class AppController {
	
	@Autowired
	MovieService movieService;
	List<Movie> levelMovies = new ArrayList<>();
	int score = 0;
	boolean win = false;
	
	@GetMapping("/menu")
	public String getMenu() {
		movieService.resetGame();
		return "menu";
	}
	
	@GetMapping("/level")
	public String getLevel(Model model) throws URISyntaxException {
		if ( movieService.isAvailableMoviesNull() ) {
			movieService.setRandomMovies();
		}
		movieService.newLevel();
		levelMovies = movieService.getLevel();
		score = movieService.calculateScore();
		model.addAttribute("levelMovies", levelMovies);
		model.addAttribute("score", score);
		return "level";
	}
	
	@GetMapping("/lost")
	public String getLost(Model model){
		model.addAttribute("levelMovies", levelMovies);
		model.addAttribute("score", score);
		return "lost";
	}
	/*
	@GetMapping("/checkAnswer/{playerAnswer}")
	public String checkAnswer(@PathVariable String playerAnswer) {
		System.out.println("PLAYER ANSWER: " + playerAnswer);
		if (movieService.checkAnswer(playerAnswer)) {
			System.out.println("PLAYER ANSWER: " + playerAnswer);
			movieService.calculateScore();
			levelMovies.clear();
			return "redirect:/level";
		} else {
			movieService.resetGame();
			return "redirect:/lost";
		}		
	}*/
}
