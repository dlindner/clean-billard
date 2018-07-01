package com.schneide.softwerkskammer.billard.inner.domain.model.composites;

import java.util.HashMap;
import java.util.Map;

import com.schneide.softwerkskammer.billard.inner.domain.event.GameLost;
import com.schneide.softwerkskammer.billard.inner.domain.event.GameWon;
import com.schneide.softwerkskammer.billard.inner.domain.event.PocketedBall;
import com.schneide.softwerkskammer.billard.inner.domain.event.StrikeByPlayer;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Ball;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Suit;
import com.schneide.softwerkskammer.billard.inner.domain.model.rules.BillardRules;
import com.schneide.softwerkskammer.billard.inner.domain.model.rules.GameState;
import com.schneide.softwerkskammer.billard.inner.domain.model.rules.StrikeResult;
import com.schneide.softwerkskammer.billard.inner.usecase.requirements.GameNarrator;

public class Game {

	private final Players players;
	private final Table table;
	private final Map<Player, Suit> suitAssociation;
	private final BillardRules rules;

	public Game(
			Players players,
			Table table,
			BillardRules rules) {
		super();
		this.players = players;
		this.table = table;
		this.rules = rules;
		this.suitAssociation = associateSuitsTo(players);
	}

	public Game startWith(BallSet balls) {
		this.table.clear();
		this.table.place(balls);
		return this;
	}

	public boolean turn(GameNarrator narrator) {
		final Player activePlayer = this.players.current();
		narrator.announce(new StrikeByPlayer(activePlayer));
		final Iterable<Ball> pocketed = this.table.strikeBy(activePlayer);
		pocketed.forEach(ball -> narrator.announce(new PocketedBall(ball, activePlayer)));
		final int remainingBalls = this.table.ballCount();
		final StrikeResult turnResult = this.rules.evaluateStrike(
				this.suitAssociation.get(activePlayer),
				pocketed,
				remainingBalls);
		turnResult.successor().applyTo(this.players);
		if (GameState.lost == turnResult.state()) {
			narrator.announce(new GameLost(activePlayer));
		}
		if (GameState.won == turnResult.state()) {
			narrator.announce(new GameWon(activePlayer));
		}
		return (GameState.ongoing == turnResult.state());
	}

	private Map<Player, Suit> associateSuitsTo(Players currentPlayers) {
		final Map<Player, Suit> result = new HashMap<>();
		result.put(currentPlayers.first(), Suit.full);
		result.put(currentPlayers.second(), Suit.half);
		return result;
	}
}
