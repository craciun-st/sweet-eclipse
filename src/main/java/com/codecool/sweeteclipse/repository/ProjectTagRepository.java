package com.codecool.sweeteclipse.repository;

import com.codecool.sweeteclipse.model.ProjectTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTagRepository extends JpaRepository<ProjectTag, Long> {

//    @Modifying
//    @Query(
//            value =
//                    "INSERT INTO tag (name) VALUES (:name)",
//            nativeQuery = true)
//    void insertTag(@Param("name") String name);

}
