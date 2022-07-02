package general;

import java.util.ArrayList;

public class ValidIpAddresses {

    public ArrayList<String> validIPAddresses(String string) {
        if(string.length() < 4) return new ArrayList<>();

        ArrayList<String> result = new ArrayList<>();

        int firstDot = 0;
        int secondDot = 1;
        int thirdDot = 2;
        while(firstDot < string.length() - 3) {
            String firstOctet = string.substring(0, firstDot + 1);
            String secondOctet = string.substring(firstDot + 1, secondDot + 1);
            String thirdOctet = string.substring(secondDot + 1, thirdDot + 1);
            String fourthOctet = string.substring(thirdDot + 1, string.length());
            String ip = firstOctet + "." + secondOctet + "." + thirdOctet + "." + fourthOctet;

            if(acceptable(firstOctet) && acceptable(secondOctet) && acceptable(thirdOctet) && acceptable(fourthOctet)) {
                result.add(ip);
            }

            thirdDot++;
            if(thirdDot == string.length() - 1) {
                secondDot++;
                thirdDot = secondDot + 1;
            }
            if(secondDot == string.length() - 2) {
                firstDot++;
                secondDot = firstDot + 1;
                thirdDot = secondDot + 1;
            }
        }

        return result;
    }

    private boolean acceptable(String octet) {
        int asInt = Integer.valueOf(octet);
        if(octet.charAt(0) == '0' && octet.length() > 1) {
            return false;
        }
        if(asInt > 255) {
            return false;
        }
        return true;
    }
}
