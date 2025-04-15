package y25m04;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/86491
 */
public class Day15_01 {
    public int solution(int[][] sizes) {
        int w = 0;
        int h = 0;

        for(int i = 0 ; i < sizes.length ; i++){
            if(sizes[i][0] > sizes[i][1]){
                w = w < sizes[i][0] ? sizes[i][0] : w;
                h = h < sizes[i][1] ? sizes[i][1] : h;
            }else{
                w = w < sizes[i][1] ? sizes[i][1] : w;
                h = h < sizes[i][0] ? sizes[i][0] : h;
            }

        }


        return w*h;
    }
}
