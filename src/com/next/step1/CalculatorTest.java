package com.next.step1;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private Calculator col;

    // 질문 -> 응답 -> 정제의 순으로 개발

    @Before
    public void setup() {
        col = new Calculator();
    }

    @Test
    public void 빈문자열_null() {
        String str = "null";
        assertEquals(col.validation(str), "0");
    }

    @Test
    public void 빈문자열_empty() {
        String str = "";
        assertEquals(col.validation(str), "0");
    }

    @Test
    public void 숫자하나를문자열로입력() {
        int[] intArray = {1};
        col.plus(intArray);
        assertEquals(col.getResult(), 1);
    }

    @Test(expected=RuntimeException.class)
    public void 유효성검사() {
        String str = "//;\\n-1;-2;3";
        col.validation(str);
    }

    @Test
    public void 커스텀문자로숫자분리() {
        String str = "//qq\n1qq2qq3";
        assertArrayEquals(col.seperateNumber(str), new int[]{1, 2, 3});
    }

    @Test
    public void 구분자_newLine() {
        String str = "1,2\n3";
        assertArrayEquals(col.seperateNumber(str), new int[]{1, 2, 3});
    }

    @Test
    public void 기본_더하기() {
        int[] intArray = {1,2,3};
        col.plus(intArray);
        assertEquals(col.getResult(), 6);
    }

    @Test
    public void 숫자두개_더하기() {
        int[] intArray = {1,2};
        col.plus(intArray);
        assertEquals(col.getResult(), 3);
    }

}