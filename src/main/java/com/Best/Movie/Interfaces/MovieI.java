package com.Best.Movie.Interfaces;

public interface MovieI {
	public void setMovieName(String newMovieName);
	public String getMovieName();
	
	public void setPopularity(short newPopularity);
	public short getPopularity();
	
	public void setGenre(byte newGenre);
	public short getGenre();
	
	public void setImageURL(String newURL);
	public String getImageURL();
	
	public String buildURL(String URL);
}
