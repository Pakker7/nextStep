package com.next.step1;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;
import static org.junit.Assert.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    /* 문자열 계산기 만들기
    문자열 계산기의 요구사항은 전달하는 문자를 구분자로 분리한 후 각 숫자의 합을 구해 반환해야 한다.
    1. 쉼표 또는 콜론을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
    2. 앞의 기본 구분자 외에 커스텀 구분자를 지정할 수 있다. ex) //;\n1;2;3
    3. 문자열 계산기에 음수를 전달하는 경우 RuntimeException으로 예외처리 해야한다.*/

    /* 순서.. 테스트..?
    1. input 받기
    2. validation 체크 (음수) -> 음수, 양수 넣었을 때 잘 나오는지 테스트
    3. 커스텀 문자로 숫자 분리 -> 임의의 문자열을 넣고 array로 잘 나오는지 assertArray
    4. for문 숫자의 합을 구하기 -> 문자를 array로 넣고 합을 잘 출력하는지 테스트
    5. 결과 출력
     */

    public Calculator() {
        Scanner sc = new Scanner(System.in);
        System.out.println("전달하는 문자의 합을 구하는 계산기 입니다. 입력해주세요.");
        String str = sc.next();

        validation(str);
        int[] intArray = seperateNumber(str);
        int result = plus(intArray);

        System.out.println("result -> " + result);
    }

    private void validation(String str) {
        if(str.contains("-")) {
            new RuntimeException();
        }
    }

    private int[] seperateNumber(String str) {
        String regex = "[//](.*?)[\\n]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        String seperate = ",|;";
        if (matcher.find()) {
            str = str.replaceAll(regex, "");
            seperate = matcher.group();
        }

        return Arrays.asList(str.split(seperate)).stream().mapToInt(Integer::parseInt).toArray();
    }

    private int plus(int[] intArray) {
        int result = 0;
        for(int i : intArray) {
            result += i;
        }
        return result;
    }

    @Test(expected=RuntimeException.class)
    public void 유효성검사() {
        String str = "//;\\n-1;-2;3";
        validation(str);
    }

    @Test
    public void 커스텀문자로숫자분리() {
        String str = "//qq\\n1qq2qq3";
        assertArrayEquals(seperateNumber(str), new int[]{1, 2, 3});
    }

    @Test
    public void 더하기() {
        int[] intArray = {1,2,3};
        assertEquals(plus(intArray), 6);
    }

}
