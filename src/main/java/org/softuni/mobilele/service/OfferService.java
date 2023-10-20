package org.softuni.mobilele.service;

import org.softuni.mobilele.models.dto.OfferDTO;
import org.softuni.mobilele.models.entity.Offer;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    List<Offer> getAllOffers();

    UUID addOffer(OfferDTO addOfferDTO);

    Offer getOffer(UUID uuid);

    void updateOffer(OfferDTO updatedOffer, UUID uuid);

    void deleteOffer(String uuid);
}
