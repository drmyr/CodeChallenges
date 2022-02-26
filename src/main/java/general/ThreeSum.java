package general;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class ThreeSum {

    public static List<List<Integer>> threeSum(final int[] nums) {
        Arrays.sort(nums);
        class Triplet {
            final int i;
            final int j;
            final int k;
            Triplet(final int i, final int j, final int k) {
                final int[] arr = {i, j, k};
                Arrays.sort(arr);
                this.i = arr[0];
                this.j = arr[1];
                this.k = arr[2];
            }

            @Override
            public boolean equals(Object other) {
                if(other instanceof final Triplet triplet) {
                    return this.i == triplet.i &&
                            this.j == triplet.j &&
                            this.k == triplet.k;
                }
                return false;
            }

            @Override
            public int hashCode() {
                return Objects.hash(i, j, k);
            }

            List<Integer> toList() {
                return List.of(i, j, k);
            }
        }

        final Set<Triplet> triplets = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0) {
                    triplets.add(new Triplet(nums[i], nums[left], nums[right]));
                    right--;
                } else if(sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return triplets.stream().map(Triplet::toList).collect(toList());
    }
}
