package y25m04;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42840
 */
public class Day15_02 {
    public ArrayList<Integer> solution(int[] answers) {
        int[][] results = {{0,0},{1,0},{2,0}};

        int[][] players = {
                {1,2,3,4,5},
                {2,1,2,3,2,4,2,5},
                {3,3,1,1,2,2,4,4,5,5}
        };

        for(int i = 0 ; i < answers.length ; i++){
            results[0][1] += players[0][i%5] == answers[i] ? 1 : 0;
            results[1][1] += players[1][i%8] == answers[i] ? 1 : 0;
            results[2][1] += players[2][i%10] == answers[i] ? 1 : 0;

        }

        Arrays.sort(results, (o1, o2) -> {
            return o2[1]-o1[1];
        });

        ArrayList<Integer> result = new ArrayList<>();
        result.add(results[0][0]+1);
        if(results[0][1] == results[1][1])
            result.add(results[1][0]+1);

        if(results[0][1] == results[2][1])
            result.add(results[2][0]+1);

        return result;
    }
}
