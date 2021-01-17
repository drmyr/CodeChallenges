package general;

public class PermutationPrinter {

    /**
     *
     * https://www.cut-the-knot.org/Curriculum/Combinatorics/JohnsonTrotter.shtml
     * https://en.wikipedia.org/wiki/Steinhaus%E2%80%93Johnson%E2%80%93Trotter_algorithm
     * https://www.youtube.com/watch?v=nYFd7VHKyWQ
     * @param input
     */
    public void printAllPermutationsImperative(final String input) {
        throw new UnsupportedOperationException("PermutationPrinter.printAllPermutationsImperative");
    }

    public void printAllPermutationsRecursive(final String input) {
        class Recurse {
            private void printRec(final String drain, final String source) {
                if(source.length() == 0) {
                    System.out.println(drain);
                } else {
                    for(int i = 0; i < source.length(); i++) {
                        final String characterRemoved = source.substring(0, i) + source.substring(i + 1, source.length() - 1);
                        printRec(drain + source.charAt(i), characterRemoved);
                    }
                }
            }
        }
        new Recurse().printRec("", input);
    }
}
