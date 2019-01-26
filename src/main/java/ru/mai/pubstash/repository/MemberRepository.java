package ru.mai.pubstash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mai.pubstash.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
