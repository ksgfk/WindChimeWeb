package com.github.ksgfk.windchimeweb.test;

import com.github.ksgfk.windchimeweb.utility.MathUtility;
import org.junit.Assert;
import org.junit.Test;

public class TestRandomWithWeight {
    @Test
    public void test() {
        int[] v = new int[5];
        v[0] = 1;//0.07 0.07
        v[1] = 1;//0.07 0.14
        v[2] = 3;//0.21 0.35
        v[3] = 4;//0.28 0.63
        v[4] = 5;//0.35 1
        int i1 = MathUtility.randomWeight(1, v);
        Assert.assertEquals(4, i1);
        int i2 = MathUtility.randomWeight(0, v);
        Assert.assertEquals(0, i2);
        int i3 = MathUtility.randomWeight(-1, v);
        Assert.assertEquals(-1, i3);
        int i4 = MathUtility.randomWeight(0.03, v);
        Assert.assertEquals(0, i4);
        int i5 = MathUtility.randomWeight(0.46, v);
        Assert.assertEquals(3, i5);
        int i6 = MathUtility.randomWeight(0.88, v);
        Assert.assertEquals(4, i6);
        //|---0.07---|---0.07---|---0.21---|---0.28---|---0.35---|
        //0         0.07       0.14       0.35       0.63        1
    }
}
