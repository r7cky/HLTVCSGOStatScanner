package com.sickpillow.helpers;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import com.sickpillow.models.HalfBean;
import com.sickpillow.models.MatchBean;

public class MatchBeanHelper {
	
	public static void printChancesOfWinningFollowingTwoRoundsAfterPistolWin(List<MatchBean> matchBeans) {
		int wonFirstThreeAmount = 0;
		int upsetSecondOrThirdRoundAmount = 0;

		for (MatchBean matchBean : matchBeans) {
			
			HalfBean half1 = matchBean.getHalf1();
			HalfBean half2 = matchBean.getHalf2();
			
			if (half1.isDidAnyTeamWinFirstThreeRounds()) {
				wonFirstThreeAmount++;
			}else {
				upsetSecondOrThirdRoundAmount++;
			}
			
			//the reason we use a try here is because if the second half is under 3 rounds it can cause a crash
			try {
				if (half2.isDidAnyTeamWinFirstThreeRounds()) {
					wonFirstThreeAmount++;
				}else {
					upsetSecondOrThirdRoundAmount++;
				}
			}catch (NullPointerException e) {
				
			}
		}
		
		System.out.println("Statistics of winning the following two rounds after a pistol win:");
		Helper.printRatioAndStdAnd95ConfidenceForWinsAndLosses(wonFirstThreeAmount, upsetSecondOrThirdRoundAmount);
		System.out.println("");
	}
	
	public static void printChancesOfWinningFollowingTwoRoundsAfterCleanPistolWin(List<MatchBean> matchBeans) {
		int wonFirstThreeWithCleanPistolAmount = 0;
		int upsetSecondOrThirdRoundAmount = 0;

		for (MatchBean matchBean : matchBeans) {
			
			HalfBean half1 = matchBean.getHalf1();
			HalfBean half2 = matchBean.getHalf2();
			
			boolean isPistolCleanHalf1 = half1.isCleanWinForRound(1);
			boolean isPistolCleanHalf2 = half2.isCleanWinForRound(1);
			
			if (isPistolCleanHalf1 && matchBean.getHalf1().isDidAnyTeamWinFirstThreeRounds()) {
				wonFirstThreeWithCleanPistolAmount++;
			}else if (isPistolCleanHalf1 && !matchBean.getHalf1().isDidAnyTeamWinFirstThreeRounds()) {
				upsetSecondOrThirdRoundAmount++;
			}
			
			//the reason we use a try here is because if the second half is under 3 rounds it can cause a crash
			try {
				//if the pistol isn't clean, don't count it towards anything
				if (isPistolCleanHalf2 && matchBean.getHalf2().isDidAnyTeamWinFirstThreeRounds()) {
					wonFirstThreeWithCleanPistolAmount++;
				}else if (isPistolCleanHalf2 && !matchBean.getHalf2().isDidAnyTeamWinFirstThreeRounds()) {
					upsetSecondOrThirdRoundAmount++;
				}
			}catch (NullPointerException e) {

			}
		}
		
		System.out.println("Statistics of winning the following two rounds after a clean pistol win:");
		Helper.printRatioAndStdAnd95ConfidenceForWinsAndLosses(wonFirstThreeWithCleanPistolAmount, upsetSecondOrThirdRoundAmount);
		System.out.println("");
	}

	public static void printChancesOfWinningTwoRoundsAfterLosingFirstThree(List<MatchBean> matchBeans) {
		int timesWhereTeamWonTwoRoundsAfterLosingFirstThreeClean = 0;
		int timesWhereTeamLostFollowingRoundAfterWinningGun = 0;

		for (MatchBean matchBean : matchBeans) {
			
			HalfBean half1 = matchBean.getHalf1();
			HalfBean half2 = matchBean.getHalf2();
			
			if (half1.isDidAnyTeamUpsetTwoRoundsAfterLosingFirstThree()) {
				timesWhereTeamWonTwoRoundsAfterLosingFirstThreeClean++;
			}else if (half1.isDidAnyTeamLose5thAfterWinning4th()){
				timesWhereTeamLostFollowingRoundAfterWinningGun++;
			}

			//the reason we use a try is due to teams closing the game out if the half is over 12 rounds making it impossible to win first 5 of second half
			try {
				if (half2.isDidAnyTeamUpsetTwoRoundsAfterLosingFirstThree()) {
					timesWhereTeamWonTwoRoundsAfterLosingFirstThreeClean++;
				}else if (half2.isDidAnyTeamLose5thAfterWinning4th()){
					timesWhereTeamLostFollowingRoundAfterWinningGun++;
				}

			}catch (NullPointerException e) {}

		}
		
		System.out.println("Statistics of winning fourth and fifth round consecutively if first three are lost:");
		Helper.printRatioAndStdAnd95ConfidenceForWinsAndLosses(timesWhereTeamWonTwoRoundsAfterLosingFirstThreeClean, timesWhereTeamLostFollowingRoundAfterWinningGun);
		System.out.println("");
	}
	
