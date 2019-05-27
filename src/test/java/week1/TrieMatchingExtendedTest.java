package week1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TrieMatchingExtendedTest {

    @Test
    void solve() {
        assertThat(new TrieMatchingExtended().solve("AAA", 2, Arrays.asList("AA", "A"))).containsOnly(0, 1, 2);
        assertThat(new TrieMatchingExtended().solve("AAA", 1, Arrays.asList("AA"))).containsOnly(0, 1);
        assertThat(new TrieMatchingExtended().solve("AA", 1, Arrays.asList("T"))).isEmpty();
        assertThat(new TrieMatchingExtended().solve("AATCGGGTTCAATCGGGGT", 2, Arrays.asList("ATCG", "GGGT")))
                .containsOnly(1, 4, 11, 15);
        assertThat(new TrieMatchingExtended().solve("ACATA", 3, Arrays.asList("AT", "A", "AG"))).containsOnly(0, 2, 4);
    }
}