package fr.kysio.squeezie.logic.mappers;

import fr.kysio.squeezie.data.entities.Account;
import fr.kysio.squeezie.logic.dtos.AccountDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", source = "idAccount")
    AccountDto accountToAccountDto(Account account);

    @InheritInverseConfiguration
    Account accountDtoToAccount(AccountDto accountDto);

}
