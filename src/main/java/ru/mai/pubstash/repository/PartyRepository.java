package ru.mai.pubstash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mai.pubstash.entity.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
}
