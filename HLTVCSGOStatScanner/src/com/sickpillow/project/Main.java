package com.sickpillow.project;

import java.util.List;

import com.sickpillow.helpers.MatchBeanHelper;
import com.sickpillow.models.MatchBean;

public class Main {
	public static void main(String[] args) throws Exception {
		List<String> hltvLinkList = Scanner.getMatchesWithHltvLink("http://www.hltv.org/?pageid=188&statsfilter=2825"); //big events for 2016
		//List<String> hltvLinkList = Scanner.getMatchesWithHltvLink("http://www.hltv.org/?pageid=188&statsfilter=2830"); //big events for 2017
		//List<String> hltvLinkList = Scanner.getMatchesWithHltvLink("http://www.hltv.org/?pageid=188&statsfilter=2053"); //every lan match from past 3 months (WILL TAKE LONG)
		List<MatchBean> matchBeans = Scanner.matchBeansFromLinkList(hltvLinkList);
		
		System.out.println("");
		System.out.println("Total matches gathered: "+matchBeans.size());
		System.out.println("");
		
		MatchBeanHelper.printChancesOfCtWinningFollowingTwoRoundsAfterNoDefusePistolWin(matchBeans);
		MatchBeanHelper.printChancesOfCtWinningFollowingTwoRoundsAfterDefusePistolWin(matchBeans);
		MatchBeanHelper.printChancesOfTWinningFollowingTwoRoundsAfterPistolWin(matchBeans);
		MatchBeanHelper.printChancesOfWinningFollowingTwoRoundsAfterPistolWinOverall(matchBeans);
		MatchBeanHelper.printChancesOfWinningTwoRoundsAfterLosingFirstThree(matchBeans);
		MatchBeanHelper.printChancesOfWinningFirstHalfAfterFirstThreeWin(matchBeans);
		MatchBeanHelper.printStatsForFirstHalfAfterFirstThreeWin(matchBeans);
		MatchBeanHelper.printChancesOfWinningBothFirstThree(matchBeans);
		MatchBeanHelper.printChancesOfWinningBothFirstThreeWithCleanPistolOnly(matchBeans);
		MatchBeanHelper.printChancesOfWinningMatchAfterWinningBothFirstThree(matchBeans);
	}
}
