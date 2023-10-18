package org.softuni.mobilele.repository;

import org.softuni.mobilele.models.entity.Brand;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
//    @Query(value = "SELECT b " +
//            "FROM Brand AS d " +
//            "JOIN FETCH b.models")


    @EntityGraph(value = "brandsAndModels", attributePaths = "models")
    @Query(value = "SELECT b " +
            "FROM Brand AS b")
    List<Brand> findAll();
}
