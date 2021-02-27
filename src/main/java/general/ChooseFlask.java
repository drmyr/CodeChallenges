package general;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ChooseFlask {

    public static int flaskFinder(final int numOrders, final int[] requirements, final int flaskTypes, final int totalMarkings, final int[][] markings) {
        Arrays.sort(requirements);

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
                if(requirements[requirements.length - 1] > this.graduations.last()) {
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
