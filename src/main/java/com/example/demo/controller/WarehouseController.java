package com.example.demo.controller;

import com.example.demo.models.Warehouse;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public String index(Model model) {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        model.addAttribute("warehouses", warehouses);
        return "warehouse/all";
    }

    @GetMapping("/new")
    public String newWarehouseForm(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        model.addAttribute("books", bookRepository.findAll());
        return "warehouse/create";
    }

    @PostMapping
    public String createWarehouse(@Valid @ModelAttribute("warehouse") Warehouse warehouse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("books", bookRepository.findAll());
            result.getAllErrors().forEach(error -> System.out.println("Error: " + error.getDefaultMessage()));
            return "warehouse/create";
        }
        warehouseRepository.save(warehouse);
        return "redirect:/warehouses";
    }


    @GetMapping("/edit/{id}")
    public String editWarehouseForm(@PathVariable Long id, Model model) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse ID"));
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("books", bookRepository.findAll());
        return "warehouse/update";
    }

    @PostMapping("/{id}")
    public String updateWarehouse(@PathVariable Long id, @ModelAttribute Warehouse warehouse) {
        warehouse.setId(id);
        warehouseRepository.save(warehouse);
        return "redirect:/warehouses";
    }

    @PostMapping("/delete/{id}")
    public String deleteWarehouse(@PathVariable Long id) {
        warehouseRepository.deleteById(id);
        return "redirect:/warehouses";
    }
}