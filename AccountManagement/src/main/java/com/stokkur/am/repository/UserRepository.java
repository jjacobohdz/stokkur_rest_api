package com.stokkur.am.repository;

import com.stokkur.am.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Jacobo
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(@Param("username") String username);

}
