package general;

public class StringCompression {

    /**
     * https://aonecode.com/amazon-interview-questions/String-Compression-and-Decompression
     *
     * Compress a string in place, by replacing repeating characters with a number representing their count.
     *
     * Ex1
     * a,a,b,c,c,c,b,b   becomes   a,2,b,c,3,b,2
     *
     * a,a,a,a,a,a,a,a,a,a,a,a     becomes    a,1,2
     * here 12 is two characters, so it needs to be split over two cell locations, thus a,1,2
     *
     * Note: Not sure how they expect this to be done 'in place' if the array after mutation is shorter than the
     * original, as you cannot just delete a cell from an array, you have to make a whole new array.
     */
    public static Character[] compress(final Character[] input) {
        if(input.length < 2) return input;

        final char delimiter = ' ';
        int count = 1;
        int position = 0;
        while(position < input.length) {
            int runForward = position + 1;
            while(runForward < input.length && input[position] == input[runForward]) {
                count++;
                final char[] countAsCharArray = String.valueOf(count).toCharArray();
                int charSpot = position + 1;
                for(int j = 0; j < countAsCharArray.length; j++) {
                    input[charSpot++] = countAsCharArray[j];
                }
                runForward++;
            }
            int runBackward = runForward - 1;
            while(count > 1 && !Character.isDigit(input[runBackward])) {
                input[runBackward--] = delimiter;
            }
            count = 1;
            position = runForward;
        }

        for(int i = 0; i < input.length - 1; i++) {
            if(input[i] == delimiter) {
                input[i] = input[i + 1];
                input[i + 1] = delimiter;
            }
        }

        return input;
    }
}
