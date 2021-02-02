package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.TransactionLogs.processLogFile;
import static org.hamcrest.MatcherAssert.assertThat;

class TransactionLogsTest {

    @Test
    void processLogFileTest() {
        assertThat(processLogFile(new String[] {"88 99 200", "88 99 300", "99 32 100", "12 12 15"}, 2), Matchers.arrayContainingInAnyOrder("88", "99"));
    }
}