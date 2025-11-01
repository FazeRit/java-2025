package com.example.lab2.lostItem.repository;

import com.example.lab2.lostItem.entity.LostItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostItemRepository extends JpaRepository<LostItemEntity, Integer>, JpaSpecificationExecutor<LostItemEntity> {

    @Query("SELECT li FROM LostItemEntity li WHERE LOWER(li.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    List<LostItemEntity> findByNameContaining(@Param("namePart") String namePart);


    List<LostItemEntity> findAllByLocation(@Param("location") String location);


    LostItemEntity findByPhoneNumber(String phoneNumber);

    List<LostItemEntity> findByTags_NameIn(List<String> tagNames);
}