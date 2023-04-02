package com.prudentgaming.betting.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.prudentgaming.betting.model.Bet;
import java.util.Date;
import java.util.List;


public interface BettingDataRepository extends JpaRepository<Bet, Integer> {

	Collection<Bet> findBetsByClientId(Integer clientId);
	Collection<Bet> findBetsByGameAndClientId(String game, Integer clientId);
	@Query(value = "SELECT * FROM betting_data WHERE game_date BETWEEN ?1 AND ?2 AND client_id IN ?3 AND game IN ?4 ORDER BY game_date DESC LIMIT ?5 OFFSET ?6", nativeQuery = true)
    List<Bet> findBetsByDateRangeAndClientsAndGames(Date startDate, Date endDate, List<Integer> clientIds, List<String> games, int limit, int offset);

}
