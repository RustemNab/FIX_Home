package ru.ivmiit.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.service.forms.BandForm;
import ru.ivmiit.service.models.Band;
import ru.ivmiit.service.repositories.BandsRepository;

import java.util.ArrayList;
import java.util.List;


@Controller
public class BandsController {
    

    @Autowired
    BandsRepository bandsRepository;

    @GetMapping("/bands")
    public String getBandsPage(ModelMap model) {
        model.addAttribute("bandsFromServer", bandsRepository.findAll());
        return "bands";
    }

//    @RequestMapping(path = "/bands", method = RequestMethod.GET)
//        public String getBandsPage(ModelMap model, @RequestParam(value = "delete_id", required = false) Long id) {
//            if (id != null) {
//                bandsRepository.delete(id);
//            }
//
//            model.addAttribute("listBands",   bandsRepository.findAll());
//            return "bands";
//        }
//
//        @GetMapping(path = "/bands/new")
//        public String getNewProdutPage() {
//            return "productNew";
//        }
//
//        @RequestMapping(path = "/bands", method = RequestMethod.POST)
//        public void addNewBand(ModelMap model, BandForm form) {
//            if (form.getId() != null) {
//                Band band = bandsRepository.findOne(form.getId());
//                band.setName(form.getName());
//                band.setGenre(form.getGenre());
//
//                bandsRepository.save(band);
//            } else {
//                Band newBand = Band.from(form);
//                bandsRepository.save(newBand);
//            }
//
//            getBandssPage(model, null);
//        }
//
//        @GetMapping(path = "/bands/edit")
//        public String getEditBandsPage(ModelMap model, Long id) {
//            List<Band> list = new ArrayList<Band>();
//            list.add(bandsRepository.findOne(id));
//
//            model.addAttribute("editBand", list);
//            return "bandEdit";
//        }
    }
