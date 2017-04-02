package com.sickpillow.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sickpillow.models.HalfBean;

public class HLTVHelper {
	public static HalfBean getHalfDetailsWithHltvDoc(Document hltvDoc, int whichHalf) {
		//Elements all4scores = hltvDoc.select("div[style*=display:flex;flex-direction: row;justify-content:]");
        //this gets the first and second half sections
        Elements halfSections = hltvDoc.select("div[style=width:49%]");
        
        HalfBean half1 = new HalfBean();
        HalfBean half2 = new HalfBean();
        
        int currentHalf = 1;
        for (Element halfSection : halfSections) {
        	Elements skullRows = halfSection.select("div[style*=display:flex;flex-direction: row;justify-content: space-between;height:25px;]");
        	
        	int currentRow = 1;
            for (Element skullRow : skullRows) {
            	Elements links = skullRow.select("[src]");
            	
            	int currentSkull = 1;
            	for (Element currentSkullElm : links) {
            		String currentSkullString = currentSkullElm.attr("abs:src").toString();
            		
            		Pattern pattern = Pattern.compile("http://static.hltv.org/images/scoreboard/(.*?).svg");
            		Matcher matcher = pattern.matcher(currentSkullString);
            		matcher.find();
            		
            		String roundResult = matcher.group(1).trim();
            		
            		if (currentHalf == 1) {
            			if (!roundResult.equals("emptyHistory")) {
            				half1.setResultForRound(roundResult, currentSkull);
            			}
            		}else {
            			if (!roundResult.equals("emptyHistory")) {
            				half2.setResultForRound(roundResult, currentSkull);
            			}
            		}
            		
            		currentSkull++;
            	}
            	currentRow++;
            }
        
            currentHalf++;
        }
        Element team1HalfScoreElm;
        Element team2HalfScoreElm;
        
        Element halfScoresElm = hltvDoc.select("div[style=font-weight:normal;width:180px;float:left;text-align:right;]").get(1);
        
        if (whichHalf == 1) {
        	team1HalfScoreElm = halfScoresElm.select("span").get(2);
        	team2HalfScoreElm = halfScoresElm.select("span").get(3);
        	
        	
        }else {
        	team1HalfScoreElm = halfScoresElm.select("span").get(4);
        	team2HalfScoreElm = halfScoresElm.select("span").get(5);
        }
        
    	String team1HalfScoreString = team1HalfScoreElm.ownText();
    	int team1HalfScore = Integer.parseInt(team1HalfScoreString);
    	
    	String team2HalfScoreString = team2HalfScoreElm.ownText();
    	int team2HalfScore = Integer.parseInt(team2HalfScoreString);
        
        
    	
    	if (whichHalf == 1) {
    		half1.setTeam1Score(team1HalfScore);
        	half1.setTeam2Score(team2HalfScore);
    		
    		return half1;
    	}else {
    		half2.setTeam1Score(team1HalfScore);
        	half2.setTeam2Score(team2HalfScore);
    		
    		return half2;
    	}
	}
}
