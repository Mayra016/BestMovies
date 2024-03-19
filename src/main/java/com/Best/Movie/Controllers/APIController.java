package com.Best.Movie.Controllers;

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
	public ResponseEntity<String> checkAnswer(@RequestBody String playerAnswer) {
		if (movieService.checkAnswer(playerAnswer)) {
			movieService.calculateScore();
			return ResponseEntity.ok("TRUE");
		} else {
			movieService.resetGame();
			return ResponseEntity.ok("FALSE");
		}		
	}
}
