package com.example.demo.controller;

import com.example.demo.models.Publisher;
import com.example.demo.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping()
    public String getAllPublishers(Model model) {
        model.addAttribute("publishers", publisherRepository.findAll());
        return "publisher/all";
    }

    @GetMapping("/new")
    public String createPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher/create";
    }

    @GetMapping("/edit/{id}")
    public String editPublisherForm(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherRepository.findById(id).orElse(null));
        return "publisher/update";
    }

    @PostMapping("/create")
    public String createPublisher(@ModelAttribute Publisher publisher) {
        publisherRepository.save(publisher);
        return "redirect:/publishers";
    }

    @PostMapping("/update/{id}")
    public String updatePublisher(@PathVariable Long id, @ModelAttribute Publisher publisher) {
        publisher.setId(id);
        publisherRepository.save(publisher);
        return "redirect:/publishers";
    }

    @PostMapping("/delete/{id}")
    public String deletePublisher(@PathVariable Long id) {
        publisherRepository.deleteById(id);
        return "redirect:/publishers";
    }
}