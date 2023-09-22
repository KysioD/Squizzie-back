package fr.kysio.squeezie.logic.mappers;

import fr.kysio.squeezie.data.entities.Evaluation;
import fr.kysio.squeezie.logic.dtos.EvaluationDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvaluationMapper {

    @Mapping(target = "id", source = "idEvaluation")
    @Mapping(target = "quizzId", source = "quizz.idQuizz")
    @Mapping(target = "username", source = "account.username")
    EvaluationDto evaluationToEvaluationDto(Evaluation evaluation);

    @InheritInverseConfiguration
    Evaluation evaluationDtoToEvaluation(EvaluationDto evaluationDto);

}
