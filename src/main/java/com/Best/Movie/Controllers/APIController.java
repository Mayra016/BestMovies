package com.Best.Movie.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Best.Movie.Services.MovieService;

@RestController
public class APIController {

	@Autowired
	MovieService movieService;

	@GetMapping("/checkAnswer/{playerAnswer}")
    public ResponseEntity<String> checkAnswer(@PathVariable String playerAnswer) {
		System.out.println("PLAYER ANSWER: " + playerAnswer);
		if (movieService.checkAnswer(playerAnswer)) {
			System.out.println("PLAYER ANSWER: " + playerAnswer);
            return ResponseEntity.status(HttpStatus.OK).body("TRUE");
        } else {
        	movieService.resetGame();
            return ResponseEntity.status(HttpStatus.OK).body("FALSE");
        }       
    }
}
