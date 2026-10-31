class Solution {
    public int[] solution(int brown, int yellow) {
        int x = 2;
        
        while(true) {
            int tmp = 2*x*x-(brown+4)*x+2*(brown+yellow);
            if(tmp == 0) {
                break;
            }
            x++;
        }       
        
        int y = (brown+yellow)/x;
        return new int[]{Math.max(x, y), Math.min(x,y)};
    }
}