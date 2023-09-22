package fr.kysio.squeezie.logic.dtos;

import java.time.LocalDateTime;

public record AnswerDto(Integer id, Integer idQuestion, String username, LocalDateTime answerDate, Boolean response) {}
