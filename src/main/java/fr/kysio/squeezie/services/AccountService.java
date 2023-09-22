package fr.kysio.squeezie.services;

import fr.kysio.squeezie.data.entities.Account;
import fr.kysio.squeezie.data.repositories.AccountRepository;
import fr.kysio.squeezie.logic.dtos.AccountDto;
import fr.kysio.squeezie.logic.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto createAccount(AccountDto request) {
        Account account = accountMapper.accountDtoToAccount(request);
        account = accountRepository.save(account);
        return accountMapper.accountToAccountDto(account);
    }

}
