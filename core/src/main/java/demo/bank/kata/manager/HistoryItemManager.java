package demo.bank.kata.manager;

import demo.bank.kata.dto.HistoryItemDto;
import org.springframework.stereotype.Component;
import org.springframework.util.comparator.Comparators;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class HistoryItemManager {

    private List<HistoryItemDto> listHistoryItem = new LinkedList<>();

    public void saveHistoryItem(HistoryItemDto historyItemDto) {
        // fixme : replace by JPA request
        if (historyItemDto != null) {
            listHistoryItem.add(historyItemDto);
        }
    }

    public List<HistoryItemDto> findByAccount(String idAccount) {
        // fixme : replace by JPA request
        return listHistoryItem
            .stream()
            .filter(historyItemDto -> Objects.equals(idAccount, historyItemDto.getIdAccount()))
            .sorted((o1, o2) -> Comparators.nullsLow().compare(o1.getTimeOperation(), o2.getTimeOperation()))
            .collect(Collectors.toList());
    }
}
