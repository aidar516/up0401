package com.example.demo.controller;

import com.example.demo.models.Genre;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;


    @GetMapping()
    public String getAllGenres(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "genre/all";
    }

    @GetMapping("new")
    public String createGenreForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre/create";
    }

    @GetMapping("/edit/{id}")
    public String editGenreForm(@PathVariable Long id, Model model) {
        model.addAttribute("genre", genreRepository.findById(id).orElse(null));
        return "genre/update";
    }

    @PostMapping("/create")
    public String createGenre(@ModelAttribute Genre genre) {
        genreRepository.save(genre);
        return "redirect:/genres";
    }

    @PostMapping("/update/{id}")
    public String updateGenre(@ModelAttribute Genre genre, @PathVariable Long id) {
        genre.setId(id);
        genreRepository.save(genre);
        return "redirect:/genres";
    }

    @PostMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        genreRepository.delete(genre);
        return "redirect:/genres";
    }
}

