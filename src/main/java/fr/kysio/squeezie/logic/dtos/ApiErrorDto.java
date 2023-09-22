package fr.kysio.squeezie.logic.dtos;

import java.time.LocalDateTime;

public record ApiErrorDto(String uuid, String errorCode, String message, Integer statusCode, String statusName,
                          String path, String method, LocalDateTime timestamp) {
}