	public static void printChancesOfWinningFirstHalfAfterFirstThreeWin(List<MatchBean> matchBeans) {
		int wonFirstHalfAfterFirstThreeAmount = 0;
		int lostFirstHalfAfterFirstThreeAmount = 0;

		for (MatchBean matchBean : matchBeans) {
			
			HalfBean half1 = matchBean.getHalf1();
			
			if (half1.isDidTeam1WinFirstThreeRoundsForHalf(1)) {
				if (half1.isDidTeam1WinHalf()) {
					wonFirstHalfAfterFirstThreeAmount++;
				}else {
					lostFirstHalfAfterFirstThreeAmount++;
				}
				
			
			}else if (half1.isDidTeam2WinFirstThreeRoundsForHalf(1)){
				if (half1.isDidTeam2WinHalf()) {
					wonFirstHalfAfterFirstThreeAmount++;
				}else {
					lostFirstHalfAfterFirstThreeAmount++;
				}
			}
			
		}
		
		System.out.println("Statistics of winning first half if first three rounds are won:");
		Helper.printRatioAndStdAnd95ConfidenceForWinsAndLosses(wonFirstHalfAfterFirstThreeAmount, lostFirstHalfAfterFirstThreeAmount);
		System.out.println("");
	}
	
	public static void printStatsForFirstHalfAfterFirstThreeWin(List<MatchBean> matchBeans) {
		DescriptiveStatistics halfStatistics = new DescriptiveStatistics();
		
		for (MatchBean matchBean : matchBeans) {
			
			HalfBean half1 = matchBean.getHalf1();
			
			if (half1.isDidTeam1WinFirstThreeRoundsForHalf(1)) {
				if (half1.isDidTeam1WinHalf()) {
					halfStatistics.addValue(half1.getTeam1Score());
				}
				
			}else if (half1.isDidTeam2WinFirstThreeRoundsForHalf(1)){
				if (half1.isDidTeam2WinHalf()) {
					halfStatistics.addValue(half1.getTeam2Score());
				}
			}
			
		}
		
		System.out.println(String.format("Average rounds won in a half if first three are won: %s (Variance: %s)", halfStatistics.getMean(), halfStatistics.getVariance()));
		System.out.println("");
	}
	
	public static void printChancesOfWinningBothFirstThree(List<MatchBean> matchBeans) {
		int wonBothFirstThreeAmount = 0;
		int didntWinBothAmount = 0;

		for (MatchBean matchBean : matchBeans) {
			HalfBean half1 = matchBean.getHalf1();
			HalfBean half2 = matchBean.getHalf2();

			//the reason we use a try is due to teams closing the game out if the half is over 12 rounds making it impossible to win first 5 of second half
			try {
				boolean didTeam1WinBothFirstThree = half1.isDidTeam1WinFirstThreeRoundsForHalf(1) && half2.isDidTeam1WinFirstThreeRoundsForHalf(2);
				boolean didTeam2WinBothFirstThree = half1.isDidTeam2WinFirstThreeRoundsForHalf(1) && half2.isDidTeam2WinFirstThreeRoundsForHalf(2);

				if (didTeam1WinBothFirstThree || didTeam2WinBothFirstThree) {
					wonBothFirstThreeAmount++;
				}else {
					didntWinBothAmount++;
				}

			}catch (NullPointerException e) {
				continue;
			}

		}
		
		System.out.println("Statistics of winning first three rounds for both halves:");
		Helper.printRatioAndStdAnd95ConfidenceForWinsAndLosses(wonBothFirstThreeAmount, didntWinBothAmount);
		System.out.println("");
	}
	
