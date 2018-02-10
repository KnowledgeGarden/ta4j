/*
  The MIT License (MIT)

  Copyright (c) 2014-2017 Marc de Verdelhan, Ta4j Organization & respective authors (see AUTHORS)

  Permission is hereby granted, free of charge, to any person obtaining a copy of
  this software and associated documentation files (the "Software"), to deal in
  the Software without restriction, including without limitation the rights to
  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
  the Software, and to permit persons to whom the Software is furnished to do so,
  subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ta4j.core.indicators.helpers;

import org.junit.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.Indicator;
import org.ta4j.core.Num.Num;
import org.ta4j.core.TestUtils;
import org.ta4j.core.indicators.AbstractIndicatorTest;
import org.ta4j.core.mocks.MockBar;
import org.ta4j.core.mocks.MockTimeSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MinusDMIndicatorTest extends AbstractIndicatorTest<Indicator<Num>,Num> {

    public MinusDMIndicatorTest(Function<Number, Num> numFunction) {
        super(numFunction);
    }

    @Test
    public void zeroDirectionalMovement() {
        MockBar yesterdayBar = new MockBar(0, 0, 10, 2,numFunction);
        MockBar todayBar = new MockBar(0, 0, 6, 6,numFunction);
        List<Bar> bars = new ArrayList<Bar>();
        bars.add(yesterdayBar);
        bars.add(todayBar);
        MockTimeSeries series = new MockTimeSeries(bars);
        MinusDMIndicator down = new MinusDMIndicator(series);
        TestUtils.assertNumEquals(down.getValue(1), 0);
    }

    @Test
    public void zeroDirectionalMovement2() {
        MockBar yesterdayBar = new MockBar(0, 0, 6, 12,numFunction);
        MockBar todayBar = new MockBar(0, 0, 12, 6,numFunction);
        List<Bar> bars = new ArrayList<Bar>();
        bars.add(yesterdayBar);
        bars.add(todayBar);
        MockTimeSeries series = new MockTimeSeries(bars);
        MinusDMIndicator down = new MinusDMIndicator(series);
        TestUtils.assertNumEquals(down.getValue(1), 0);
    }

    @Test
    public void zeroDirectionalMovement3() {
        MockBar yesterdayBar = new MockBar(0, 0, 6, 6,numFunction);
        MockBar todayBar = new MockBar(0, 0, 12, 4,numFunction);
        List<Bar> bars = new ArrayList<Bar>();
        bars.add(yesterdayBar);
        bars.add(todayBar);
        MockTimeSeries series = new MockTimeSeries(bars);
        MinusDMIndicator down = new MinusDMIndicator(series);
        TestUtils.assertNumEquals(down.getValue(1), 0);
    }

    @Test
    public void positiveDirectionalMovement() {
        MockBar yesterdayBar = new MockBar(0, 0, 6, 20,numFunction);
        MockBar todayBar = new MockBar(0, 0, 12, 4,numFunction);
        List<Bar> bars = new ArrayList<Bar>();
        bars.add(yesterdayBar);
        bars.add(todayBar);
        MockTimeSeries series = new MockTimeSeries(bars);
        MinusDMIndicator down = new MinusDMIndicator(series);
        TestUtils.assertNumEquals(down.getValue(1), 16);
    }
}