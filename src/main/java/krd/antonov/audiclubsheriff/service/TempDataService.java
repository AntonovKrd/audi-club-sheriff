package krd.antonov.audiclubsheriff.service;

import krd.antonov.audiclubsheriff.exceptions.TempDataNotFoundException;
import krd.antonov.audiclubsheriff.model.TempData;

import java.util.List;

public interface TempDataService {

    TempData create(String chatId, String stage, String value);

    TempData getLastStageTempDataByChatId(String chatId) throws TempDataNotFoundException;

    List<TempData> getListTempDataByChatId(String chatId) throws TempDataNotFoundException;

    TempData update(TempData tempData);

    void deleteTempDataByChatId(String chatId);

    List<TempData> getAllTempData();

    void deleteAllTempData();
}
