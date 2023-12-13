package com.daanielowsky.crm.Repositories;

import com.daanielowsky.crm.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository <Item, Long> {
    List<Item> getAllBy();
}
