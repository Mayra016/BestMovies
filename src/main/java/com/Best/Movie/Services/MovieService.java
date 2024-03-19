package com.Best.Movie.Services;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Best.Movie.Entities.Movie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class MovieService{

	
	private Movie movie1 = new Movie();
	private Movie movie2 = new Movie();
	private List<Movie> playedMovies = new ArrayList<>();
	private List<Movie> availableMovies = new ArrayList<>();
	private List<Movie> levelMove = new ArrayList<>();
	private int score = 0;
	private boolean firstLevel = true;
	
	public void setRandomMovies() throws URISyntaxException {
		int statusCode;
    
	    // HTTP client creation
	    HttpClient httpClient = HttpClient.newHttpClient();
	
	    // HTTP GET petition construction
	    HttpRequest request = HttpRequest.newBuilder()
	    	    .uri(URI.create("https://api.themoviedb.org/3/trending/movie/day?language=en-US"))
	    	    .header("accept", "application/json")
	    	    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMmNkYjg4ZTRjOTFjYzNhY2I2NzdjYWYzYWJlYjFmNSIsInN1YiI6IjY1ZjM0OTk3MzU4MThmMDE4OGQxZDQ5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.dc8SH43CQCmbIKS_elYLi-Lo2rT4DJ8L8pVdOl7Lnyk")
	    	    .method("GET", HttpRequest.BodyPublishers.noBody())
	    	    .build();
	    
	        	
		try {
	        // Send petition and get response
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());       
	        ObjectMapper objectMapper = new ObjectMapper();
	
	        // Deserializar la respuesta JSON a un objeto JsonNode
	        JsonNode rootNode = objectMapper.readValue(response.body(), JsonNode.class);
	        
	        JsonNode results = rootNode.path("results");
	        System.out.println(results);	
	        for ( JsonNode movie : results ) {
	        	String title = movie.path("title").asText();
	        	short popularity = (short) movie.path("popularity").asInt();
	        	JsonNode genres = movie.path("genre_ids");
	        	short genre = (short) genres.get(0).asInt();
	        	Movie newMovie = new Movie(title, popularity, genre);
	        	
	        	if (this.playedMovies == null || !this.playedMovies.contains(newMovie)) {
	        		this.availableMovies.add(newMovie);
	        	}
	        }
	        System.out.println(availableMovies);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    
		}
	}
	
	public List<Movie> getLevel() {
		movie1 = this.availableMovies.get(0);
		movie2 = this.availableMovies.get(1);
		
		levelMove.add(movie1);
		levelMove.add(movie2);
		
		playedMovies.add(movie1);
		playedMovies.add(movie2);
		
		availableMovies.remove(0);
		availableMovies.remove(1);
		return levelMove;
	}
	
	public boolean checkAnswer(String playerAnswer) {
		
		if ( playerAnswer.equalsIgnoreCase(this.movie1.getMovieName()) && this.movie1.getPopularity() >= this.movie2.getPopularity() ) {
			return true;
		}
		if ( playerAnswer.equalsIgnoreCase(this.movie2.getMovieName()) && this.movie1.getPopularity() <= this.movie2.getPopularity() ) {
			return true;
		}
		if (this.firstLevel) {
			this.firstLevel = false;
		}
		
		return false;		
	}
	
	public void resetGame() {
		this.playedMovies.clear();
		this.levelMove.clear();
		this.availableMovies.clear();
		this.firstLevel = true;
	}
	
	public int calculateScore() {
		return this.firstLevel == false ? this.score += 10 : 0;
	}
	
	public boolean isAvailableMoviesNull() {
		if (this.availableMovies == null || this.availableMovies.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
