package fr.kysio.squeezie.logic.mappers;

import fr.kysio.squeezie.data.entities.Answer;
import fr.kysio.squeezie.logic.dtos.AnswerDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {


    @Mapping(target = "id", source = "idAnswer")
    @Mapping(target = "idQuestion", source = "question.idQuestion")
    @Mapping(target = "username", source = "user.username")
    AnswerDto answerToAnswerDto(Answer answer);

    @InheritInverseConfiguration
    Answer answerDtoToAnswer(AnswerDto answerDto);

}
