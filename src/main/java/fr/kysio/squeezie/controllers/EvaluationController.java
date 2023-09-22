package fr.kysio.squeezie.controllers;

import fr.kysio.squeezie.logic.dtos.EvaluationDto;
import fr.kysio.squeezie.services.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping("/user/{username}")
    public List<EvaluationDto> listEvaluationsByUsername(@PathVariable String username) {
        return evaluationService.listEvaluations(username);
    }

    @GetMapping("/quizz/{quizzId}")
    public List<EvaluationDto> listEvaluationsByQuizzId(@PathVariable Integer quizzId) {
        return evaluationService.listEvaluations(quizzId);
    }

    @PostMapping("/")
    public EvaluationDto createEvaluation(@RequestBody EvaluationDto evaluationDto) {
        return evaluationService.createEvaluation(evaluationDto);
    }

}
