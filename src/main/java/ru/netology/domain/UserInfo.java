package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String city = "Санкт-Петербург";
    private String name;
    private String phone;
}
