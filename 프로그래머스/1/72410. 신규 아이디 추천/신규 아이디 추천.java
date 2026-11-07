class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        new_id = new_id.toLowerCase();
        
        for(char c : new_id.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                answer += c;
            } else if(c >= '0' && c <= '9') {
                answer += c;
            } else if(c == '-' || c == '_' || c == '.') {
                answer += c;
            }
        }
        
        String tmp = "";
        for(char c : answer.toCharArray()) {
            if (c != '.') {
                tmp += c;
            } else {
                if(tmp.length() == 0 || (tmp.length() > 0 && tmp.charAt(tmp.length()-1) != '.')) {
                    tmp += c;
                }
            }
        }
        
        if (tmp.length() == 1) {
            if (tmp.charAt(0) == '.') {
                answer = "";
            } else {
                answer = tmp;
            }
        } else if (tmp.length() > 1) {
            if (tmp.charAt(0) == '.' && tmp.charAt(tmp.length() - 1) == '.') {
                answer = tmp.substring(1, tmp.length() - 1);
            } else if (tmp.charAt(0) == '.') {
                answer = tmp.substring(1, tmp.length());
            } else if (tmp.charAt(tmp.length() - 1) == '.') {
                answer = tmp.substring(0, tmp.length() - 1);
            } else {
                answer = tmp;
            }
        }
        
        if (answer.length() == 0)
            answer = "a";
        
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
            if(answer.charAt(14) == '.')
                answer = answer.substring(0, 14);
        }
        
        if (answer.length() <= 2) {
            char t = answer.charAt(answer.length() - 1);
            while(answer.length() < 3) {
                answer += t;
            }
        }
        
        return answer;
    }
}