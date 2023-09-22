package fr.kysio.squeezie.logic.dtos;

import java.time.LocalDateTime;

public record HistoryDto(Integer id, LocalDateTime dateQuizz, Integer score, Integer quizzId) {

}
