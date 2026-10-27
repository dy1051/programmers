import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int[] dist = new int[words.length];
        Arrays.fill(dist, -1);

        Deque<Integer> queue = new ArrayDeque<>();

        // begin에서 한 번에 변환 가능한 단어들을 넣기
        for (int i = 0; i < words.length; i++) {
            if (canChange(begin, words[i])) {
                dist[i] = 1;
                queue.offerLast(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.pollFirst();

            if (words[now].equals(target)) {
                return dist[now];
            }

            for (int i = 0; i < words.length; i++) {
                if (dist[i] != -1) continue;
                if (!canChange(words[now], words[i])) continue;

                dist[i] = dist[now] + 1;
                queue.offerLast(i);
            }
        }

        return 0;
    }

    boolean canChange(String a, String b) {
        int count = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (++count > 1) return false;
            }
        }

        return count == 1;
    }
}