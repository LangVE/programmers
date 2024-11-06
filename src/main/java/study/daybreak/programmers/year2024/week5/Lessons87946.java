package study.daybreak.programmers.year2024.week5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
    코딩테스트 연습 > 완전탐색 > 피로도
    https://school.programmers.co.kr/learn/courses/30/lessons/87946

    문제 설명

    XX게임에는 피로도 시스템(0 이상의 정수로 표현합니다)이 있으며, 일정 피로도를 사용해서 던전을 탐험할 수 있습니다. 이때, 각 던전마다 탐험을 시작하기 위해 필요한 "최소 필요 피로도"와 던전 탐험을 마쳤을 때 소모되는 "소모 피로도"가 있습니다. "최소 필요 피로도"는 해당 던전을 탐험하기 위해 가지고 있어야 하는 최소한의 피로도를 나타내며, "소모 피로도"는 던전을 탐험한 후 소모되는 피로도를 나타냅니다. 예를 들어 "최소 필요 피로도"가 80, "소모 피로도"가 20인 던전을 탐험하기 위해서는 유저의 현재 남은 피로도는 80 이상 이어야 하며, 던전을 탐험한 후에는 피로도 20이 소모됩니다.

    이 게임에는 하루에 한 번씩 탐험할 수 있는 던전이 여러개 있는데, 한 유저가 오늘 이 던전들을 최대한 많이 탐험하려 합니다. 유저의 현재 피로도 k와 각 던전별 "최소 필요 피로도", "소모 피로도"가 담긴 2차원 배열 dungeons 가 매개변수로 주어질 때, 유저가 탐험할수 있는 최대 던전 수를 return 하도록 solution 함수를 완성해주세요.
    제한사항

        k는 1 이상 5,000 이하인 자연수입니다.
        dungeons의 세로(행) 길이(즉, 던전의 개수)는 1 이상 8 이하입니다.
            dungeons의 가로(열) 길이는 2 입니다.
            dungeons의 각 행은 각 던전의 ["최소 필요 피로도", "소모 피로도"] 입니다.
            "최소 필요 피로도"는 항상 "소모 피로도"보다 크거나 같습니다.
            "최소 필요 피로도"와 "소모 피로도"는 1 이상 1,000 이하인 자연수입니다.
            서로 다른 던전의 ["최소 필요 피로도", "소모 피로도"]가 서로 같을 수 있습니다.

    입출력 예
    k 	dungeons 	result
    80 	[[80,20],[50,40],[30,10]] 	3
    입출력 예 설명

    현재 피로도는 80입니다.

    만약, 첫 번째 → 두 번째 → 세 번째 던전 순서로 탐험한다면

        현재 피로도는 80이며, 첫 번째 던전을 돌기위해 필요한 "최소 필요 피로도" 또한 80이므로, 첫 번째 던전을 탐험할 수 있습니다. 첫 번째 던전의 "소모 피로도"는 20이므로, 던전을 탐험한 후 남은 피로도는 60입니다.
        남은 피로도는 60이며, 두 번째 던전을 돌기위해 필요한 "최소 필요 피로도"는 50이므로, 두 번째 던전을 탐험할 수 있습니다. 두 번째 던전의 "소모 피로도"는 40이므로, 던전을 탐험한 후 남은 피로도는 20입니다.
        남은 피로도는 20이며, 세 번째 던전을 돌기위해 필요한 "최소 필요 피로도"는 30입니다. 따라서 세 번째 던전은 탐험할 수 없습니다.

    만약, 첫 번째 → 세 번째 → 두 번째 던전 순서로 탐험한다면

        현재 피로도는 80이며, 첫 번째 던전을 돌기위해 필요한 "최소 필요 피로도" 또한 80이므로, 첫 번째 던전을 탐험할 수 있습니다. 첫 번째 던전의 "소모 피로도"는 20이므로, 던전을 탐험한 후 남은 피로도는 60입니다.
        남은 피로도는 60이며, 세 번째 던전을 돌기위해 필요한 "최소 필요 피로도"는 30이므로, 세 번째 던전을 탐험할 수 있습니다. 세 번째 던전의 "소모 피로도"는 10이므로, 던전을 탐험한 후 남은 피로도는 50입니다.
        남은 피로도는 50이며, 두 번째 던전을 돌기위해 필요한 "최소 필요 피로도"는 50이므로, 두 번째 던전을 탐험할 수 있습니다. 두 번째 던전의 "소모 피로도"는 40이므로, 던전을 탐험한 후 남은 피로도는 10입니다.

    따라서 이 경우 세 던전을 모두 탐험할 수 있으며, 유저가 탐험할 수 있는 최대 던전 수는 3입니다.

    ※ 공지 - 2022년 2월 25일 테스트케이스가 추가되었습니다.
* */
public class Lessons87946 {
    private int result = -1;
    private int energy = 0;

    public int solution(int k, int[][] dungeons) {
        this.energy = k;
        for (int i = 0; i < dungeons.length; i++) {
            List<int[]> resultList = new ArrayList<>();
            resultList.add(dungeons[i]);
            int finalI = i;
            List<int[]> waitList = IntStream.range(0, dungeons.length)
                    .filter(n -> n != finalI)
                    .mapToObj(n -> dungeons[n])
                    .collect(Collectors.toList());

            print(resultList, waitList);
        }

        return result;
    }

    private void print(List<int[]> resultList, List<int[]> waitList) {

        if (waitList != null && waitList.size() > 0) {
            for (int i = 0; i < waitList.size(); i++) {
                List<int[]> tmpResultList = new ArrayList<>(resultList);
                tmpResultList.add(waitList.get(i));
                int finalI = i;
                List tmpWaitList = IntStream.range(0, waitList.size())
                        .filter(n -> n != finalI)
                        .mapToObj(n -> waitList.get(n))
                        .collect(Collectors.toList());

                //System.out.println("i = " + i + ", resultList = " + tmpResultList + ", waitList = " + tmpWaitList);
                print(tmpResultList, tmpWaitList);
            }
        } else {
            //System.out.println("resultList = " + resultList);

            check(resultList);

        }
    }

    private void check(List<int[]> resultList) {
        int result = 0;
        int k = energy;

        for (int i = 0; i < resultList.size(); i++) {
            if (k >= resultList.get(i)[0]) {
                k = k - resultList.get(i)[1];
                result++;
            } else {
                break;
            }
        }

        if (result > this.result) {
            this.result = result;
        }
    }
}

