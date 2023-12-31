package fr.kysio.squeezie.services;

import fr.kysio.squeezie.data.repositories.QuestionRepository;
import fr.kysio.squeezie.logic.dtos.AnswerDto;
import fr.kysio.squeezie.logic.dtos.QuestionDto;
import fr.kysio.squeezie.logic.dtos.QuestionLightDto;
import fr.kysio.squeezie.logic.mappers.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionsService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    private final AnswersService answersService;

    public QuestionLightDto getNextQuestion(Integer quizId, String username) {
        final List<AnswerDto> userAnswers = answersService.listUserAnswersByQuiz(username, quizId);
        final List<QuestionLightDto> questions = questionRepository.findAllByQuizzId(quizId).stream()
                .map(questionMapper::questionToQuestionLightDto)
                .toList();

        // Remove all answered questions from questions list
        final List<QuestionLightDto> filteredQuestions = questions.stream()
                .filter(questionDto -> userAnswers.stream()
                        .noneMatch(answerDto -> answerDto.idQuestion().equals(questionDto.id())))
                .toList();

        if (filteredQuestions.isEmpty()) {
            return null;
        }

        return filteredQuestions.get((int) (Math.random() * filteredQuestions.size()));
    }



}
