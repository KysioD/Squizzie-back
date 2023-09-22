package fr.kysio.squeezie.services;

import fr.kysio.squeezie.data.entities.*;
import fr.kysio.squeezie.data.repositories.*;
import fr.kysio.squeezie.exceptions.BadRequestException;
import fr.kysio.squeezie.exceptions.UnknownEntityException;
import fr.kysio.squeezie.logic.dtos.QuizzDto;
import fr.kysio.squeezie.logic.mappers.QuizzMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizzService {

    private final QuizzRepository quizzRepository;
    private final AccountRepository accountRepository;
    private final QuizzMapper quizzMapper;
    private final QuestionRepository questionRepository;
    private final HistoryRepository historyRepository;
    private final AnswersRepository answersRepository;

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
        final List<Answer> answers = answersRepository.findAllByUsernameAndQuizz(username, quizzId);

        if (answers.size() != quizz.getQuestions().size()) {
            throw new BadRequestException("not all questions have been answered");
        }

        Long result = answers.stream()
                .filter(answerDto ->
                        answerDto.getResponse().booleanValue() ==
                                questionRepository
                                        .findById(answerDto.getQuestion().getIdQuestion())
                                        .orElseThrow(() -> new UnknownEntityException("unknown question " + answerDto.getQuestion().getIdQuestion()))
                                        .getCorrect().booleanValue())
                .count();

        final float score = ((float) result) / quizz.getQuestions().size() * 100;

        History history = new History();
        history.setQuizz(quizz);
        history.setDateQuizz(LocalDateTime.now());
        history.setScore(score);
        history.setAccount(accountRepository.findByUsername(username)
                .orElseThrow(() -> new UnknownEntityException("unknown account " + username)));
        historyRepository.save(history);

        // Clear existing answers
        answers.forEach(answersRepository::delete);

        return score;
    }

    public Float getGlobalScore(Integer quizzId) {
        final Quizz quizz = quizzRepository.findById(quizzId)
                .orElseThrow(() -> new UnknownEntityException("unknown quizz " + quizzId));
        final List<History> histories = historyRepository.findAllByQuizzId(quizz.getIdQuizz());

        if (histories.isEmpty()) {
            return 0f;
        }

        // Return percentage
        return histories.stream()
                .map(History::getScore)
                .reduce(Float::sum)
                .orElse(0f) / histories.size();
    }

}
