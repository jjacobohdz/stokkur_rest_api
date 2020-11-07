package com.stokkur.am;

import com.stokkur.am.entity.Account;
import com.stokkur.am.repository.AccountRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class just loads a couple of records in the in-memory database during application launch.
 * This class will get loaded automatically by Spring.
 * 
 * @author Jacobo
 */
@Configuration
public class AccountsPrimer {

  private static final Logger theLog = Logger.getLogger(AccountsPrimer.class.getName());

  /**
   * This method adds a couple of entries to accounts table in the in-memory database.
   * It also creates a couple of users
   * @param anAccountRepository
   * @return 
   */
  @Bean
  CommandLineRunner initAccounts(AccountRepository anAccountRepository) {
      
    anAccountRepository.save(new Account("John", "Connor", "john.connor@gmail.com", 21, 1000));
    anAccountRepository.save(new Account("Jacob", "Hernandez", "jjacobohdz@hotmail.com", 821, 5000));

    return args -> {
      theLog.log(Level.CONFIG, "Accounts Primed");
    };
  }
  
}
