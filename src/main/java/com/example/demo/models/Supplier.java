package com.example.demo.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 20, message = "Имя должно содержать 2-20 символов")
    private String name;

    @NotBlank(message = "Поле не должно быть пустым")
    @Pattern(regexp = "^\\d{11}$", message = "Телефон должен содержать ровно 11 цифр")
    private String phone;

    @NotBlank(message = "Поле не должно быть пустым")
    private String address;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
