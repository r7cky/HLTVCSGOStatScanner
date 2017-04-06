package com.sickpillow.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sickpillow.helpers.HLTVHelper;
import com.sickpillow.helpers.Helper;
import com.sickpillow.models.HalfBean;
import com.sickpillow.models.MatchBean;

public class Scanner {
	final private static String USERAGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";
	final private static String REFERRER = "http://www.google.com";
	
	public static List<String> getMatchesWithHltvLink(String hltvLink) throws IOException {
		//if link doesn't start with hltv then return null
		if (!hltvLink.startsWith("http://www.hltv.org") || hltvLink.isEmpty() || hltvLink == null) {
			System.out.println("Please enter a valid hltv link!");
			return null;
		}
		
		List<String> hltvLinkList = new ArrayList<>();

		int currentPage = 0;
		while (true) {
			String currentHltvLink = hltvLink + "&offset=" + currentPage;
			System.out.printf("Fetching matches (%s)\n", currentHltvLink);
			Document hltvDoc = Jsoup.connect(currentHltvLink).userAgent(USERAGENT).referrer(REFERRER).timeout(30*1000).get();
			
			Elements links = hltvDoc.select("a[href]");

			int currentLink = 0;
			for(Element link : links) {

				String linkString = link.attr("abs:href");

				if (Helper.containsIgnoreCase(linkString, "matchid")) {
					hltvLinkList.add(linkString);
					currentLink++;
				}
			}
			
			//if no links were looped through in the previous for loop, that means we reached the end so break the while loop.
			if (currentLink == 0) {
				break;
			}
			
			currentPage += 50;
		}

		return hltvLinkList;
	}
	
	public static MatchBean scanHltvMatch(String hltvLink) throws IOException {
		//if link doesn't start with hltv then return null
		if (!hltvLink.startsWith("http://www.hltv.org") || hltvLink.isEmpty() || hltvLink == null) {
			System.out.println("Please enter a valid hltv link!");
			return null;
		}

		System.out.printf("Fetching match (%s)\n", hltvLink);
		Document hltvDoc = Jsoup.connect(hltvLink).userAgent(USERAGENT).referrer(REFERRER).timeout(30*1000).get();

		HalfBean half1 = HLTVHelper.getHalfDetailsWithHltvDoc(hltvDoc, 1);
		HalfBean half2 = HLTVHelper.getHalfDetailsWithHltvDoc(hltvDoc, 2);

		MatchBean matchBean = new MatchBean(hltvLink, half1, half2);
		return matchBean;
	}
	
	public static List<MatchBean> matchBeansFromLinkList(List<String> hltvLinkList) throws IOException {
		List<MatchBean> matchBeans = new ArrayList<>();

		for (String hltvLink : hltvLinkList) {
			MatchBean matchBean = scanHltvMatch(hltvLink);

			if (!matchBean.isFirstHalfTotalUnder15()) {
				matchBeans.add(matchBean);
			}
		}

		return matchBeans;
	}

	

	
}
