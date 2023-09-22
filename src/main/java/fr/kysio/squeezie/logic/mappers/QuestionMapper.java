package fr.kysio.squeezie.logic.mappers;

import fr.kysio.squeezie.data.entities.Question;
import fr.kysio.squeezie.logic.dtos.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "id", source = "idQuestion")
    QuestionDto questionToQuestionDto(Question question);

}
