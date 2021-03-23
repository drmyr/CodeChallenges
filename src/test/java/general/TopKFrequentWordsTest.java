package general;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static general.PairsOfSongs.numPairsDivisibleBy60;
import static general.TopKFrequentWords.getTopGames;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TopKFrequentWordsTest {

    @Test
    void getTopGamesTest() {
        final Set<String> games = Set.of("osmo","uno","playmonster","lcr","buzzed","pieface");
        final Set<String> reviews = Set.of(
            "the new osmo super fun",
            "Osmo hottest of season, osmo on list!",
            "expect playmonster drone things playmonster",
            "Uno is fam tradition",
            "Playmonster and osmo games holiday grouping, playmonster good",
            "parents old kids, buy LCR",
            "PieFace become popular."
        );
        assertThat(Set.of("osmo", "playmonster"), is(equalTo(getTopGames(6, 2, games, 7, reviews))));
    }
}