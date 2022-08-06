package general;

import org.junit.jupiter.api.Test;

import static general.NumberGameMaxScoreAfterNOperations.maxScoreByGCD;
import static org.junit.jupiter.api.Assertions.*;

class NumberGameMaxScoreAfterNOperationsTest {

    @Test
    void maxScoreByGCDTest() {
        assertEquals(527, maxScoreByGCD(new int[] {109497,983516,698308,409009,310455,528595,524079,18036,341150,641864,913962,421869,943382,295019}));
        assertEquals(869, maxScoreByGCD(new int[] {697035,181412,384958,575458}));
        assertEquals(1, maxScoreByGCD(new int[] {1,2}));
        assertEquals(11, maxScoreByGCD(new int[] {3,4,6,8}));
        assertEquals(14, maxScoreByGCD(new int[] {1,2,3,4,5,6}));
    }
}