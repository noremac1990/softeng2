package com.wayne.cookoutapp.server;

public class ComboRating {
	
	private int timesRated;
	private int totalRating;
	private int flavor1;
	private int flavor2;
	
	public ComboRating(int flavor1, int flavor2, int timesRated, int totalRating) {
		this.setTimesRated(timesRated);
		this.setTotalRating(totalRating);
		this.setFlavor1(flavor1);
		this.setFlavor2(flavor2);
	}

	public int getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}

	public int getTimesRated() {
		return timesRated;
	}

	public void setTimesRated(int timesRated) {
		this.timesRated = timesRated;
	}

	public int getFlavor1() {
		return flavor1;
	}

	public void setFlavor1(int flavor1) {
		this.flavor1 = flavor1;
	}

	public int getFlavor2() {
		return flavor2;
	}

	public void setFlavor2(int flavor2) {
		this.flavor2 = flavor2;
	}
	
	public double getRating() {
		return (double) totalRating / timesRated;
	}
}
