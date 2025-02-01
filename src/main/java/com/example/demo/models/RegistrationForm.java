package com.example.demo.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegistrationForm {
    @NotBlank(message = "Username не может быть пустым")
    private String username;
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;
    @NotBlank(message = "Повтор пароля не может быть пустым")
    private String confirmPassword;
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;
    @NotBlank(message = "Адрес не может быть пустым")
    private String address;
    private boolean active;

    public @NotBlank(message = "Username не может быть пустым") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username не может быть пустым") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Пароль не может быть пустым") @Size(min = 6, message = "Пароль должен содержать минимум 6 символов") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Пароль не может быть пустым") @Size(min = 6, message = "Пароль должен содержать минимум 6 символов") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Повтор пароля не может быть пустым") String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotBlank(message = "Повтор пароля не может быть пустым") String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public @NotBlank(message = "Email не может быть пустым") @Email(message = "Некорректный формат email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email не может быть пустым") @Email(message = "Некорректный формат email") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Адрес не может быть пустым") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Адрес не может быть пустым") String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
