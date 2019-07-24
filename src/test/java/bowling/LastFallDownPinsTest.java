package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LastFallDownPinsTest {

  LastFallDownPins lastFallDownPins;

  @BeforeEach
  void setup() {
    lastFallDownPins = new LastFallDownPins();
  }

  @Test
  void 한번도_안굴리면_마지막프레임은_안끝난다() {
    assertThat(lastFallDownPins.isLastFrameFinish()).isFalse();
  }

  @Test
  void 한번만_굴리면_마지막프레임은_안끝난다() {
    assertThat(lastFallDownPins.roll(5).isLastFrameFinish()).isFalse();
  }

  @Test
  void 마지막_프레임은_스트라이크면_한번더_투구할수있다() {
    assertThat(lastFallDownPins.roll(10).roll(6).isLastFrameFinish()).isEqualTo(true);
  }

  @Test
  void 마지막_프레임은_스페어하면_한번더_투구할수있다() {
    assertThat(lastFallDownPins.roll(3).roll(7).roll(6).isLastFrameFinish()).isEqualTo(true);
  }

  @Test
  void 스트라이크나_스페어못하면_보너스가_없다() {
    assertThat(lastFallDownPins.roll(3).roll(6).isLastFrameFinish()).isEqualTo(true);
  }

}
