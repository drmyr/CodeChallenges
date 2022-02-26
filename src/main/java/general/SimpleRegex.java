package general;

public class SimpleRegex {

    public static boolean solution(final String regExp, final String str) {
        if(regExp == null || str == null) return false;
        if(regExp.length() > str.length()) return false;

        //TODO backtracking in the case of multiple wildcards
        int currentMatchIndex = 0, i = 0;
        char currentMatch = regExp.charAt(currentMatchIndex);
        while(i < str.length()) {
            if(currentMatch == str.charAt(i) || currentMatch == '.') {
                currentMatchIndex++;
                if(currentMatchIndex >= regExp.length()) {
                    return true;
                }
                currentMatch = regExp.charAt(currentMatchIndex);
                i++;
            } else {
                currentMatchIndex = 0;
                currentMatch = regExp.charAt(currentMatchIndex);
                if(currentMatch != str.charAt(i) || currentMatch != '.') {
                    i++;
                }
            }
        }

        return false;
    }
}
