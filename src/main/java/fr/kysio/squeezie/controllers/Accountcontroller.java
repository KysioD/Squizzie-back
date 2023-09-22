package fr.kysio.squeezie.controllers;

import fr.kysio.squeezie.logic.dtos.AccountDto;
import fr.kysio.squeezie.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class Accountcontroller {

    private final AccountService accountService;

    @PostMapping("/")
    public AccountDto createAccoutn(@RequestBody AccountDto request) {
        return accountService.createAccount(request);
    }

}
