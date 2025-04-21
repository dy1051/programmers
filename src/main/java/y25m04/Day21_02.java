package y25m04;

import java.util.ArrayList;
import java.util.Collections;

public class Day21_02 {
    public ArrayList<Integer> solution(int[] array, int[][] commands) {
        ArrayList<Integer> answer = new ArrayList<>();

        for(int[] command:commands){
            int i = command[0];
            int j = command[1];
            int k = command[2];

            ArrayList<Integer> list = new ArrayList<>();
            for(int v=i-1; v<j; v++){
                list.add(array[v]);
            }
            Collections.sort(list);
            answer.add(list.get(k-1));
        }

        return answer;
    }
}
