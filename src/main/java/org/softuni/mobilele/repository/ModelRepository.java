package org.softuni.mobilele.repository;

import org.softuni.mobilele.models.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Model getModelById(Long id);
}
