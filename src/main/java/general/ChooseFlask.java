package general;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ChooseFlask {

    /**
     * https://aonecode.com/amazon-online-assessment-choose-a-flask
     *
     *      "A robotic chemical delivery system for an Amazon laboratory has been configured to work using only
     *       one type of glass flask per day. For each chemical ordered, it will be filled to a mark that is at
     *       least equal to the volume ordered. There are multiple flasks available, each with markings at various levels.
     *
     *       Given a list of order requirements and a list of flasks with their measurements, write an algorithm
     *       to determine the single type of flask that will result in minimal waste. Waste is the sum of
     *       marking - requirement for each order. Output the zero-based index of the flask type chosen. If there are
     *       multiple answers, output the minimum index. If no flask will satisfy the constraints, output -1."
     *
     * @param numOrders
     * @param requirements
     * @param flaskTypes
     * @param totalMarkings
     * @param markings
     * @return
     */
    public static int flaskFinder(final int numOrders, final int[] requirements, final int flaskTypes, final int totalMarkings, final int[][] markings) {
        Arrays.sort(requirements);

        final int largest = requirements[requirements.length - 1];

        class Flask {
            final int id;
            final TreeSet<Integer> graduations;
            Flask(final int id) {
                this.id = id;
                this.graduations = new TreeSet<>();
            }

            @SuppressWarnings("ConstantConditions")
            int calculateWaste() {
                // If the flask is too small for the requirement, return early.
                if(largest > this.graduations.last()) {
                    return Integer.MAX_VALUE;
                }

                return Arrays.stream(requirements)
                    .map(req -> (this.graduations.ceiling(req) - req))
                    .reduce(0, Integer::sum);
            }
        }

        final Map<Integer, Flask> flaskMap = new HashMap<>();
        for(final int[] marking : markings) {
            flaskMap.computeIfAbsent(marking[0], Flask::new).graduations.add(marking[1]);
        }

        final int[] wasteAndId = new int[] {Integer.MAX_VALUE, -1};
        for(final Flask flask : flaskMap.values()) {
            final int waste = flask.calculateWaste();
            if(waste < wasteAndId[0]) {
                wasteAndId[0] = waste;
                wasteAndId[1] = flask.id;
            }
        }

        return wasteAndId[1];
    }
}
