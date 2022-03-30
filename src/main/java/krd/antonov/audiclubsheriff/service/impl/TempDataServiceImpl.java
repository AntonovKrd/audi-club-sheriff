package krd.antonov.audiclubsheriff.service.impl;

import krd.antonov.audiclubsheriff.exceptions.TempDataNotFoundException;
import krd.antonov.audiclubsheriff.model.TempData;
import krd.antonov.audiclubsheriff.repository.TempDataRepository;
import krd.antonov.audiclubsheriff.service.TempDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TempDataServiceImpl implements TempDataService {

    private final TempDataRepository tempDataRepository;

    public TempDataServiceImpl(TempDataRepository tempDataRepository) {
        this.tempDataRepository = tempDataRepository;
    }

    @Override
    @Transactional
    public TempData create(String chatId, String stage, String value) {
        TempData tempData = new TempData().setChatId(chatId).setStage(stage).setValue(value).setDateEvent(LocalDate.now());
        tempDataRepository.save(tempData);
        return tempData;
    }

    @Override
    public TempData getLastStageTempDataByChatId(String chatId) throws TempDataNotFoundException {
        return tempDataRepository.findFirstByChatIdOrderByStage(chatId).orElseThrow(() -> new TempDataNotFoundException(chatId));
    }

    @Override
    public List<TempData> getListTempDataByChatId(String chatId) throws TempDataNotFoundException {
        return tempDataRepository.findAllByChatId(chatId).orElseThrow(() -> new TempDataNotFoundException(chatId));
    }

    @Override
    @Transactional
    public TempData update(TempData tempData) {
        tempDataRepository.save(tempData);
        return tempData;
    }

    @Override
    public void deleteTempDataByChatId(String chatId) {
        tempDataRepository.deleteAllByChatId(chatId);
    }

    @Override
    public List<TempData> getAllTempData() {
        return tempDataRepository.findAll();
    }

    @Override
    public void deleteAllTempData() {
        tempDataRepository.deleteAll();
    }
}
