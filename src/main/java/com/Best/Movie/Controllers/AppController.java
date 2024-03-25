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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.PushBuilder;

@Controller
public class AppController {
	
	@Autowired
	MovieService movieService;
	List<Movie> levelMovies = new ArrayList<>();
	int score = 0;
	boolean win = false;
	
	@GetMapping("/menu")
	public String getMenu(HttpServletRequest request) {
		if (levelMovies != null) {
			levelMovies.clear();
			score = 0;
			win = false;
		}
		movieService.resetGame();
	    PushBuilder pushBuilder = request.newPushBuilder();
	    if (pushBuilder != null) {
	        pushBuilder.path("/css/menu.css").push();
	        pushBuilder.path("/images/background.jpg").push();
	    }
	    pushBuilder = null;
	    
		return "menu";
	}
	
	@GetMapping("/level")
	public String getLevel(HttpServletRequest request, Model model) throws URISyntaxException {
		if ( movieService.isAvailableMoviesNull() ) {
			movieService.setRandomMovies();
		}
		movieService.newLevel();
		levelMovies = movieService.getLevel();
		score = movieService.calculateScore();
	    PushBuilder pushBuilder = request.newPushBuilder();
	    if (pushBuilder != null) {
	        pushBuilder.path("/css/level.css").push();
	        pushBuilder.path("/images/background.jpg").push();
	        pushBuilder.path("/images/menu-logo.jpg").push();
	    }
	    pushBuilder = null;
	    
		model.addAttribute("levelMovies", levelMovies);
		model.addAttribute("score", score);
		return "level";
	}
}
