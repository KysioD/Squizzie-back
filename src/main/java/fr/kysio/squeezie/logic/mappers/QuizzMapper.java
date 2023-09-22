package fr.kysio.squeezie.logic.mappers;

import fr.kysio.squeezie.data.entities.Quizz;
import fr.kysio.squeezie.logic.dtos.QuizzDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuizzMapper {

    @Mapping(target = "id", source = "idQuizz")
    @Mapping(target = "author", source = "author.username")
    QuizzDto quizzToQuizzDto(Quizz quizz);

    @InheritInverseConfiguration
    Quizz quizzDtoToQuizz(QuizzDto quizzDto);

}
