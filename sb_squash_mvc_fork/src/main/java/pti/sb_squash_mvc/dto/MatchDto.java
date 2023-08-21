package pti.sb_squash_mvc.dto;

import java.time.LocalDate;

public class MatchDto {
	
	private String player1Email;
	private String player2Email;
	private int placeId;
	private int player1Score;
	private int player2Score;
	private LocalDate date;
	
	public String getPlayer1Email() {
		return player1Email;
	}
	
	public void setPlayer1Email(String player1Email) {
		this.player1Email = player1Email;
	}
	
	public String getPlayer2Email() {
		return player2Email;
	}
	
	public void setPlayer2Email(String player2Email) {
		this.player2Email = player2Email;
	}
	
	public int getPlaceId() {
		return placeId;
	}
	
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	
	public int getPlayer1Score() {
		return player1Score;
	}
	
	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}
	
	public int getPlayer2Score() {
		return player2Score;
	}
	
	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
