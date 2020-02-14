/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.SimpleCalc;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void addTwoNumbersNegative(){
        double resultAdd = mCalculator.add(-2d, -5d);
        assertThat(resultAdd, is(equalTo(-7d)));
    }

    @Test
    public void addTwoNumbersFloats()
    {
        double resultAdd = mCalculator.add(1.5f, 2.7f);
        assertThat(resultAdd, is(closeTo(4.2, 0.1)));
    }

    @Test
    public void subTwoNumbers()
    {
        double result = mCalculator.sub(7d, 2d);
        assertThat(result, is(equalTo(5d)));
    }

    @Test
    public void subTwoNumbersNegativeResult()
    {
        double result = mCalculator.sub(2d, 5d);
        assertThat(result, is(equalTo(-3d)));
    }

    @Test
    public void multTwoNumbers()
    {
        double result = mCalculator.mul(1.5d, 1.5d);
        assertThat(result, is(equalTo(2.25d)));
    }

    @Test
    public void multTwoNumbersZero()
    {
        double result = mCalculator.mul(5d, 0d);
        assertThat(result, is(equalTo(0d)));
    }

    @Test
    public void divTwoNumbers()
    {
        double result = mCalculator.div(6d, 2d);
        assertThat(result, is(equalTo(3d)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void divByZeroThrows()
    {
        mCalculator.div(7d, 0d);
    }

    @Test
    public void powTwoNumbers()
    {
        double result = mCalculator.pow(2d, 2d);
        assertThat(result, is(equalTo(4d)));
    }

    @Test
    public void powNegPos()
    {
        double result = mCalculator.pow(-2d, 2d);
        assertThat(result, is(equalTo(4d)));
    }

    @Test
    public void powPosNeg()
    {
        double result = mCalculator.pow(2d, -2d);
        assertThat(result, is(equalTo(.25)));
    }

    @Test
    public void powZeroPos()
    {
        double result = mCalculator.pow(0d, 2d);
        assertThat(result, is(equalTo(0d)));
    }

    @Test
    public void powPosZero()
    {
        double result = mCalculator.pow(2d, 0d);
        assertThat(result, is(equalTo(1d)));
    }

    @Test
    public void powZeroNegOne()
    {
        double result = mCalculator.pow(0d, -1d);
        assertThat(result, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void powNegZeroNeg()
    {
        double result = mCalculator.pow(-0d, 0);
        assertThat(result, is(equalTo(1d)));
    }

}