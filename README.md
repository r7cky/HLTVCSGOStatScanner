# HLTVCSGOStatScanner

This project scans all the CSGO matches for big events in 2016 using HLTV's match statistics.

These are the statistcs the project currently prints out:

- Chances of CT winning next two after winning pistol with no bomb defuse on pistol round
- Chances of CT winning next two after winning pistol with bomb defuse on pistol round
- Chances of T winning next two after winning pistol
- Chances of winning following two rounds after pistol win (overall)
- Chances of winning two consecutive rounds after losing first three (round 4&5)
- Chances of winning first half (greater than or equal to 8 rounds) if first three rounds are won
- The average amount of rounds won in the first half when a team wins first three rounds + the variance
- Chances of winning the first three rounds of both halves
- Chances of winning the first three rounds of both halves with clean pistol wins (no bomb plant for Ts = no extra money to force)
- Win/Loss/Overtime statistics for when a team wins the first three rounds of both halves

You can change the filters within HLTV, then change the link in the main class and it will work, try it yourself!

Here is a link to get you started (http://www.hltv.org/?pageid=188&statsfilter=0), just change the filters at the top of the page then change the link in the main class. Keep in mind; the more filters you use the less matches will show. This can change the data in many ways.

GLHF!
