package com.next.tdd;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private Account account = null;

    @Before
    public void setting() {
        account = new Account(10000);
    }

    @Test
    public void testGetBalance() throws Exception {
        if(account.getBalance() != 10000) {
            assertEquals(10000, account.getBalance());
        }

        account = new Account(0);
        if(account.getBalance() != 0) {
            assertEquals(0, account.getBalance());
        }
    }

    @Test
    public void 입금() {
        account.deposit(1000);
        assertEquals(11000, account.getBalance());

    }

    @Test
    public void 출금() {
        account.withdraw(1000);
        assertEquals(9000, account.getBalance());
    }
}
