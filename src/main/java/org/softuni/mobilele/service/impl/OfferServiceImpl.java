package org.softuni.mobilele.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.AddOfferDTO;
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
    public UUID addOffer(AddOfferDTO addOfferDTO) {

        return offerRepository.save(map(addOfferDTO)).getUuid();
    }

    @Override
    public Offer getOffer(UUID uuid) {
        return offerRepository.getOfferByUuid(uuid);
    }

    @Override
    public Offer updateOffer(AddOfferDTO updatedOffer) {
//        TODO
        return null;
    }

    private Offer map(AddOfferDTO offerDTO) {
        Offer offer = new Offer();

        offer.setDescription(offerDTO.getDescription());
        offer.setEngine(offerDTO.getEngine());
        offer.setImageUrl(offerDTO.getImageUrl());
        offer.setMileage(offerDTO.getMileage());
        offer.setPrice(offerDTO.getPrice());
        offer.setTransmission(offerDTO.getTransmission());
        offer.setYear(offerDTO.getYear());
        offer.setUuid(UUID.randomUUID());
        offer.setCreated(LocalDateTime.now());
        offer.setModified(LocalDateTime.now());

        offer.setModel(modelRepository.getModelById(offerDTO.getModel()));
        offer.setSeller(userRepository.getUserById(currentUser.getId()));

        return offer;
    }
}
