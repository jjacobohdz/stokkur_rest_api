package com.stokkur.am;

import com.stokkur.am.entity.User;
import com.stokkur.am.repository.UserRepository;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class just loads a couple of records in the in-memory database during application launch.
 * 
 * @author Jacobo
 */
@Configuration
public class UsersPrimer {

  private static final Logger theLog = Logger.getLogger(UsersPrimer.class.getName());

  /**
   * This method adds a couple of users
   * 
   * @param aUsersRepository
   * @return 
   */
  @Bean
  CommandLineRunner initUsers(UserRepository aUsersRepository) {
      
    PasswordEncoder thePasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
      
    aUsersRepository.save(User.builder()
        .username("duke")
        .password(thePasswordEncoder.encode("java"))
        .roles(Arrays.asList("ROLE_USER"))
        .build()
    );

    aUsersRepository.save(User.builder()
        .username("admin")
        .password(thePasswordEncoder.encode("admin"))
        .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
        .build()
    );

    return args -> {
      theLog.log(Level.CONFIG, "Users Primed");
    };
  }
  
}
