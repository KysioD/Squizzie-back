package fr.kysio.squeezie.logic.dtos;

public record QuizzDto(Integer id, String title, String description, String author, QuestionDto[] questions) {
}