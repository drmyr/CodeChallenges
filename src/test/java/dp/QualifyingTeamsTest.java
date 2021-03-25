package dp;

import org.junit.jupiter.api.Test;

import static dp.QualifyingTeams.howManyQualifyingTeams;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class QualifyingTeamsTest {

    @Test
    void howManyQualifyingTeamsTest() {
        assertThat(5, is(equalTo(howManyQualifyingTeams(6, new int[] {12,4,6,13,5,10}, 3, 4, 10))));
    }
}