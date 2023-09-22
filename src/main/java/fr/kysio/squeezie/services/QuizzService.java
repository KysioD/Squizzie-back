package fr.kysio.squeezie.services;

import fr.kysio.squeezie.data.entities.Account;
import fr.kysio.squeezie.data.entities.Question;
import fr.kysio.squeezie.data.entities.Quizz;
import fr.kysio.squeezie.data.repositories.AccountRepository;
import fr.kysio.squeezie.data.repositories.QuestionRepository;
import fr.kysio.squeezie.data.repositories.QuizzRepository;
import fr.kysio.squeezie.exceptions.UnknownEntityException;
import fr.kysio.squeezie.logic.dtos.QuizzDto;
import fr.kysio.squeezie.logic.mappers.QuizzMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizzService {

    private final QuizzRepository quizzRepository;
    private final AccountRepository accountRepository;
    private final QuizzMapper quizzMapper;
    private final AnswersService answersService;
    private final QuestionRepository questionRepository;

    public List<QuizzDto> getQuizzs() {
        return quizzRepository.findAll().stream()
                .map(quizzMapper::quizzToQuizzDto)
                .collect(Collectors.toList());
    }

    public QuizzDto createQuizz(QuizzDto request) {
        Quizz quizz = quizzMapper.quizzDtoToQuizz(request);

        Account account = accountRepository.findByUsername(request.author())
                .orElseThrow(() -> new UnknownEntityException("unknown account " + request.author()));

        quizz.setAuthor(account);

        for (Question question : quizz.getQuestions()) {
            question.setQuizz(quizz);
        }

        quizz = quizzRepository.save(quizz);
        return quizzMapper.quizzToQuizzDto(quizz);
    }

    public QuizzDto getQuizz(Integer quizzId) {
        Quizz quizz = quizzRepository.findById(quizzId)
                .orElseThrow(() -> new UnknownEntityException("unknown quizz " + quizzId));
        return quizzMapper.quizzToQuizzDto(quizz);
    }

    public Float getResultPercentage(Integer quizzId, String username) {
        final Quizz quizz = quizzRepository.findById(quizzId)
                .orElseThrow(() -> new UnknownEntityException("unknown quizz " + quizzId));
        System.out.println("answers : " + answersService.listUserAnswersByQuiz(username, quizzId));
        System.out.println("answers : " + answersService.listUserAnswersByQuiz(username, quizzId).size());
        Long result = answersService.listUserAnswersByQuiz(username, quizzId).stream()
                .filter(answerDto ->
                        answerDto.response().booleanValue() ==
                                questionRepository
                                        .findById(answerDto.idQuestion())
                                        .orElseThrow(() -> new UnknownEntityException("unknown question " + answerDto.idQuestion()))
                                        .getCorrect().booleanValue())
                .count();

        return ((float) result) / quizz.getQuestions().size() * 100;
    }

}
