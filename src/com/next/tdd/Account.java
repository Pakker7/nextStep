package com.next.tdd;

// 테스트 주도 개발 : 고품질 쾌속개발을 위한 TDD 실천법과 도구 책의 1장 공개 자료를 보고 TDD 연습
// 질문 -> 응답 -> 정제의 순으로 개발
// 테스트 케이스를 하나씩 추가해 나가면서 구현 클래스를 점진적으로 만드는 방식임
// 일단 먼저 질문에 해당하는 것은, test case를 만들고 진행

public class Account {

    private int balance;

    public Account(int money) {
        this.balance = money;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int money) {
        this.balance += money;
    }

    public void withdraw(int money) {
        this.balance -= money;
    }

}