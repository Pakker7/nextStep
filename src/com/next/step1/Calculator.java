package com.next.step1;

import lombok.Getter;

import java.util.Arrays;
import java.util.Scanner;
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

    @Getter
    private int result;

    public void numbersSum() {
        plus(seperateNumber(input()));
    }

    protected String input() {
        System.out.println("전달하는 문자의 합을 구하는 계산기 입니다. 입력해주세요.");

        Scanner sc = new Scanner(System.in);
        return validation(sc.next());
    }

    protected String validation(String str) {
        if (str.contains("-")) {
            throw new RuntimeException("음수가 포함되어 있습니다.");
        }

        if (str.equals("null") || str.isEmpty()) {
            return "0";
        }

        return str;
    }

    protected int[] seperateNumber(String str) {
        String regex = "//(.*?)\\n"; // //(.)\n(.*) 이렇게도 가능
        Matcher matcher = Pattern.compile(regex).matcher(str);

        String seperate = ",|;|\n";
        if (matcher.find()) {
            str = str.replaceAll(regex, "");
            seperate = matcher.group(1);
        }

        return convertStringAryToIntAry(str.split(seperate));
    }

    protected int[] convertStringAryToIntAry (String[] ary) {
        return Arrays.asList(ary).stream().mapToInt(Integer::parseInt).toArray();
    }

    protected void plus(int[] intArray) {
        this.result = Arrays.stream(intArray).sum();
    }

}
