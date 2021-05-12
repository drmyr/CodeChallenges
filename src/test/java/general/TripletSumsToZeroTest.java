package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.TransactionLogs.processLogFile;
import static general.TripletSumsToZero.findAllTripletsThatSumToZero;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TripletSumsToZeroTest {

    @Test
    void findAllTripletsThatSumToZeroTest() {
        assertThat(
            findAllTripletsThatSumToZero(new int[] {0, -1, 2, -3, 1}),
            Matchers.arrayContainingInAnyOrder(new int[] {-1, 0, 1}, new int[] {-3, 1, 2})
        );
    }
}