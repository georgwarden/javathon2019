package ru.mai.pubstash.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mai.pubstash.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
