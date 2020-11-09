package com.stokkur.am;

import com.stokkur.am.config.SecurityConfig;
import com.stokkur.am.entity.User;
import com.stokkur.am.repository.UserRepository;
import java.util.Arrays;
import org.slf4j.LoggerFactory;
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

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

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
        logger.debug("Users primed");
      };
    }
  
}
