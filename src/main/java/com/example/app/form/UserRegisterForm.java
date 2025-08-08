package com.example.app.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserRegisterForm {

    @NotBlank(message = "ユーザー名は必須です")
    private String username;

    @NotBlank(message = "パスワードは必須です")
    private String password;

    @NotBlank(message = "確認用パスワードは必須です")
    private String confirmPassword;
}
