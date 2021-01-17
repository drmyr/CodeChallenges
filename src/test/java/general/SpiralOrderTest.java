package general;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static general.SpiralOrder.spiralOrder;

class SpiralOrderTest {

    @Test
    void spiralOrderTest() {
        final int[][] spiralOrder1 = {
                {1,2,3},
                {4,5,6},
                {7,8,9}};
        assertThat(List.of(1,2,3,6,9,8,7,4,5), is(equalTo(spiralOrder(spiralOrder1))));

        final int[][] spiralOrder2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9,10,11,12}};
        assertThat(List.of(1,2,3,4,8,12,11,10,9,5,6,7), is(equalTo(spiralOrder(spiralOrder2))));

        final int[][] spiralOrder3 = {
                { 1, 2, 3, 4, 5},
                { 6, 7, 8, 9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}};
        assertThat(List.of(1,2,3,4,5,10,15,20,25,24,23,22,21,16,11,6,7,8,9,14,19,18,17,12,13), is(equalTo(spiralOrder(spiralOrder3))));
    }
}