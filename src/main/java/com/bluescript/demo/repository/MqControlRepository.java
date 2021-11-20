package com.bluescript.demo.repository;

import com.bluescript.demo.model.MqReadRecord;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface MqControlRepository extends CrudRepository<MqReadRecord, Integer> {
}