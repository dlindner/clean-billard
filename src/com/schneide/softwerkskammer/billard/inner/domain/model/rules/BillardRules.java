package com.schneide.softwerkskammer.billard.inner.domain.model.rules;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Ball;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Suit;

public class BillardRules {

	public BillardRules() {
		super();
	}

	public StrikeResult evaluateStrike(
			Suit playerSuit,
			Iterable<Ball> pocketedBalls,
			int remainingBalls) {
		if (!pocketedBalls.iterator().hasNext()) {
			return resultFor(GameState.ongoing, NextPlayer.change);
		}
		if (0 == remainingBalls) {
			return resultFor(GameState.won, NextPlayer.change);
		}
//		player Ludwig pockets [Ball 1 (full)]
//				The game is ongoing with 14 balls on the table
//				-----
//				player Ludwig pockets [Ball 12 (half), Ball 8 (black)]
//				The game is ongoing with 12 balls on the table


		for (Ball each : pocketedBalls) {
			if (each.hasSuit(Suit.black)) {
				return resultFor(GameState.lost, NextPlayer.change);
			}
			if (each.hasSuit(Suit.white)
					|| !each.hasSuit(playerSuit)) {
				return resultFor(GameState.ongoing, NextPlayer.change);
			}
		}
		return resultFor(GameState.ongoing, NextPlayer.stay);
	}

	private StrikeResult resultFor(
			final GameState state,
			final NextPlayer next) {
		return new StrikeResult() {
			@Override
			public NextPlayer successor() {
				return next;
			}
			@Override
			public GameState state() {
				return state;
			}
		};
	}
}
