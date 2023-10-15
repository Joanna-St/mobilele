package org.softuni.mobilele.repository;

import org.softuni.mobilele.models.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Offer getOfferByUuid(UUID uuid);
}
