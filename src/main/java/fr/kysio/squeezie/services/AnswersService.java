package fr.kysio.squeezie.services;

import fr.kysio.squeezie.data.entities.Account;
import fr.kysio.squeezie.data.repositories.AccountRepository;
import fr.kysio.squeezie.data.repositories.AnswersRepository;
import fr.kysio.squeezie.exceptions.UnknownEntityException;
import fr.kysio.squeezie.logic.dtos.AccountDto;
import fr.kysio.squeezie.logic.dtos.AnswerDto;
import fr.kysio.squeezie.logic.mappers.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswersService {

    private final AnswersRepository answersRepository;
    private final AnswerMapper answerMapper;
    private final AccountService accountService;

    public List<AnswerDto> listUserAnswers(String username) {
        return answersRepository.findAllByUsername(username).stream()
                .map(answerMapper::answerToAnswerDto)
                .toList();
    }

}
