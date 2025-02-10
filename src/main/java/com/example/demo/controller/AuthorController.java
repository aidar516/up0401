package com.example.demo.controller;

import com.example.demo.models.Author;
import com.example.demo.models.Book;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping()
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors/all";
    }

    @GetMapping("/new")
    public String createAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/create";
    }

    @GetMapping("/edit/{id}")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorRepository.findById(id).orElse(null));
        return "authors/update";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute Author author) {
        for (Book book : author.getBooks()) {
            book.setAuthor(author);
        }
        return "redirect:/authors";
    }

    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id, @ModelAttribute Author updatedAuthor) {
        Author existingAuthor = authorRepository.findById(id).orElseThrow();

        existingAuthor.setName(updatedAuthor.getName());
        return "redirect:/authors";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElseThrow();

        author.getBooks().clear();
        authorRepository.save(author);

        authorRepository.delete(author);
        return "redirect:/authors";
    }
}
