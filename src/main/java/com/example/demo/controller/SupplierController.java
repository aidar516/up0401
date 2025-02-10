    package com.example.demo.controller;

    import com.example.demo.models.Supplier;
    import com.example.demo.repository.SupplierRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;

    import javax.validation.Valid;
    import java.util.List;

    @Controller
    @RequestMapping("/suppliers")
    public class SupplierController {

        @Autowired
        private SupplierRepository supplierRepository;

        @GetMapping
        public String index(Model model) {
            List<Supplier> suppliers = supplierRepository.findAll();
            model.addAttribute("suppliers", suppliers);
            return "supplier/all";
        }

        @GetMapping("/new")
        public String newSupplierForm(Model model) {
            model.addAttribute("supplier", new Supplier());
            return "supplier/create";
        }

        @PostMapping()
        public String createSupplier(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult result, Model model) {
            if (result.hasErrors()) {
                model.addAttribute("supplier", supplier);  // передаем ошибку обратно в форму
                return "supplier/create";  // возвращаемся к форме
            }
            supplierRepository.save(supplier);
            return "redirect:/suppliers";  // успешный редирект
        }

        @GetMapping("/edit/{id}")
        public String editSupplierForm(@PathVariable Long id, Model model) {
            Supplier supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid supplier ID"));
            model.addAttribute("supplier", supplier);
            return "supplier/update";
        }


        @PostMapping("/update/{id}")
        public String updateSupplier(@PathVariable Long id, @Valid @ModelAttribute Supplier supplier, BindingResult result, Model model) {
            System.out.println("Received id: " + id);
            if (result.hasErrors()) {
                model.addAttribute("supplier", supplier);
                return "supplier/update";
            }
            supplier.setId(id);
            supplierRepository.save(supplier);
            return "redirect:/suppliers";
        }


        @PostMapping("/delete/{id}")
        public String deleteSupplier(@PathVariable Long id) {
            supplierRepository.deleteById(id);
            return "redirect:/suppliers";
        }
    }