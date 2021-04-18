package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static general.QuestionOne.productOfArray;
import static general.SpiralOrder.spiralOrder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionOneTest {

    @Test
    void productOfArrayTest() {
        final int[] test1 = new int[] {4, 5, 0, 7, 8, 0, 6, 5};
        final int[] ans = new int[test1.length];
        for(final int value : productOfArray(test1)) {
            if(value != 0) {
                throw new IllegalArgumentException();
            }
        }
    }
}