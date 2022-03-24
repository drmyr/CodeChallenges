package sorting;

import java.util.Arrays;

public class SearchInSortedRotatedArray {

    public static int search(final int[] nums, final int target) {
        final boolean isRotated = nums[0] > nums[nums.length - 1];

        if(isRotated) {
            // find inflection
            final int inflection = partitionSearch(nums);
            if(nums[inflection] == target) {
                return inflection;
            }
            if(target >= nums[0] && target <= nums[inflection - 1]) {
                return binarySearch(Arrays.copyOfRange(nums, 0, inflection), target);
            } else {
                final int result = binarySearch(Arrays.copyOfRange(nums, inflection, nums.length), target);
                return result == -1 ? result : result + inflection;
            }
        } else {
            return binarySearch(nums, target);
        }
    }

    public static int partitionSearch(final int[] nums) {
        int start = 0, end = nums.length - 1;

        while(start <= end) {
            final int mid = start + ((end - start) / 2);
            if(nums[mid] > nums[mid + 1]) {
                return mid + 1;
            } else if(nums[mid - 1] > nums[mid]) {
                return mid;
            } else if(nums[start] < nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static int binarySearch(final int[] nums, final int target) {
        int start = 0, end = nums.length - 1;
        while(start <= end) {
            final int mid = start + ((end - start) / 2);
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
