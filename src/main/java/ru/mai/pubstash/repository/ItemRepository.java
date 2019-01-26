package ru.mai.pubstash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mai.pubstash.entity.Item;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Long> {
}
