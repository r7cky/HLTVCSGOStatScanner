package com.sickpillow.models;

public class MatchBean implements java.io.Serializable {
	private String hltvLink;
	private HalfBean half1;
	private HalfBean half2;
	
    public MatchBean(){}
    
	public MatchBean(String hltvLink, HalfBean half1, HalfBean half2) {
		super();
		this.hltvLink = hltvLink;
		this.half1 = half1;
		this.half2 = half2;
	}
	
	public String getHltvLink() {
		return hltvLink;
	}

	public void setHltvLink(String hltvLink) {
		this.hltvLink = hltvLink;
	}

	public HalfBean getHalf1() {
		return half1;
	}

	public void setHalf1(HalfBean half1) {
		this.half1 = half1;
	}

	public HalfBean getHalf2() {
		return half2;
	}

	public void setHalf2(HalfBean half2) {
		this.half2 = half2;
	}
	
	public int getTotalRoundsForTeam(int whichTeam) {
		if (whichTeam == 1) {
			int team1TotalRounds = half1.getTeam1Score() + half2.getTeam1Score();
			return team1TotalRounds;
		}else if (whichTeam == 2) {
			int team2TotalRounds = half1.getTeam2Score() + half2.getTeam2Score();
			return team2TotalRounds;
		}else {
			return 0;
		}
	}
	
	public boolean isFirstHalfForBothTeamUnder11() {
		int firstHalfTeam1 = half1.getTeam1Score();
		int firstHalfTeam2 = half1.getTeam2Score();
		
		if (firstHalfTeam1 < 11 && firstHalfTeam2 < 11) {
			return true;
		}else {
			return false;
		}
	}
	
	//this is only true if it's a showmatch or a decider
	public boolean isFirstHalfTotalUnder15() {
		int firstHalfTotal = half1.getTeam1Score() + half1.getTeam2Score();
		
		if (firstHalfTotal < 15) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isTeam1MatchWinner() {
		int team1RoundCount = half1.getTeam1Score() + half2.getTeam2Score();
		int team2RoundCount = half2.getTeam2Score() + half2.getTeam2Score();
		
		if (team1RoundCount > team2RoundCount) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isOvertime() {
		int team1RoundsInRelegation = half1.getTeam1Score() + half2.getTeam1Score();
		int team2RoundsInRelegation = half1.getTeam2Score() + half2.getTeam2Score();
		
		if (team1RoundsInRelegation == 15 && team2RoundsInRelegation == 15) {
			return true;
		}else {
			return false;
		}
	}
	
}
