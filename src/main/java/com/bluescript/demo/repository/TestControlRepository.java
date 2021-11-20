package com.bluescript.demo.repository;

import com.bluescript.demo.model.TestReadRecord;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface TestControlRepository extends CrudRepository<TestReadRecord, Integer> {
}