package ru.netology.Data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestUserRegister {
    private final String login;
    private final String password;
    private final String status;
}
