package com.restfulservices.rest.service;

import com.restfulservices.rest.domain.JpaUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class JpaUserService {

    @PersistenceContext//laczy kontekst z kontenerem
    private EntityManager entityManager;

    public long insert(JpaUser jpaUser) {
        entityManager.persist(jpaUser);
        return jpaUser.getId();
    }
}
