package com.web.Bang.repository.queryStorage;

import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QlrmRepository<T> {

    private final EntityManager entityManager;

    public List<T> returnDataList(String queryText, Class<T> target) {
        Query query = entityManager.createNativeQuery(queryText);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        return (List<T>) jpaResultMapper.list(query, target);
    }

}
