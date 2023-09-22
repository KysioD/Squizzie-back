package fr.kysio.squeezie.services;

import fr.kysio.squeezie.data.entities.Account;
import fr.kysio.squeezie.data.entities.Evaluation;
import fr.kysio.squeezie.data.repositories.AccountRepository;
import fr.kysio.squeezie.data.repositories.EvaluationRepository;
import fr.kysio.squeezie.logic.dtos.EvaluationDto;
import fr.kysio.squeezie.logic.mappers.EvaluationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final EvaluationMapper evaluationMapper;
    private final AccountRepository accountRepository;

    public List<EvaluationDto> listEvaluations(Integer quizz) {
        return evaluationRepository.findAll(quizz)
                .stream().map(evaluationMapper::evaluationToEvaluationDto)
                .toList();
    }

    public List<EvaluationDto> listEvaluations(String username) {
        return evaluationRepository.findAll(username)
                .stream().map(evaluationMapper::evaluationToEvaluationDto)
                .toList();
    }

    public EvaluationDto createEvaluation(EvaluationDto evaluationDto) {
        Account account = accountRepository.findByUsername(evaluationDto.username())
                .orElseThrow(() -> new RuntimeException("unknown account " + evaluationDto.username()));

        Evaluation evaluation = evaluationMapper.evaluationDtoToEvaluation(evaluationDto);
        evaluation.setAccount(account);
        evaluation = evaluationRepository.save(evaluation);

        return evaluationMapper.evaluationToEvaluationDto(evaluation);
    }

}
