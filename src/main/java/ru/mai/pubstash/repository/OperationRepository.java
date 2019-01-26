package ru.mai.pubstash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mai.pubstash.entity.Operation;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
}
