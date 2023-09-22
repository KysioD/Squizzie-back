package fr.kysio.squeezie.controllers;

import fr.kysio.squeezie.logic.dtos.HistoryDto;
import fr.kysio.squeezie.services.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/{username}")
    public List<HistoryDto> getHistory(@PathVariable String username) {
        return historyService.listHistory(username);
    }
}
