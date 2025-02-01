package com.example.demo.controller;

import com.example.demo.models.Order;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public String getAllOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order/all";
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        model.addAttribute("currentDate", LocalDate.now().toString());
        model.addAttribute("order", new Order());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "order/create";
    }

    @PostMapping
    public String createOrder(@Valid @ModelAttribute("order") Order order, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("order", order);
            return "order/create";
        }
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        model.addAttribute("currentDate", LocalDate.now().toString());
        model.addAttribute("order", order);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "order/update";
    }

    @PostMapping("/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute Order order) {
        order.setId(id);
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @Transactional
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid order id"));
        return "redirect:/orders";
    }
}
