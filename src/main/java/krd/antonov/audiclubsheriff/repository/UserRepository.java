package krd.antonov.audiclubsheriff.repository;

import krd.antonov.audiclubsheriff.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTgNickname(String tgNickname);
}
