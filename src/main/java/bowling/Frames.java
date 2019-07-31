package bowling;

import static java.util.stream.Collectors.toList;

import java.util.LinkedList;
import java.util.List;

public class Frames {

  private static final int LIST_INDEX_AND_FRAME_NO_DIFF = 1;

  private LinkedList<Frame> frames = new LinkedList<>();

  public Frames() {
    frames.add(NormalFrame.first());
  }

  public Frames bowl(int countOfPin) {
    Frame frame = currentFrame().bowl(new Pins(countOfPin));
    if (!isCurrentFrame(frame)) {
      frames.add(frame);
    }
    return this;
  }

  public Frame currentFrame() {
    return frames.getLast();
  }

  public int currentFrameNo() {
    return frames.getLast().frameNo();
  }

  private boolean isCurrentFrame(Frame frame) {
    return frame.equals(currentFrame());
  }

  public String desc(int frameNo) {
    return frames.get(frameNo - LIST_INDEX_AND_FRAME_NO_DIFF).desc();
  }

  public Score score(int frameNo) {
    return frames.get(frameNo - LIST_INDEX_AND_FRAME_NO_DIFF).getScore();
  }

  public GameResult getResult() {
    List<FrameResult> collect = frames.stream()
        .map(frame -> new FrameResult(frame.desc(), frame.getScore().getScore()))
        .collect(toList());
    return new BowlingGameResult(collect);
  }

  public boolean isGameEnd() {
    return currentFrame().isGameEnd();
  }

}
