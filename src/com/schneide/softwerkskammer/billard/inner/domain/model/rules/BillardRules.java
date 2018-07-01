package com.schneide.softwerkskammer.billard.inner.domain.model.rules;

import java.util.stream.StreamSupport;

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
		if (isEmpty(pocketedBalls)) {
			return resultFor(GameState.ongoing, NextPlayer.change);
		}
		if (contains(Suit.black, pocketedBalls)) {
			return resultFor(
					(remainingBalls < 2) ? GameState.won : GameState.lost,
					NextPlayer.change);
		}
		if (contains(Suit.white, pocketedBalls)
				|| contains(playerSuit.other(), pocketedBalls)) {
			return resultFor(GameState.ongoing, NextPlayer.change);
		}
		return resultFor(GameState.ongoing, NextPlayer.stay);
	}

	private boolean isEmpty(Iterable<Ball> pocketedBalls) {
		return !pocketedBalls.iterator().hasNext();
	}

	private boolean contains(Suit required, Iterable<Ball> balls) {
		return StreamSupport.stream(balls.spliterator(), false).anyMatch(b -> b.hasSuit(required));
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
