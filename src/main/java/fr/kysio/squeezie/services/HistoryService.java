package fr.kysio.squeezie.services;

import fr.kysio.squeezie.data.repositories.HistoryRepository;
import fr.kysio.squeezie.logic.dtos.HistoryDto;
import fr.kysio.squeezie.logic.mappers.HistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    public List<HistoryDto> listHistory(String username) {
        return historyRepository.findAllByAccountName(username)
                .stream()
                .map(historyMapper::historyToHistoryDto)
                .toList();
    }

}
