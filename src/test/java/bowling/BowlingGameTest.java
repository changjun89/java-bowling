package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {

  BowlingGame bowlingGame;

  @BeforeEach
  void 생성() {
    bowlingGame = new BowlingGame();
  }

  @Test
  void 스트라이크_다음_프레임이_리턴된다() {
    assertThat(bowlingGame.roll(10).getFrameNo()).isEqualTo(2);
  }

  @Test
  void 스트라이크가_아니면_두번의_roll이후_다음_프레임이_리턴된다() {
    assertThat(bowlingGame.roll(4).getFrameNo()).isEqualTo(1);
    assertThat(bowlingGame.roll(3).getFrameNo()).isEqualTo(2);
  }

  @Test
  void 마지막10프레임이_끝이나면_게임이_끝난다() {
    assertThat(bowlingGame.roll(10).isGameEnd()).isEqualTo(false);

    bowlingGame.roll(10);
    bowlingGame.roll(10);
    bowlingGame.roll(10);
    bowlingGame.roll(10);
    bowlingGame.roll(10);
    bowlingGame.roll(10);
    bowlingGame.roll(10);

    assertThat(bowlingGame.roll(4).roll(5).isGameEnd()).isTrue();
  }

  @Test
  void 프레임에대한_결과를_저장한다() {
    bowlingGame.roll(5);
    assertThat(bowlingGame.getResult().result(1)).isEqualTo("5");
    bowlingGame.roll(5);
    assertThat(bowlingGame.getResult().result(1)).isEqualTo("5|/");

    bowlingGame.roll(10);
    assertThat(bowlingGame.getResult().result(2)).isEqualTo("X");

    bowlingGame.roll(3);
    assertThat(bowlingGame.getResult().result(3)).isEqualTo("3");
    bowlingGame.roll(5);
    assertThat(bowlingGame.getResult().result(3)).isEqualTo("3|5");

    bowlingGame.roll(10);
    bowlingGame.roll(10);
    bowlingGame.roll(10);
    bowlingGame.roll(10);
    bowlingGame.roll(10);
    bowlingGame.roll(10);

    bowlingGame.roll(3);
    bowlingGame.roll(7);
    bowlingGame.roll(7);

    assertThat(bowlingGame.getResult().result(10)).isEqualTo("3|/|7");

  }
}
