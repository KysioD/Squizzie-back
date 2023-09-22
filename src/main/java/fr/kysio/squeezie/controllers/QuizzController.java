package fr.kysio.squeezie.controllers;

import fr.kysio.squeezie.logic.dtos.QuizzDto;
import fr.kysio.squeezie.services.QuizzService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/quizz")
@RequiredArgsConstructor
public class QuizzController {

    private final QuizzService quizzService;

    @GetMapping("/{quizzId}")
    public QuizzDto getQuizz(@PathVariable Integer quizzId) {
        return quizzService.getQuizz(quizzId);
    }

    @GetMapping("/")
    public List<QuizzDto> getQuizzs() {
        return quizzService.getQuizzs();
    }

    @PostMapping("/")
    public QuizzDto createQuizz(@RequestBody QuizzDto request) {
        return quizzService.createQuizz(request);
    }

    @GetMapping("/{quizzId}/{username}/result")
    public Float getResult(@PathVariable Integer quizzId, @PathVariable String username) {
        return quizzService.getResultPercentage(quizzId, username);
    }

}
