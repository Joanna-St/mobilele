package org.softuni.mobilele.web.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.BrandDTO;
import org.softuni.mobilele.models.dto.OfferDTO;
import org.softuni.mobilele.models.entity.Offer;
import org.softuni.mobilele.models.enums.EngineEnum;
import org.softuni.mobilele.models.enums.TransmissionEnum;
import org.softuni.mobilele.service.impl.AllBrandsServiceImpl;
import org.softuni.mobilele.service.impl.OfferServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/offers")
@Controller
public class OfferController {
    private OfferServiceImpl offerService;
    private AllBrandsServiceImpl allBrandsService;

//    Model Attributes =============================================================================
    @ModelAttribute("engines")
    public EngineEnum[] engines() {
        return EngineEnum.values();
    }

    @ModelAttribute("transmissions")
    public TransmissionEnum[] transmissions(){
        return TransmissionEnum.values();
    }

    @ModelAttribute("brands")
    public List<BrandDTO> brands(){
        return allBrandsService.getAllBrands();
    }

//    Show All Offers ================================================================================
    @GetMapping("/all")
    public String allOffers(Model model) {
        List<Offer> offers = offerService.getAllOffers();
        model.addAttribute("offers", offers);
        return "offers";
    }

//    Add New Offer ===================================================================================
    @GetMapping("/add")
    public String addOffer(Model model) {
        if (!model.containsAttribute("addOfferDTO")) {
            model.addAttribute("addOfferDTO",
                    new OfferDTO());
        }
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid OfferDTO addOfferDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        }
        UUID offerCreated = offerService.addOffer(addOfferDTO);
        return "redirect:/offers/" + offerCreated.toString() + "/details";
    }

//    Offer Details
    @GetMapping("/{uuid}/details")
    public String offerDetails(@PathVariable("uuid") String uuid, Model model) {
        Offer offer = offerService.getOffer(UUID.fromString(uuid));
        model.addAttribute("offer", offer);
        return "details";
    }

//    Update Existing offer ============================================================================
    @GetMapping("/{uuid}/update")
    public String updateOffer(@PathVariable("uuid") String uuid, Model model) {
        if (model.containsAttribute("updatedOffer")) {
            model.addAttribute("offer", model.getAttribute("updatedOffer"));
        } else {
            Offer offer = offerService.getOffer(UUID.fromString(uuid));
            model.addAttribute("offer", offer);
        }
        return "update";
    }

    @PostMapping("/{uuid}/update")
    public String updateOffer(@PathVariable("uuid") UUID uuid, @Valid OfferDTO updatedOffer, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("updatedOffer", updatedOffer);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.offer", bindingResult);
            return "redirect:/offers/" + uuid + "/update";
        }

        offerService.updateOffer(updatedOffer, uuid);
        return "redirect:/offers/" + uuid + "/details";
    }

//    Delete offer - not working :(
    @DeleteMapping("/{uuid}/delete")
    public String deleteOffer(@PathVariable("uuid") String uuid) {

        offerService.deleteOffer(uuid);

        return "redirect:/offers/all";
    }
}
