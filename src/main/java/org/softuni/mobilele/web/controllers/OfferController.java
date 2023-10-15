package org.softuni.mobilele.web.controllers;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.AddOfferDTO;
import org.softuni.mobilele.models.dto.BrandDTO;
import org.softuni.mobilele.models.entity.Offer;
import org.softuni.mobilele.service.impl.AllBrandsServiceImpl;
import org.softuni.mobilele.service.impl.OfferServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/offers")
@Controller
public class OfferController {
    private OfferServiceImpl offerService;
    private AllBrandsServiceImpl allBrandsService;

    @GetMapping("/all")
    public String allOffers(Model model) {
        List<Offer> offers = offerService.getAllOffers();
        model.addAttribute("offers", offers);
        return "offers";
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        List<BrandDTO> brands = allBrandsService.getAllBrands();
        model.addAttribute("brands", brands);
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(AddOfferDTO addOfferDTO) {
        UUID offerCreated = offerService.addOffer(addOfferDTO);
        return "redirect:/offers/" + offerCreated.toString() + "/details";
    }

    @GetMapping("/{uuid}/details")
    public String offerDetails(@PathVariable("uuid") String uuid, Model model) {
        Offer offer = offerService.getOffer(UUID.fromString(uuid));
        model.addAttribute("offer", offer);
        return "details";
    }

    @GetMapping("/{uuid}/update")
    public String updateOffer(@PathVariable("uuid") String uuid, Model model) {
        List<BrandDTO> brands = allBrandsService.getAllBrands();
        model.addAttribute("brands", brands);
        Offer offer = offerService.getOffer(UUID.fromString(uuid));
        model.addAttribute("offer", offer);
        return "update";
    }


    @PostMapping ("/{uuid}/update")
    public String updateOffer(@PathVariable("uuid") String uuid, AddOfferDTO updatedOffer) {
        offerService.updateOffer(updatedOffer);
        return "redirect:/offers/" + updatedOffer.toString() + "/details";
    }

    @PostMapping("/{uuid}/delete")
    public String deleteOffer(@PathVariable("uuid") String uuid, Model model) {
        Offer offer = offerService.getOffer(UUID.fromString(uuid));
        model.addAttribute("offer", offer);
        //TODO
        return "redirect:/offers/all";
    }
}
