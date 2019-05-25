package week2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InverseBWTTest {

  @Test
  void inverseBWT() {
    assertThat(new InverseBWT().inverseBWT("AA$")).isEqualTo("AA$");
    assertThat(new InverseBWT().inverseBWT("CCCC$AAAA")).isEqualTo("ACACACAC$");
    assertThat(new InverseBWT().inverseBWT("ATG$CAAA")).isEqualTo("AGACATA$");
    assertThat(new InverseBWT().inverseBWT("AC$A")).isEqualTo("ACA$");
    assertThat(new InverseBWT().inverseBWT("AGGGAA$")).isEqualTo("GAGAGA$");
  }
}