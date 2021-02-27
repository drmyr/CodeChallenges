package general;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ChooseFlask {

    public static int flaskFinder(final int numOrders, final int[] requirements, final int flaskTypes, final int totalMarkings, final int[][] markings) {
        class Flask {
            final int id;
            final TreeSet<Integer> graduations;
            int waste = 0;
            Flask(final int id) {
                this.id = id;
                this.graduations = new TreeSet<>();
            }

            int calculateWaste() {
                int waste = 0;
                for(final int requirement : requirements) {
                    final Integer nextLarger = this.graduations.ceiling(requirement);
                    if(nextLarger != null) {
                        waste += (nextLarger - requirement);
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
                return waste;
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
