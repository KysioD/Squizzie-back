package fr.kysio.squeezie.services;

import fr.kysio.squeezie.data.entities.Account;
import fr.kysio.squeezie.data.entities.Answer;
import fr.kysio.squeezie.data.entities.Question;
import fr.kysio.squeezie.data.repositories.AccountRepository;
import fr.kysio.squeezie.data.repositories.AnswersRepository;
import fr.kysio.squeezie.data.repositories.QuestionRepository;
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
    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;

    public List<AnswerDto> listUserAnswersByQuiz(String username, Integer quizzId) {
        return answersRepository.findAllByUsername(username).stream()
                .map(answerMapper::answerToAnswerDto)
                .toList();
    }

    public AnswerDto answerQuestion(AnswerDto answerDto) {
        final Account account = accountRepository.findByUsername(answerDto.username())
                .orElseThrow(() -> new UnknownEntityException("unknown account " + answerDto.username()));

        final Question question = questionRepository.findById(answerDto.idQuestion())
                .orElseThrow(() -> new UnknownEntityException("unknown question " + answerDto.idQuestion()));

        Answer answer = answerMapper.answerDtoToAnswer(answerDto);
        answer.setAccount(account);
        answer.setQuestion(question);

        answer = answersRepository.save(answer);

        return answerMapper.answerToAnswerDto(answer);
    }

}
