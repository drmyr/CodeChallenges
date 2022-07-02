package general;

import java.util.ArrayList;
import java.util.List;

public class ValidIpAddresses {

    public List<String> validIPAddresses(final String string) {
        final List<String> result = new ArrayList<>();

        if(string.length() < 4) return result;

        int firstDot = 0, secondDot = 1, thirdDot = 2;
        while(firstDot < string.length() - 3) {
            final String firstOctet = string.substring(0, firstDot + 1);
            final String secondOctet = string.substring(firstDot + 1, secondDot + 1);
            final String thirdOctet = string.substring(secondDot + 1, thirdDot + 1);
            final String fourthOctet = string.substring(thirdDot + 1);
            final String ip = firstOctet + "." + secondOctet + "." + thirdOctet + "." + fourthOctet;

            if(isValidOctet(firstOctet) && isValidOctet(secondOctet) && isValidOctet(thirdOctet) && isValidOctet(fourthOctet)) {
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

    private boolean isValidOctet(final String octet) {
        if(octet.charAt(0) == '0' && octet.length() > 1) {
            return false;
        }
        return Integer.parseInt(octet) < 256;
    }
}
