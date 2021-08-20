package com.codecool.sweeteclipse.repository;

import com.codecool.sweeteclipse.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Long> {

//    @Modifying
//    @Query(
//            value =
//                    "INSERT INTO image (uri, project_id) VALUES (:uri, :projectId)",
//            nativeQuery = true)
//    void insertImage(@Param("uri") String uri, @Param("projectId") Long projectId);
}
