package org.softuni.mobilele.web.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.AddOfferDTO;
import org.softuni.mobilele.models.dto.BrandDTO;
import org.softuni.mobilele.models.entity.Offer;
import org.softuni.mobilele.service.impl.AllBrandsServiceImpl;
import org.softuni.mobilele.service.impl.OfferServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if (!model.containsAttribute("addOfferDTO")) {
            model.addAttribute("addOfferDTO",
                    new AddOfferDTO());
        }
        List<BrandDTO> brands = allBrandsService.getAllBrands();
        model.addAttribute("brands", brands);
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDTO addOfferDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        }
        UUID offerCreated = offerService.addOffer(addOfferDTO);
        return "redirect:/offers/" + offerCreated.toString();
    }

    @GetMapping("/{uuid}")
    public String offerDetails(@PathVariable("uuid") String uuid, Model model) {
        Offer offer = offerService.getOffer(UUID.fromString(uuid));
        model.addAttribute("offer", offer);
        return "details";
    }

    @GetMapping("/{uuid}/update")
    public String updateOffer(@PathVariable("uuid") String uuid, Model model) {
        List<BrandDTO> brands = allBrandsService.getAllBrands();
        model.addAttribute("brands", brands);

        if (model.containsAttribute("updatedOffer")) {
            model.addAttribute("offer", model.getAttribute("updatedOffer"));
        } else {
            Offer offer = offerService.getOffer(UUID.fromString(uuid));
            model.addAttribute("offer", offer);
        }
        return "update";
    }


    @PostMapping("/{uuid}/update")
    public String updateOffer(@PathVariable("uuid") String uuid, @Valid AddOfferDTO updatedOffer, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("updatedOffer", updatedOffer);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.updatedOffer", bindingResult);
            return "redirect:/offers/" + uuid + "/update";
        }

        offerService.updateOffer(updatedOffer);
        return "redirect:/offers/" + uuid + "/details";
    }

    @PostMapping("/{uuid}/delete")
    public String deleteOffer(@PathVariable("uuid") String uuid, Model model) {
        Offer offer = offerService.getOffer(UUID.fromString(uuid));
        model.addAttribute("offer", offer);
        //TODO
        return "redirect:/offers/all";
    }
}
