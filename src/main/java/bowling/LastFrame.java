package bowling;

public class LastFrame implements Frame {

  private static final int LAST_FRAME_NO = 10;

  private LastFallDownPins lastFallDownPins;

  public LastFrame roll(int countOfPin) {
    if (lastFallDownPins == null) {
      lastFallDownPins = LastFallDownPins.first(countOfPin);
      return this;
    }
    lastFallDownPins = lastFallDownPins.roll(countOfPin);
    return this;
  }

  public boolean isFinish() {
    return lastFallDownPins.isLastFrameFinish();
  }

  @Override
  public boolean isGameEnd() {
    return isFinish();
  }

  @Override
  public int getFrameNo() {
    return LAST_FRAME_NO;
  }
}
