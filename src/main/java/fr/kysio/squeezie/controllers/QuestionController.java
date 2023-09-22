package fr.kysio.squeezie.controllers;

import fr.kysio.squeezie.logic.dtos.QuestionDto;
import fr.kysio.squeezie.logic.dtos.QuestionLightDto;
import fr.kysio.squeezie.services.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionsService questionsService;

    @GetMapping("/next/{quizzId}/{username}")
    public QuestionLightDto getNextQuestion(@PathVariable Integer quizzId, @PathVariable String username) {
        return questionsService.getNextQuestion(quizzId, username);
    }

}
