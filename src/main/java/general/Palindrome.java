package general;

public class Palindrome {

    public static boolean isPalindrome(final String input) {
        int left = 0, right = input.length() - 1;
        while(left < right) {
            if(input.charAt(left) == input.charAt(right)) {
                left++; right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
