package fr.kysio.squeezie.logic.mappers;

import fr.kysio.squeezie.data.entities.History;
import fr.kysio.squeezie.logic.dtos.HistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    @Mapping(target = "id", source = "idHistory")
    @Mapping(target = "quizzId", source = "quizz.idQuizz")
    HistoryDto historyToHistoryDto(History history);

}
