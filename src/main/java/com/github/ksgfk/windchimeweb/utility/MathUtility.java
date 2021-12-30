package com.github.ksgfk.windchimeweb.utility;

import java.util.Arrays;

public class MathUtility {
    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    public static int randomWeight(double uniform, int[] weight) {
        int len = weight.length;
        if (len == 0) {
            return 0;
        }
        if (uniform < 0 || uniform > 1) {
            return -1;
        }
        var cdf = new double[len];
        cdf[0] = weight[0];
        for (int i = 1; i < len; i++) {
            cdf[i] = cdf[i - 1] + weight[i];
        }
        var sum = cdf[len - 1];
        if (sum > 0) {
            var normalize = 1.0 / sum;
            for (int i = 1; i < len; i++) {
                cdf[i] *= normalize;
            }
            cdf[len - 1] = 1;
        }
        int i = Arrays.binarySearch(cdf, uniform);
        if (i < 0) {
            i = Math.abs(i) - 1;
        }
        return clamp(i, 0, len - 1);
    }
}
