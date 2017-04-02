package com.sickpillow.helpers;

public class Helper {
	public static boolean containsIgnoreCase(String src, String what) {
		final int length = what.length();
		if (length == 0)
			return true; // Empty string is contained

		final char firstLo = Character.toLowerCase(what.charAt(0));
		final char firstUp = Character.toUpperCase(what.charAt(0));

		for (int i = src.length() - length; i >= 0; i--) {
			// Quick check before calling the more expensive regionMatches() method:
			final char ch = src.charAt(i);
			if (ch != firstLo && ch != firstUp)
				continue;

			if (src.regionMatches(true, i, what, 0, length))
				return true;
		}

		return false;
	}

	public static double getWinRate(int wins, int losses) {
		int total = wins+losses;
		double winRate = (double)wins /total;

		return winRate;
	}

	public static double getStandardDeviationFromWinsAndLosses(int wins, int losses) {
		int total = wins+losses;
		double winRate = (double)wins /total;
		double loseRate = (double)1-winRate;
		double standardDeviation = Math.sqrt(winRate * loseRate/total);

		return standardDeviation;
	}

	public static double get95ConfidenceIntervalFromWinsAndLosses(int wins, int losses) {
		double standardDeviation = getStandardDeviationFromWinsAndLosses(wins, losses);

		return 1.96 * standardDeviation;
	}

	public static int time100ThenRound(double doubleParam) {
		return (int) Math.round(doubleParam * 100);
	}
	
	public static void printRatioAndStdAnd95ConfidenceForWinsAndLosses(int wins, int losses) {		
		int total = wins+losses;
		int winDifference = total-wins;
		int lossDifference = total-losses;

		double winRatio = (double) wins/total;
		double lossRatio = (double) losses/total;
		
		double winVariance = total*(winRatio)*((double) winDifference/total);
		double lossVariance = total*(lossRatio)*((double) lossDifference/total);

		double winStd = Math.sqrt(winVariance);
		double lossStd = Math.sqrt(lossVariance);
		
		double winStdPerc = (double) winStd/total;
		double lossStdPerc = (double) lossStd/total;

		double win95 = (double) 1.96 * winStdPerc;
		double loss95 = (double) 1.96 * lossStdPerc;
		
		String winString = String.format("Win | %s | %s±%s | %s±%s", wins, Helper.time100ThenRound(winRatio), Helper.time100ThenRound(winStdPerc), Helper.time100ThenRound(winRatio), Helper.time100ThenRound(win95));
		String loseString = String.format("Loss | %s | %s±%s | %s±%s", losses, Helper.time100ThenRound(lossRatio), Helper.time100ThenRound(lossStdPerc), Helper.time100ThenRound(lossRatio), Helper.time100ThenRound(loss95));
		
		System.out.println("Aggregate | Occurrences | [%], std | 95% conf");
		System.out.println(winString);
		System.out.println(loseString);
	}
	
	public static void printRatioAndStdAnd95ConfidenceForWinsLossesOvertime(int wins, int losses, int overtime) {
		int total = wins+losses+overtime;
		int winDifference = total-wins;
		int lossDifference = total-losses;
		int overtimeDifference = total-overtime;

		double winRatio = (double) wins/total;
		double lossRatio = (double) losses/total;
		double overTimeRatio = (double) overtime/total;
		
		double winVariance = total*(winRatio)*((double) winDifference/total);
		double lossVariance = total*(lossRatio)*((double) lossDifference/total);
		double overtimeVariance = total*(overTimeRatio)*((double) overtimeDifference/total);

		double winStd = Math.sqrt(winVariance);
		double lossStd = Math.sqrt(lossVariance);
		double overtimeStd = Math.sqrt(overtimeVariance);

		double winStdPerc = (double) winStd/total;
		double lossStdPerc = (double) lossStd/total;
		double overtimeStdPerc = (double) overtimeStd/total;

		double win95 = (double) 1.96 * winStdPerc;
		double loss95 = (double) 1.96 * lossStdPerc;
		double overtime95 = (double) 1.96 * overtimeStdPerc;

		String winString = String.format("Win | %s | %s±%s | %s±%s", wins, Helper.time100ThenRound(winRatio), Helper.time100ThenRound(winStdPerc), Helper.time100ThenRound(winRatio), Helper.time100ThenRound(win95));
		String loseString = String.format("Loss | %s | %s±%s | %s±%s", losses, Helper.time100ThenRound(lossRatio), Helper.time100ThenRound(lossStdPerc), Helper.time100ThenRound(lossRatio), Helper.time100ThenRound(loss95));
		String overtimeString = String.format("Overtime | %s | %s±%s | %s±%s", overtime, Helper.time100ThenRound(overTimeRatio), Helper.time100ThenRound(overtimeStdPerc), Helper.time100ThenRound(overTimeRatio), Helper.time100ThenRound(overtime95));


		System.out.println("Aggregate | Maps | [%], 1std | 95% conf");
		System.out.println(winString);
		System.out.println(loseString);
		System.out.println(overtimeString);
	}
}
