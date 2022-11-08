package com.sunday.jewelry.repository;

import com.sunday.jewelry.model.Item;
import com.sunday.jewelry.model.enums.ItemType;
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
@Transactional
public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query("SELECT i FROM Item i " +
           " ORDER BY i.code ")
    List<Item> findAllWithPageable(Pageable pageable);

    @Query("SELECT i FROM Item i " +
           "WHERE LOWER(i.name) LIKE %:name% ")
    List<Item> findItemsLikeName(String name, Pageable pageable);

    @Query("SELECT DISTINCT i FROM Item i " +
           "JOIN Size s ON i.id = s.item.id " +
           "WHERE (:itemType IS NULL OR i.itemType = :itemType) " +
           "AND i.isInStock = TRUE " +
           "AND s.size IN (:sizes) " +
           "ORDER BY i.code ")
    List<Item> findAllFilterWithSizes(
            ItemType itemType,
            List<Float> sizes,
            Pageable pageable
    );

    @Query("SELECT DISTINCT i FROM Item i " +
           "WHERE (:itemType IS NULL OR i.itemType = :itemType) " +
           "AND i.isInStock = COALESCE(:isInStock, TRUE) " +
           "ORDER BY i.code ")
    List<Item> findAllByFilter(
            ItemType itemType,
            Boolean isInStock,
            Pageable pageable
    );


    @NonNull
    Optional<Item> findById(UUID id);

    @Query(value = "SELECT code FROM items i ORDER BY code DESC LIMIT 1", nativeQuery = true)
    Optional<String> getLastCode();
}