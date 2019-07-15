# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

#기능요구사항
* 핀은 한번에 0 ~ 10개 쓰러진다
* 핀이 한번에 10개 넘어가면 한 프레임이 끝난다
* 핀을 두번 쓰러뜨리면 프레임이 끝난다. (10프레임 제외)
* 넘어진 핀의 합이 10을 넘을 수 없다(10프레임제외)

* 첫번째 ROLL에서 10개를 넘어뜨리면 다음 프레임이 리턴된다
* 두번 ROLL하면 다음 프레임이 리턴된다

* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다
* 10 프레임에 스트라이크나 스페어 하지 못하면 보너스 투구가 없다
* 9 프레임이 끝이나면 LastFrame이 리턴된다
* 10프레임이 끝이나면 게임이 끝이난다