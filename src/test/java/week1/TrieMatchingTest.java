package week1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class TrieMatchingTest {

  @Test
  void solve() {
    assertThat(new TrieMatching().solve("AATCGGGTTCAATCGGGGT", 2, Arrays.asList("ATCG", "GGGT")))
        .containsOnly(1, 4, 11, 15);
    assertThat(new TrieMatching().solve("AA", 1, Arrays.asList("T"))).isEmpty();
    assertThat(new TrieMatching().solve("AAA", 1, Arrays.asList("AA"))).containsOnly(0, 1);
  }
}