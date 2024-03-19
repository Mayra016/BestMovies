package com.Best.Movie.Controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Best.Movie.Services.MovieService;

@RestController
public class APIController {

	@Autowired
	MovieService movieService;
	
	@PostMapping("/checkAnswer")
	public String checkAnswer(@RequestBody JSONObject playerAnswer) {
		if (movieService.checkAnswer(playerAnswer.getString("playerAnswer"))) {
			movieService.calculateScore();
			return "redirect:/level";
		} else {
			movieService.resetGame();
			return "redirect:/lost";
		}		
	}
}
