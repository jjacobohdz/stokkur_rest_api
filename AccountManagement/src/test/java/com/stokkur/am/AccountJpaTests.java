package com.stokkur.am;

import com.stokkur.am.entity.Account;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This class is intended to provide tests related to JPA
 * @author Jacobo
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountJpaTests {
    
    @Autowired
    private TestEntityManager theTestEntityManager;

    @Test
    public void TestAccountIsCorrectlyPersisted() {
        Account testAccount = Account.builder()
                .firstName("John")
                .lastName("Connor")
                .email("john.connor@gmail.com")
                .age(30)
                .balance(1000)
                .build();
        
        Account anAccount = theTestEntityManager.persistFlushFind(testAccount);

        assertTrue(anAccount != null);
        assertTrue(anAccount.getId() != null);
        assertTrue(anAccount.getId() > 0);
        assertTrue("john.connor@gmail.com".equals(anAccount.getEmail()));
        assertTrue("John".equals(anAccount.getFirstName()));
        assertTrue("Connor".equals(anAccount.getLastName()));
        assertTrue(anAccount.getAge() == 30);
        assertTrue(anAccount.getBalance() == 1000.0);
    }
}
