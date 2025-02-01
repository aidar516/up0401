package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Логин не должен быть пустым")
    @Size(min = 2, max = 20, message = "Логин должен содержать 2-20 символов")
    private String username;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Неправильный формат email")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
    private String password;

    private boolean active;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Логин не должен быть пустым") @Size(min = 2, max = 20, message = "Логин должен содержать 2-20 символов") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Логин не должен быть пустым") @Size(min = 2, max = 20, message = "Логин должен содержать 2-20 символов") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Email не должен быть пустым") @Email(message = "Неправильный формат email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email не должен быть пустым") @Email(message = "Неправильный формат email") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Пароль не должен быть пустым") @Size(min = 8, message = "Пароль должен быть не менее 8 символов") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Пароль не должен быть пустым") @Size(min = 8, message = "Пароль должен быть не менее 8 символов") String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
