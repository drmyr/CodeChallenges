package heap;

import org.junit.jupiter.api.Test;

import static heap.ReorderDataInLogs.reorderLogs;
import static org.junit.jupiter.api.Assertions.*;

class ReorderDataInLogsTest {

    @Test
    void reorderLogsTest() {
        final String[] result1 = reorderLogs(new String[] {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"});

        final String[] answer1 = {"let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"};

        for(int i = 0; i < result1.length; i++) {
            assertEquals(answer1[i], result1[i]);
        }

        final String[] result2 = reorderLogs(new String[] {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"});

        final String[] answer2 = {"g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"};

        for(int i = 0; i < result2.length; i++) {
            assertEquals(answer2[i], result2[i]);
        }

    }
}