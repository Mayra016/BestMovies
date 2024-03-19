package com.Best.Movie.Entities;

import com.Best.Movie.Interfaces.MovieI;

public class Movie implements MovieI{
	private String movieName;	
	private short popularity;
	private short genre;
	private String imageURL;
	
	public Movie() {
		
	}
	
	public Movie(String movieName, short popularity, short genre, String imageURL) {
		this.movieName = movieName;
		this.popularity = popularity;
		this.imageURL = buildURL(imageURL);
	}

	public String buildURL(String URL) {
		String baseURL = "https://image.tmdb.org/t/p/w500";
		return baseURL.concat(URL);
	}

	@Override
	public void setMovieName(String newMovieName) {
		this.movieName = newMovieName;
	}

	@Override
	public String getMovieName() {
		return this.movieName;
	}

	@Override
	public void setPopularity(short newPopularity) {
		this.popularity = newPopularity;
	}

	@Override
	public short getPopularity() {
		return this.popularity;
	}

	@Override
	public void setGenre(byte newGenre) {
		this.genre = newGenre;
		
	}

	@Override
	public short getGenre() {
		return this.genre;
	}

	@Override
	public void setImageURL(String newURL) {
		this.imageURL = newURL;
		
	}

	@Override
	public String getImageURL() {
		return this.imageURL;
	}
}
