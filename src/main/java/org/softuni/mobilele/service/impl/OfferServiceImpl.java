package org.softuni.mobilele.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.OfferDTO;
import org.softuni.mobilele.models.entity.Offer;
import org.softuni.mobilele.repository.ModelRepository;
import org.softuni.mobilele.repository.OfferRepository;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.OfferService;
import org.softuni.mobilele.util.CurrentUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOffer(UUID uuid) {
        return offerRepository.getOfferByUuid(uuid);
    }

    @Override
    public UUID addOffer(OfferDTO addOfferDTO) {

        return offerRepository.save(newOffer(addOfferDTO)).getUuid();
    }

    @Override
    public void updateOffer(OfferDTO updatedOffer, UUID uuid) {
        offerRepository.save(updatedOffer(updatedOffer, uuid));
    }

    @Override
    public void deleteOffer(String uuid) {
        offerRepository.delete(offerRepository.getOfferByUuid(UUID.fromString(uuid)));
    }

    private Offer newOffer(OfferDTO offerDTO) {
        Offer offer = new Offer();

        map(offerDTO, offer);
        offer.setUuid(UUID.randomUUID());
        offer.setCreated(LocalDateTime.now());
        offer.setModified(LocalDateTime.now());

        offer.setSeller(userRepository.getUserById(currentUser.getId()));

        return offer;
    }

    private Offer updatedOffer(OfferDTO updateOfferDTO, UUID uuid) {
        Offer offer = offerRepository.getOfferByUuid(uuid);

        map(updateOfferDTO, offer);

        offer.setModified(LocalDateTime.now());

        return offer;
    }

    private void map(OfferDTO offerDTO, Offer offer) {
        offer.setDescription(offerDTO.getDescription());
        offer.setEngine(offerDTO.getEngine());
        offer.setImageUrl(offerDTO.getImageUrl());
        offer.setMileage(offerDTO.getMileage());
        offer.setPrice(offerDTO.getPrice());
        offer.setTransmission(offerDTO.getTransmission());
        offer.setYear(offerDTO.getYear());
        offer.setModel(modelRepository.getModelById(offerDTO.getModel().getId()));
    }
}
