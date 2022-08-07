package dp;

import org.junit.jupiter.api.Test;

import static dp.JumpGameII.jumpRecDFS;

class JumpGameIITest {

    @Test
    void jumpRecTest() {
        System.out.println("HERE" + jumpRecDFS(new int[] {2,3,1,1,4}));
    }
}