package com.prudentgaming.betting.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import com.prudentgaming.betting.model.Bet;


public interface BettingDataRepository extends JpaRepository<Bet, Integer> {

	Collection<Bet> findBetsByClientId(Integer clientId);

}
