import java.util.*;
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);
        
        int L = A.length;
        for(int i = 0 ; i < L ; i++) {
            answer += A[i] * B[L-1-i];
        }

        return answer;
    }
}