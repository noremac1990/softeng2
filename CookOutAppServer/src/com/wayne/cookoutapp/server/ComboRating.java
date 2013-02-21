package com.wayne.cookoutapp.server;

public class ComboRating {
	
	private int timesRated;
	private int totalRating;
	
	public ComboRating(int timesRated, int totalRating) {
		this.timesRated = timesRated;
		this.totalRating = totalRating;
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
}