	public static void printChancesOfWinningBothFirstThreeWithCleanPistolOnly(List<MatchBean> matchBeans) {
		int wonBothFirstThreeCleanAmount = 0;
		int didntWinFirstThreeCleanAmount = 0;

		for (MatchBean matchBean : matchBeans) {
			HalfBean half1 = matchBean.getHalf1();
			HalfBean half2 = matchBean.getHalf2();

			boolean isPistolCleanHalf1 = half1.isCleanWinForRound(1);
			boolean isPistolCleanHalf2 = half2.isCleanWinForRound(1);
			
			
			try {

				boolean didTeam1WinBothFirstThree = half1.isDidTeam1WinFirstThreeRoundsWithCleanPistolOnlyForHalf(1) && half2.isDidTeam1WinFirstThreeRoundsWithCleanPistolOnlyForHalf(2);
				boolean didTeam2WinBothFirstThree = half1.isDidTeam2WinFirstThreeRoundsWithCleanPistolOnlyForHalf(1) && half2.isDidTeam2WinFirstThreeRoundsWithCleanPistolOnlyForHalf(2);

				//the reason we use a try is due to teams closing the game out if the half is over 12 rounds making it impossible to win first 5 of second half
				if (didTeam1WinBothFirstThree || didTeam2WinBothFirstThree) {
					if (isPistolCleanHalf1 && isPistolCleanHalf2) {
						wonBothFirstThreeCleanAmount++;
					}
				}else {
					didntWinFirstThreeCleanAmount++;
				}

			}catch (NullPointerException e) {
				continue;
			}
		}
		
		System.out.println("Statistics of winning first three rounds for both halves with clean pistol only:");
		Helper.printRatioAndStdAnd95ConfidenceForWinsAndLosses(wonBothFirstThreeCleanAmount, didntWinFirstThreeCleanAmount);
		System.out.println("");
	}
	
	public static void printChancesOfWinningMatchAfterWinningBothFirstThree(List<MatchBean> matchBeans) {
		int wonMatchAfterWinningBothFirstThree = 0;
		int lostMatchAfterWinningBothFirstThree = 0;
		int overtimeMatchAfterWinningBothFirstThree = 0;

		for (MatchBean matchBean : matchBeans) {
			HalfBean half1 = matchBean.getHalf1();
			HalfBean half2 = matchBean.getHalf2();
			
			//the reason we use a try is due to teams closing the game out if the half is over 12 rounds making it impossible to win first 5 of second half
			try {
				
				boolean didTeam1WinBothFirstThree = half1.isDidTeam1WinFirstThreeRoundsForHalf(1) && half2.isDidTeam1WinFirstThreeRoundsForHalf(2);
				boolean didTeam2WinBothFirstThree = half1.isDidTeam2WinFirstThreeRoundsForHalf(1) && half2.isDidTeam2WinFirstThreeRoundsForHalf(2);
				
				if (didTeam1WinBothFirstThree) {
					
					if (matchBean.isTeam1MatchWinner()) {
						wonMatchAfterWinningBothFirstThree++;
					}else if (matchBean.isOvertime()) {
						overtimeMatchAfterWinningBothFirstThree++;
					}else {
						lostMatchAfterWinningBothFirstThree++;
					}
					
				}else if (didTeam2WinBothFirstThree) {
					if (!matchBean.isTeam1MatchWinner()) {
						wonMatchAfterWinningBothFirstThree++;
					}else if (matchBean.isOvertime()) {
						overtimeMatchAfterWinningBothFirstThree++;
					}else {
						lostMatchAfterWinningBothFirstThree++;
					}
				}
			}catch (NullPointerException e) {}
			
		}
		
		System.out.println("Match results after a team wins the first three rounds of both halves:");
		Helper.printRatioAndStdAnd95ConfidenceForWinsLossesOvertime(wonMatchAfterWinningBothFirstThree, lostMatchAfterWinningBothFirstThree, overtimeMatchAfterWinningBothFirstThree);
		System.out.println("");
	}
}