package bowling;

import bowling.state.State;
import java.util.ArrayList;
import java.util.List;

public class LastFallDownPins implements State {

  private static final int LAST_FRAME_MAX_COUNT = 3;
  private static final int LAST_FRAME_STRIKE_AND_BONUS_SIZE = 2;
  private static final int NO_BONUS_SIZE = 2;
  private static final int FIRST_FALL_DOWN_COUNT_INDEX = 0;
  private static final int SECOND_FALL_DOWN_COUNT_INDEX = 1;
  private static final int BONUS_FALL_DOWN_COUNT_INDEX = 2;
  private static final int SPARE_SIZE = 2;
  private static final int NO_PLAY_SIZE = 0;

  List<FallDownPin> fallDownCounts = new ArrayList<>();

  public LastFallDownPins roll(int countOfPin) {
    if (!validateSumOfFallDownCount(countOfPin)) {
      throw new IllegalArgumentException("한 프레임에서 넘어뜨릴 수 있는 핀의 합은 10을 넘어 갈 수 없습니다.");
    }
    fallDownCounts.add(FallDownPin.of(countOfPin));
    return this;
  }


  private boolean validateSumOfFallDownCount(int fallDownCount) {
    if (isNoPlay()) {
      return true;
    }
    if (isStrike()) {
      return true;
    }
    if (isSpare()) {
      return true;
    }
    return getFirstFallDown().isValidCount(fallDownCount);
  }


  @Override
  public Boolean isFinish() {
    if (isNoPlay()) {
      return false;
    }
    if (isMaxCount()) {
      return true;
    }
    if (isStrikeAndBonus()) {
      return true;
    }
    if (isNoBonus()) {
      return true;
    }
    return false;
  }

  private boolean isNoPlay() {
    return fallDownCounts.size() == NO_PLAY_SIZE;
  }

  private boolean isMaxCount() {
    return fallDownCounts.size() == LAST_FRAME_MAX_COUNT;
  }

  private boolean isStrikeAndBonus() {
    return isStrike() && isStrikeBonusSize();
  }

  private boolean isStrike() {
    return getFirstFallDown().isStrike();
  }

  private boolean isStrikeBonusSize() {
    return fallDownCounts.size() == LAST_FRAME_STRIKE_AND_BONUS_SIZE;
  }

  private boolean isNoBonus() {
    return !isStrike() && !isSpare() && isNoBonusSize();
  }

  private boolean isSpare() {
    if (fallDownCounts.size() < SPARE_SIZE) {
      return false;
    }
    return getFirstFallDown().isSpare(getSecondFallDown());
  }

  private FallDownPin getFirstFallDown() {
    return fallDownCounts.get(FIRST_FALL_DOWN_COUNT_INDEX);
  }

  private FallDownPin getSecondFallDown() {
    return fallDownCounts
        .get(SECOND_FALL_DOWN_COUNT_INDEX);
  }

  private boolean isNoBonusSize() {
    return fallDownCounts.size() == NO_BONUS_SIZE;
  }

  private String getBonus() {
    return fallDownCounts.get(BONUS_FALL_DOWN_COUNT_INDEX).toString();
  }

  @Override
  public String toString() {
    if (isStrikeAndBonus()) {
      return "X|" + getSecondFallDown().toString();
    }
    if (isStrike()) {
      return "X";
    }
    if (isSpare() && fallDownCounts.size() == SPARE_SIZE) {
      return getFirstFallDown().toString() + "|" + "/";
    }
    if (isSpare()) {
      return getFirstFallDown().toString() + "|" + "/" + "|" + getBonus();
    }
    if (fallDownCounts.size() == 1) {
      return getFirstFallDown().toString();
    }
    return getFirstFallDown().toString() + "|" + getSecondFallDown().toString();
  }
}
