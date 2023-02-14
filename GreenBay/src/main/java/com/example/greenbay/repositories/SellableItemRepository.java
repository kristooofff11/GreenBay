package com.example.greenbay.repositories;

import com.example.greenbay.models.entities.SellableItem;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellableItemRepository extends JpaRepository<SellableItem,Long> {
  SellableItem findSellableItemByItemId(Long id);
  SellableItem findSellableItemByName(String name);
  List<SellableItem> findSellableItemBySold(boolean sold, PageRequest of);
}
