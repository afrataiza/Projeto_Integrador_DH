package com.br.digital_hoteis.domain.repository;


import com.br.digital_hoteis.domain.entity.UserDetail;
import com.br.digital_hoteis.domain.entity.UserPermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserDetail, UUID>, JpaSpecificationExecutor<UserDetail> {
    Optional<UserDetail> findByEmail(String emailId);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserDetail u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);



    UserDetail findByEmailIgnoreCase(String email);


}





