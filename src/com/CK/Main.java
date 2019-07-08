package com.CK;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] num1 = new int[]{1};
        int[] num2 = new int[]{};

        Solution2 solution = new Solution2();
        System.out.println(solution.findMedianSortedArrays(num1, num2));
    }
}

class Solution2 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] combined = new int[nums1.length + nums2.length];
        for (int i = 0; i < m + n; i++) {
            if (i < m) combined[i] = nums1[i];
            else combined[i] = nums2[i - m];
        }
        Arrays.sort(combined);
        if (combined.length % 2 == 1) return (double) combined[(combined.length / 2)];
        else return ((double) combined[(combined.length / 2) - 1] + (double) combined[(combined.length / 2)]) / 2;
    }
}

// Binary Search
// cut1, cut2
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int MIN_VALUE = 0x80000000;

        int MAX_VALUE = 0x7fffffff;

        int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 > N2) {// 确保N1是短的部分。
            return findMedianSortedArrays(nums2, nums1);
        }

        if (N1 == 0)
            return ((double) nums2[(N2 - 1) / 2] + (double) nums2[N2 / 2]) / 2;
        int size = N1 + N2;
        int cutL = 0, cutR = N1;
        int cut1 = N1 / 2;
        int cut2 = size / 2 - cut1;

        while (cut1 <= N1) {
            cut1 = (cutR - cutL) / 2 + cutL;
            cut2 = size / 2 - cut1;

            double L1 = (cut1 == 0) ? MIN_VALUE : nums1[cut1 - 1];
            double L2 = (cut2 == 0) ? MIN_VALUE : nums2[cut2 - 1];
            double R1 = (cut1 == N1) ? MAX_VALUE : nums1[cut1];
            double R2 = (cut2 == N2) ? MAX_VALUE : nums2[cut2];
            if (L1 > R2)
                cutR = cut1 - 1;
            else if (L2 > R1)
                cutL = cut1 + 1;
            else {// Otherwise, that's the right cut.
                if (size % 2 == 0) {// 偶数个数的时候
                    L1 = (L1 > L2 ? L1 : L2);
                    R1 = (R1 < R2 ? R1 : R2);
                    return (L1 + R1) / 2;
                } else {
                    R1 = (R1 < R2 ? R1 : R2);
                    return R1;
                }
            }
        }
        return -1;
    }
}