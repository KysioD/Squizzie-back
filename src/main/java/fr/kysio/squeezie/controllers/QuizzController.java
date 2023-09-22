package fr.kysio.squeezie.controllers;

import fr.kysio.squeezie.logic.dtos.QuizzDto;
import fr.kysio.squeezie.services.QuizzService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/quizz")
@RequiredArgsConstructor
public class QuizzController {

    private final QuizzService quizzService;

    @GetMapping("/")
    public List<QuizzDto> getQuizzs() {
        return quizzService.getQuizzs();
    }

    @PostMapping("/")
    public QuizzDto createQuizz(@RequestBody QuizzDto request) {
        return quizzService.createQuizz(request);
    }

}
