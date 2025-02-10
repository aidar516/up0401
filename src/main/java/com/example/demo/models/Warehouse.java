package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotBlank(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Количество не может быть отрицательным")
    private String line;

    @NotBlank(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Количество не может быть отрицательным")
    private String cell;

    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Количество не может быть отрицательным")
    private Integer quantity;

    public Warehouse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}