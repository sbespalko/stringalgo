package week2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BurrowsWheelerTransformTest {

  @Test
  void BWT() {
    assertThat(new BurrowsWheelerTransform().BWT("AA$")).isEqualTo("AA$");
    assertThat(new BurrowsWheelerTransform().BWT("ACACACAC$")).isEqualTo("CCCC$AAAA");
    assertThat(new BurrowsWheelerTransform().BWT("AGACATA$")).isEqualTo("ATG$CAAA");
  }
}