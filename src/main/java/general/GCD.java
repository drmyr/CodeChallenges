package general;

public class GCD {

    public static int gcdByEuclidsAlgorithm(final int n1, final int n2) {
        int temp1 = n1;
        int temp2 = n2;
        while(temp2 != 0) {
            final int mod = temp1 % temp2;
            temp1 = temp2;
            temp2 = mod;
        }
        return temp1;
    }

    public static int gcdByEuclidsAlgorithmRecursive(final int n1, final int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcdByEuclidsAlgorithmRecursive(n2, n1 % n2);
    }
}
