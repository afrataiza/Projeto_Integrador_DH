package com.br.digital_hoteis.domain.repository;


import com.br.digital_hoteis.domain.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserDetail, UUID>, JpaSpecificationExecutor<UserDetail> {
    Optional<UserDetail> findByEmail(String email);
}


