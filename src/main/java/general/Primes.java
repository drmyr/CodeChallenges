package general;

public class Primes {

    /**
     * https://mkyong.com/java/how-to-determine-a-prime-number-in-java/
     *
     *      "With some more efficient coding, we notice that you really only have to go up to the square root of n,
     *      because if you list out all of the factors of a number, the square root will always be in the middle (if
     *      it happens to not be an integer, we’re still ok, we just might over-approximate, but our code will still work).
     *
     *      Finally, we know 2 is the “oddest” prime – it happens to be the only even prime number.
     *      Because of this, we need only check 2 separately, then traverse odd numbers up to the
     *      square root of n. In the end, our code will resemble this:"
     *
     */
    public static boolean isPrime(final int num) {
        if (num < 0) {
            return false;
        }
        if (num == 0 || num == 1) {
            return false;
        }
        if (num == 2 || num == 3) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        for(int i = 3; i * i <= num; i += 2) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
