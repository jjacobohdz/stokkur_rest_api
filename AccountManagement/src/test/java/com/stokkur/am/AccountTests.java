package com.stokkur.am;

import com.stokkur.am.entity.Account;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class is intended to provide tests related to the Account Entity
 * 
 * @author Jacobo
 */
@SpringBootTest
public class AccountTests {
    
    @Test
    public void TestAccountFormedCorrectly() {
        Account anAccount = Account.builder()
                .firstName("John")
                .lastName("Connor")
                .email("john.connor@gmail.com")
                .age(30)
                .balance(1000)
                .build();
        
        assertTrue("john.connor@gmail.com".equals(anAccount.getEmail()));
        assertTrue("John".equals(anAccount.getFirstName()));
        assertTrue("Connor".equals(anAccount.getLastName()));
        assertTrue(anAccount.getAge() == 30);
        assertTrue(anAccount.getBalance() == 1000.0);
    }
}
