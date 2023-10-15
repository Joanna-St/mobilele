package org.softuni.mobilele.service;

import org.softuni.mobilele.models.dto.AddOfferDTO;
import org.softuni.mobilele.models.entity.Offer;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    List<Offer> getAllOffers();

    UUID addOffer(AddOfferDTO addOfferDTO);

    Offer getOffer(UUID uuid);

    Offer updateOffer(AddOfferDTO updatedOffer);
}
