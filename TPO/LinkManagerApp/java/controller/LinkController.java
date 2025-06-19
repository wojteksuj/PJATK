package org.example.s31087tpo10.controller;

import jakarta.validation.Valid;
import org.example.s31087tpo10.model.CreateDTO;
import org.example.s31087tpo10.model.ResponseDTO;
import org.example.s31087tpo10.model.UpdateDTO;
import org.example.s31087tpo10.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("createDTO")) {
            model.addAttribute("createDTO", new CreateDTO());
        }
        return "index";
    }

    @PostMapping("/create")
    public String createLink(@Valid @ModelAttribute("createDTO") CreateDTO dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createDTO", bindingResult);
            redirectAttributes.addFlashAttribute("createDTO", dto);
            return "redirect:/";
        }

        ResponseDTO created = linkService.addLink(dto);
        redirectAttributes.addFlashAttribute("success",
                "Link '" + created.getName() + "' created successfully with ID: " + created.getId());
        return "redirect:/";
    }



    @GetMapping("/view")
    public String viewInfo(@RequestParam String name,
                           @RequestParam(required = false) String password,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            Optional<ResponseDTO> result = linkService.getByName(name, password);
            if (result.isPresent()) {
                ResponseDTO link = result.get();
                model.addAttribute("link", link);

                if (!model.containsAttribute("updateDTO")) {
                    UpdateDTO dto = new UpdateDTO();
                    dto.setName(link.getName());
                    dto.setTargetUrl(link.getTargetUrl());
                    dto.setPassword(password);
                    model.addAttribute("updateDTO", dto);
                }

                model.addAttribute("password", password);

                return "view";
            } else {
                redirectAttributes.addFlashAttribute("error", "Link not found");
                return "redirect:/";
            }
        } catch (SecurityException e) {
            redirectAttributes.addFlashAttribute("error", "Wrong password");
            return "redirect:/";
        }
    }

    @PostMapping("/edit/{id}")
    public String editLink(
            @PathVariable String id,
            @Valid @ModelAttribute("updateDTO") UpdateDTO dto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            linkService.getById(id).ifPresent(link -> model.addAttribute("link", link));
            return "view";
        }

        try {
            linkService.update(id, dto);
            redirectAttributes.addFlashAttribute("success", "Link updated successfully.");
        } catch (SecurityException e) {
            redirectAttributes.addFlashAttribute("error", "Wrong password.");
        }

        return "redirect:/view?name=" + dto.getName() + "&password=" + dto.getPassword();
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id,
                         @RequestParam(required = false) String password,
                         RedirectAttributes redirectAttributes) {
        try {
            boolean deleted = linkService.delete(id, password);
            if (deleted) {
                redirectAttributes.addFlashAttribute("success", "Link deleted successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "Link not found");
            }
        } catch (SecurityException e) {
            redirectAttributes.addFlashAttribute("error", "Wrong password");
        }
        return "redirect:/";
    }




}
