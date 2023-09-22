package fr.kysio.squeezie.controllers;

import fr.kysio.squeezie.logic.dtos.AnswerDto;
import fr.kysio.squeezie.services.AnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswersService answersService;

    @PostMapping("/")
    public Boolean answerQuestion(@RequestBody AnswerDto request) {
        return answersService.answerQuestion(request);
    }

    @GetMapping("/{quizzId}/{username}")
    public List<AnswerDto> getAnswers(@PathVariable Integer quizzId, @PathVariable String username) {
        return answersService.listUserAnswersByQuiz(username, quizzId);
    }

}
