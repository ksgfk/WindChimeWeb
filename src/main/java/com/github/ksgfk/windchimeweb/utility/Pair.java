package com.github.ksgfk.windchimeweb.utility;

public class Pair<TValue1,TValue2> {
    private final TValue1 val1;
    private final TValue2 val2;

    public TValue1 getVal1() {
        return val1;
    }

    public TValue2 getVal2() {
        return val2;
    }

    public Pair(TValue1 val1, TValue2 val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
}
