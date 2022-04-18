package krd.antonov.audiclubsheriff.repository;

import krd.antonov.audiclubsheriff.model.TempData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TempDataRepository extends JpaRepository<TempData, Long> {

    Optional<TempData> findFirstByChatIdOrderByStageDesc(String chatId);

    Optional<List<TempData>> findAllByChatId(String chatId);

    void deleteAllByChatId(String chatId);
}
