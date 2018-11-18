package com.codegym.controller;

import com.codegym.model.Thing;
import com.codegym.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class ThingController {

    @Autowired
    private ThingService thingService;

    @GetMapping("/")
    public ModelAndView showThing(@PageableDefault(size = 8) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list", "things", thingService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/create-thing")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create", "thing", new Thing());
        return modelAndView;
    }

    @PostMapping("/create-thing")
    public ModelAndView createThing(@Valid @ModelAttribute("thing") Thing thing, BindingResult bindingResult) {
        new Thing().validate(thing, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("create");
        } else {
            thingService.save(thing);
            ModelAndView modelAndView = new ModelAndView("create", "thing", new Thing());
            modelAndView.addObject("message", "New thing created !");
            return modelAndView;
        }
    }

    @GetMapping("/edit-thing/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Thing thing = thingService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit", "thing", thing);
        return modelAndView;
    }

    @PostMapping("/edit-thing")
    public ModelAndView updateThing(@ModelAttribute("thing") Thing thing) {
        thingService.save(thing);
        ModelAndView modelAndView = new ModelAndView("edit", "thing", thing);
        modelAndView.addObject("message", "Update successfully !");
        return modelAndView;
    }

    @GetMapping("/delete-thing/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Thing thing = thingService.findById(id);
        if (thing != null) {
            ModelAndView modelAndView = new ModelAndView("delete", "thing", thing);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-thing")
    public String deleteThing(@ModelAttribute("thing") Thing thing) {
        thingService.remove(thing.getId());
        return "redirect:/";
    }

}
