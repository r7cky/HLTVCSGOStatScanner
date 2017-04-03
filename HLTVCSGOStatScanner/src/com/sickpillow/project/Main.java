package com.sickpillow.project;

import java.util.List;

import com.sickpillow.helpers.MatchBeanHelper;
import com.sickpillow.models.MatchBean;

public class Main {
	public static void main(String[] args) throws Exception {
		List<String> hltvLinkList = Scanner.getMatchesWithHltvLink("http://www.hltv.org/?pageid=188&statsfilter=2825");
		List<MatchBean> matchBeans = Scanner.matchBeansFromLinkList(hltvLinkList);
		
		System.out.println("");
		System.out.println("Total matches gathered: " +matchBeans.size());
		System.out.println("");
		
		MatchBeanHelper.printChancesOfWinningFollowingTwoRoundsAfterPistolWin(matchBeans);
		MatchBeanHelper.printChancesOfWinningFollowingTwoRoundsAfterCleanPistolWin(matchBeans);
		MatchBeanHelper.printChancesOfWinningTwoRoundsAfterLosingFirstThree(matchBeans);
		MatchBeanHelper.printChancesOfWinningFirstHalfAfterFirstThreeWin(matchBeans);
		MatchBeanHelper.printStatsForFirstHalfAfterFirstThreeWin(matchBeans);
		MatchBeanHelper.printChancesOfWinningBothFirstThree(matchBeans);
		MatchBeanHelper.printChancesOfWinningBothFirstThreeWithCleanPistolOnly(matchBeans);
		MatchBeanHelper.printChancesOfWinningMatchAfterWinningBothFirstThree(matchBeans);
	}
}
