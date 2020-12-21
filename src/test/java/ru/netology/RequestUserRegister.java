package ru.netology;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RequestUserRegister {
    @NonNull private String login;
    @NonNull private String password;
    @NonNull private String status;
}
