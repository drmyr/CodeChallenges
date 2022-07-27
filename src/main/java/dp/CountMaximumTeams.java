package dp;

import java.util.Collections;
import java.util.List;

public class CountMaximumTeams {

    /**
     * https://cybergeeksquad.co/2022/02/count-maximum-teams-amazon-oa.html
     *
     * Amazon is hosting a team hackathon.
     *
     *     Each team will have exactly teamSize developers.
     *     A developer’s skill level is denoted by skill[i].
     *     The difference between the maximum and minimum skill levels within a team cannot exceed a threshold, maxDiff.
     *     Determine the maximum number of teams that can be formed from the contestants.
     *
     * Example
     * skill = [3, 4, 3, 1, 6, 5]
     * team5ize = 3
     * maxDiff = 2
     *
     * At most, 2 teams can be formed: [3, 3, 1] and [4, 6, 5].
     * The difference between the maximum and minimum skill levels is 2 in each case,
     * which does not exceed the threshold value oft.
     *
     * Function Description
     * Complete the function countMaximumTeams in the editor below.
     *
     * countMaximumTeams has the following parameter(s):
     *
     *     int skill[n]: the developers’ skill levels
     *     int teamSize: the number of developers to make up a team
     *     int maxDiff: the threshold value
     *
     * Returns:
     *
     *     int: the maximum number of teams that can be formed at one time
     *
     * credit unknown
     * @return
     */
    public static int countMaxTeams(final List<Integer> skills, final int teamSize, final int maxDiff) {
        Collections.sort(skills);
        int result = 0;

        for(int i = 0; i <= skills.size() - teamSize; ) {
            if(skills.get(i + 2) - skills.get(i) <= maxDiff) {
                result++;
                i += teamSize;
            } else {
                i++;
            }
        }
        return result;
    }

    /**
     * credit unknown
     */
    public static int countMaxTeamsTwo(final List<Integer> skills, final int teamSize, final int maxDiff) {
        if(teamSize == 1)
            return skills.size();
        Collections.sort(skills);
        int teams = 0;
        int leastSkilled = 0;
        for(int i = 1; i < skills.size(); ++i) {
            while(skills.get(i) - skills.get(leastSkilled) > maxDiff) {
                ++leastSkilled;
            }
            if(i - leastSkilled + 1 == teamSize) {
                ++teams;
                leastSkilled = i + 1;
            }
        }
        return teams;
    }

    /**
     * all me baby
     */
    public static int countMaxTeamsThree(final List<Integer> skills, final int teamSize, final int maxDiff) {
        if(teamSize == 1) {
            return skills.size();
        }

        Collections.sort(skills);

        int rearAnchor = 0;
        int teamCount = 0;
        for(int i = 1; i < skills.size(); i++) {
            while(i < skills.size() && skills.get(i) - skills.get(rearAnchor) <= maxDiff) {
                i++;
            }
            final int span = i - rearAnchor;
            if((skills.get(i - 1) - skills.get(rearAnchor)) <= maxDiff && span >= teamSize) {
                teamCount++;
                // if the span is larger than the team size, we need to move i back to shrink it back down.
                while((i - rearAnchor) > teamSize) i--;
            }
            rearAnchor = i;
        }

        return teamCount;
    }
}
