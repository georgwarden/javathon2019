package ru.mai.pubstash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mai.pubstash.entity.Party;

@Repository
public interface PartyRepository extends CrudRepository<Party, Long> {
}
