package fr.kysio.squeezie.controllers;

import fr.kysio.squeezie.logic.dtos.AccountDto;
import fr.kysio.squeezie.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{username}")
    public AccountDto getAccount(@PathVariable String username) {
        return accountService.getAccount(username);
    }

    @PostMapping("/")
    public AccountDto createAccount(@RequestBody AccountDto request) {
        return accountService.createAccount(request);
    }

}
