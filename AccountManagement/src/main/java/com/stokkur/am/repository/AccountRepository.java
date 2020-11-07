package com.stokkur.am.repository;

import com.stokkur.am.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * This class provides JPA persistence technology support for CRUD operations
 * 
 * @author Jacobo
 */
@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts") // this annotation allows us to customize the REST endpoint
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    /**
     * Convenience API to allow search by email functionality
     * @param email
     * @return 
     */
    Optional<Account> findByEmail(@Param("email") String email);
}
