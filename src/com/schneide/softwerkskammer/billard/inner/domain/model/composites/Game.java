package com.schneide.softwerkskammer.billard.inner.domain.model.composites;

import java.util.HashMap;
import java.util.Map;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Ball;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Suit;
import com.schneide.softwerkskammer.billard.inner.domain.model.rules.BillardRules;
import com.schneide.softwerkskammer.billard.inner.domain.model.rules.GameState;
import com.schneide.softwerkskammer.billard.inner.domain.model.rules.StrikeResult;

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

	public boolean turn() {
		final Player activePlayer = this.players.current();
		final Iterable<Ball> pocketed = this.table.strikeBy(activePlayer);
		System.out.println(activePlayer + " pockets " + pocketed);
		final int remainingBalls = this.table.ballCount();
		final StrikeResult turnResult = this.rules.evaluateStrike(
				this.suitAssociation.get(activePlayer),
				pocketed,
				remainingBalls);
		String additionalInfo = " with " + remainingBalls + " balls on the table";
		if (GameState.ongoing != turnResult.state()) {
			additionalInfo = " for " + activePlayer;
		}
		System.out.println("The game is " + turnResult.state() + additionalInfo);
		turnResult.successor().applyTo(this.players);
		return (GameState.ongoing == turnResult.state());
	}

	private Map<Player, Suit> associateSuitsTo(Players currentPlayers) {
		final Map<Player, Suit> result = new HashMap<>();
		result.put(currentPlayers.first(), Suit.full);
		result.put(currentPlayers.second(), Suit.half);
		return result;
	}
}
