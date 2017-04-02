package com.sickpillow.models;

//What is a "clean" win? A clean win is when Ts win or when CTs win with kills/time. Any of these wins force the opposite team on a double eco for the first three rounds.

public class HalfBean implements java.io.Serializable {
	private int team1Score;
	private int team2Score;
	private String round1Result;
	private String round2Result;
	private String round3Result;
	private String round4Result;
	private String round5Result;
	private String round6Result;
	private String round7Result;
	private String round8Result;
	private String round9Result;
	private String round10Result;
	private String round11Result;
	private String round12Result;
	private String round13Result;
	private String round14Result;
	private String round15Result;

	public HalfBean(){}
	
	public HalfBean(int team1Score, int team2Score, String round1Result, String round2Result, String round3Result,
			String round4Result, String round5Result, String round6Result, String round7Result, String round8Result,
			String round9Result, String round10Result, String round11Result, String round12Result, String round13Result,
			String round14Result, String round15Result) {
		super();
		this.team1Score = team1Score;
		this.team2Score = team2Score;
		this.round1Result = round1Result;
		this.round2Result = round2Result;
		this.round3Result = round3Result;
		this.round4Result = round4Result;
		this.round5Result = round5Result;
		this.round6Result = round6Result;
		this.round7Result = round7Result;
		this.round8Result = round8Result;
		this.round9Result = round9Result;
		this.round10Result = round10Result;
		this.round11Result = round11Result;
		this.round12Result = round12Result;
		this.round13Result = round13Result;
		this.round14Result = round14Result;
		this.round15Result = round15Result;
	}
	
	//HALF SCORES
	
	public int getTeam1Score() {
		return team1Score;
	}

	public void setTeam1Score(int team1Score) {
		this.team1Score = team1Score;
	}

	public int getTeam2Score() {
		return team2Score;
	}

	public void setTeam2Score(int team2Score) {
		this.team2Score = team2Score;
	}
	
	
	//HALF RESULTS
	
	public boolean isDidTeam1WinHalf() {
		return team1Score >= 8 ? true : false;
	}
	
	public boolean isDidTeam2WinHalf() {
		return team2Score >= 8 ? true : false;
	}
	
	
	//ROUND RESULTS
	
	public String getResultForRound(int round) {
		switch (round) {
		case 1:
			return round1Result;
		case 2:
			return round2Result;
		case 3:
			return round3Result;
		case 4:
			return round4Result;
		case 5:
			return round5Result;
		case 6:
			return round6Result;
		case 7:
			return round7Result;
		case 8:
			return round8Result;
		case 9:
			return round9Result;
		case 10:
			return round10Result;
		case 11:
			return round11Result;
		case 12:
			return round12Result;
		case 13:
			return round13Result;
		case 14:
			return round14Result;
		case 15:
			return round15Result;
		default:
			return null;
		}
	}
	
	public void setResultForRound(String roundResult, int round) {
		switch (round) {
		case 1:
			round1Result = roundResult;
			return;
		case 2:
			round2Result = roundResult;
			return;
		case 3:
			round3Result = roundResult;
			return;
		case 4:
			round4Result = roundResult;
			return;
		case 5:
			round5Result = roundResult;
			return;
		case 6:
			round6Result = roundResult;
			return;
		case 7:
			round7Result = roundResult;
			return;
		case 8:
			round8Result = roundResult;
			return;
		case 9:
			round9Result = roundResult;
			return;
		case 10:
			round10Result = roundResult;
			return;
		case 11:
			round11Result = roundResult;
			return;
		case 12:
			round12Result = roundResult;
			return;
		case 13:
			round13Result = roundResult;
			return;
		case 14:
			round14Result = roundResult;
			return;
		case 15:
			round15Result = roundResult;
			return;
		default:
			return;
		}
	}
	
	
	//IS SPECIFIC CT/T ROUND WINS
	
	public boolean isCtKillWinForRound(int round) {
		String roundResult = getResultForRound(round);
		return roundResult.equals("ct_win") ? true : false;
	}
	
	public boolean isCtBombDefuseWinForRound(int round) {
		String roundResult = getResultForRound(round);
		return roundResult.equals("bomb_defused") ? true : false;
	}
	
	public boolean isCtTimeWinForRound(int round) {
		String roundResult = getResultForRound(round);
		return roundResult.equals("stopwatch") ? true : false;
	}
	
	public boolean isTKillWinForRound(int round) {
		String roundResult = getResultForRound(round);
		return roundResult.equals("t_win") ? true : false;
	}
	
	public boolean isTBombExplodeWinForRound(int round) {
		String roundResult = getResultForRound(round);
		return roundResult.equals("bomb_exploded") ? true : false;
	}
	
	
	//IS CT/T ROUND WINS
	
