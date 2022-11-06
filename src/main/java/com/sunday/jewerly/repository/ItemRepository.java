package com.sunday.jewerly.repository;

import com.sunday.jewerly.model.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item,UUID> {

    @Transactional
    @Query("SELECT i FROM Item i " +
           " ORDER BY i.code" +
           "")
    List<Item> findAllWithPageable(Pageable pageable);

    @NonNull
    Optional<Item> findById(UUID id);

    @Query(value = "SELECT code FROM items i ORDER BY code DESC LIMIT 1", nativeQuery = true)
    Optional<String> getLastCode();
}