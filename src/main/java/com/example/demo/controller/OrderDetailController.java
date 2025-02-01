package com.example.demo.controller;

import com.example.demo.models.OrderDetail;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public String getAllOrderDetails(Model model) {
        model.addAttribute("orderDetails", detailRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "order-detail/all";
    }

    @GetMapping("/new")
    public String createOrderDetailForm(Model model) {
        model.addAttribute("orderDetail", new OrderDetail());
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "order-detail/create";
    }

    @GetMapping("/edit/{id}")
    public String editOrderDetailForm(@PathVariable Long id, Model model) {
        model.addAttribute("orderDetail", detailRepository.findById(id).orElse(null));
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "order-detail/update";
    }

    @PostMapping("/create")
    public String createOrderDetail(@ModelAttribute OrderDetail orderDetail) {
        detailRepository.save(orderDetail);
        return "redirect:/order-details";
    }

    @PostMapping("/update/{id}")
    public String updateOrderDetail(@PathVariable Long id, @ModelAttribute OrderDetail orderDetail) {
        orderDetail.setId(id);
        detailRepository.save(orderDetail);
        return "redirect:/order-details";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrderDetail(@PathVariable Long id) {
        detailRepository.deleteById(id);
        return "redirect:/order-details";
    }
}