	public boolean isCtWinForRound(int round) {
		switch (getResultForRound(round)) {
		case "ct_win":
			return true;
		case "bomb_defused":
			return true;
		case "stopwatch":
			return true;
		default:
			return false;
		}
	}
	
	public boolean isTWinForRound(int round) {
		switch (getResultForRound(round)) {
		case "t_win":
			return true;
		case "bomb_exploded":
			return true;
		default:
			return false;
		}
	}
	
	public boolean isCtCleanWinForRound(int round) {
		switch (getResultForRound(round)) {
		case "ct_win":
			return true;
		case "stopwatch":
			return true;
		default:
			return false;
		}
	}
	
	public boolean isCleanWinForRound(int round) {
		boolean ctClean = isCtCleanWinForRound(round);
		boolean tWin = isTWinForRound(round);
		
		if (ctClean || tWin) {
			return true;
		}else {
			return false;
		}
	}

	public boolean isThisRoundAnUpsetWithCleanPrevious(int round) {
		if (round == 1 || round > 15) {
			return false;
		}
		
		//checking if previous round was clean CT win and checking if this is T upset
		boolean isTWinForThisRound = isTWinForRound(round);
		boolean isCleanCtPreviousRound = isCtCleanWinForRound(round-1);
		
		//for opposite situation where T wins and CT upsets
		boolean isCtWinThisRound = isCtWinForRound(round);
		boolean isTWinPreviousRound = isTWinForRound(round-1);
		
		if (isCleanCtPreviousRound && isTWinForThisRound) {
			return true;
		}else if (isTWinPreviousRound && isCtWinThisRound) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isThisRoundAnUpset(int round) {
		if (round == 1 || round > 15) {
			return false;
		}
		
		boolean roundCt = isCtWinForRound(round);
		boolean roundBeforeCt = isCtWinForRound(round-1);
		
		return roundCt != roundBeforeCt ? true : false;
	}
	
	
	//FIRST THREE ROUNDS
	
	public boolean isDidAnyTeamWinFirstThreeRounds() {
		boolean didCtWin = isDidCtWinFirstThreeRounds();
		boolean didTWin = isDidTWinFirstThreeRounds();
		
		if (didCtWin || didTWin) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidCtWinFirstThreeRounds() {
		boolean isCtWinRound1 = isCtWinForRound(1);
		boolean isCtWinRound2 = isCtWinForRound(2);
		boolean isCtWinRound3 = isCtWinForRound(3);
		
		if (isCtWinRound1 && isCtWinRound2 && isCtWinRound3) {
			return true;
		}else {
			return false;
		}
	}
	
	//the reason we don't take the way the round was won into account for the Ts is because no matter the way they win, the CTs will usually save for the first three if the Ts win
	public boolean isDidTWinFirstThreeRounds() {
			boolean isTWinRound1 = isTWinForRound(1);
			boolean isTWinRound2 = isTWinForRound(2);
			boolean isTWinRound3 = isTWinForRound(3);
			
			if (isTWinRound1 && isTWinRound2 && isTWinRound3) {
				return true;
			}else {
				return false;
			}
		}

	
	//FIRST THREE ROUNDS (CLEAN)
	
	public boolean isDidAnyTeamWinFirstThreeRoundsClean() {
		boolean didCtWinClean = isDidCtWinFirstThreeRoundsClean();
		boolean didTWinClean = isDidTWinFirstThreeRounds();
		
		if (didCtWinClean || didTWinClean) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidCtWinFirstThreeRoundsClean() {
		boolean isCtKillWinRound1 = isCtCleanWinForRound(1);
		boolean isCtKillWinRound2 = isCtCleanWinForRound(2);
		//the reason we don't take the way the round was won for the third round is because even if the Ts plant on the third round save, they usually spend all their money on the 4th (gun round)
		boolean isCtWinRound3 = isCtWinForRound(3);
		
		if (isCtKillWinRound1 && isCtKillWinRound2 && isCtWinRound3) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidCtWinFirstThreeRoundsWithCleanPistolOnly() {
		boolean isCtKillWinRound1 = isCtCleanWinForRound(1);
		boolean isCtKillWinRound2 = isCtWinForRound(2);
		boolean isCtWinRound3 = isCtWinForRound(3);
		
		if (isCtKillWinRound1 && isCtKillWinRound2 && isCtWinRound3) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//FIRST THREE ROUNDS (TEAM 1/2)
	
	public boolean isDidTeam1WinFirstThreeRoundsForHalf(int half) {
		boolean didCtWin = isDidCtWinFirstThreeRounds();
		boolean didTWin = isDidTWinFirstThreeRounds();
		
		if (didCtWin && half == 1) {
			return true;
		}else if (didTWin && half == 2) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidTeam2WinFirstThreeRoundsForHalf(int half) {
		boolean didCtWin = isDidCtWinFirstThreeRounds();
		boolean didTWin = isDidTWinFirstThreeRounds();
		
		if (didTWin && half == 1) {
			return true;
		}else if (didCtWin && half == 2) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//FIRST THREE ROUNDS (TEAM 1/2) (CLEAN)
	
	public boolean isDidTeam1WinFirstThreeRoundsCleanForHalf(int half) {
		boolean didCtWinClean = isDidCtWinFirstThreeRoundsClean();
		boolean didTWinClean = isDidTWinFirstThreeRounds();
		
		if (didCtWinClean && half == 1) {
			return true;
		}else if (didTWinClean && half == 2) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidTeam2WinFirstThreeRoundsCleanForHalf(int half) {
		boolean didCtWinClean = isDidCtWinFirstThreeRoundsClean();
		boolean didTWinClean = isDidTWinFirstThreeRounds();
		
		if (didTWinClean && half == 1) {
			return true;
		}else if (didCtWinClean && half == 2) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//FIRST THREE ROUNDS (TEAM 1/2) (CLEAN PISTOLS ONLY)
	
	public boolean isDidAnyTeamWinFirstThreeRoundsWithCleanPistolOnly() {
		boolean didCtWinClean = isDidCtWinFirstThreeRoundsWithCleanPistolOnly();
		boolean didTWinClean = isDidTWinFirstThreeRounds();
		
		if (didCtWinClean || didTWinClean) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidTeam1WinFirstThreeRoundsWithCleanPistolOnlyForHalf(int half) {
		boolean didCtWinClean = isDidCtWinFirstThreeRoundsWithCleanPistolOnly();
		boolean didTWinClean = isDidTWinFirstThreeRounds();
		
		if (didCtWinClean && half == 1) {
			return true;
		}else if (didTWinClean && half == 2) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidTeam2WinFirstThreeRoundsWithCleanPistolOnlyForHalf(int half) {
		boolean didCtWinClean = isDidCtWinFirstThreeRoundsWithCleanPistolOnly();
		boolean didTWinClean = isDidTWinFirstThreeRounds();
		
		if (didTWinClean && half == 1) {
			return true;
		}else if (didCtWinClean && half == 2) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//4TH AND 5TH ROUND WINS AFTER LOSING FIRST THREE
	
	public boolean isDidAnyTeamUpsetTwoRoundsAfterLosingFirstThree() {
		boolean didCtUpsetTwo = isDidCtUpsetTwoRoundsAfterLosingFirstThree();
		boolean didTUpsetTwo = isDidTUpsetTwoRoundsAfterLosingFirstThree();
		
		if (didCtUpsetTwo || didTUpsetTwo) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidCtUpsetTwoRoundsAfterLosingFirstThree() {
		boolean didTWinFirstThree = isDidTWinFirstThreeRounds();
		
		boolean didCtWinFourth = isCtWinForRound(4);
		boolean didCtWinFifth = isCtWinForRound(5);
		
		if (didTWinFirstThree && didCtWinFourth && didCtWinFifth) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidTUpsetTwoRoundsAfterLosingFirstThree() {
		boolean didCtWinFirstThree = isDidCtWinFirstThreeRounds();

		boolean didTWinFourth = isTWinForRound(4);
		boolean didTWinFifth = isTWinForRound(5);

		if (didCtWinFirstThree && didTWinFourth && didTWinFifth) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//4TH AND 5TH ROUND FAILS AFTER LOSING FIRST THREE
	
	public boolean isDidAnyTeamLose5thAfterWinning4th() {
		boolean didCtLose5th = isDidTLose5thAfterWinning4th();
		boolean didTLose5th = isDidCtLose5thAfterWinning4th();
		
		if (didCtLose5th || didTLose5th) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidCtLose5thAfterWinning4th() {
		boolean didTWinFirstThree = isDidTWinFirstThreeRounds();

		boolean didCtWinFourth = isCtWinForRound(4);
		boolean didCtLoseFifth = isTWinForRound(5);

		if (didTWinFirstThree && didCtWinFourth && didCtLoseFifth) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isDidTLose5thAfterWinning4th() {
		boolean didCtWinFirstThree = isDidCtWinFirstThreeRounds();

		boolean didTWinFourth = isTWinForRound(4);
		boolean didTLoseFifth = isCtWinForRound(5);

		if (didCtWinFirstThree && didTWinFourth && didTLoseFifth) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//PRINT
	
	public void printHalf() {
		System.out.println("===printing half===");
		System.out.println(round1Result);
		System.out.println(round2Result);
		System.out.println(round3Result);
		System.out.println(round4Result);
		System.out.println(round5Result);
		System.out.println(round6Result);
		System.out.println(round7Result);
		System.out.println(round8Result);
		System.out.println(round9Result);
		System.out.println(round10Result);
		System.out.println(round11Result);
		System.out.println(round12Result);
		System.out.println(round13Result);
		System.out.println(round14Result);
		System.out.println(round15Result);
		System.out.println("===done printing half===");
	}
}
