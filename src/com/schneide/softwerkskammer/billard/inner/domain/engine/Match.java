package com.schneide.softwerkskammer.billard.inner.domain.engine;

import com.schneide.softwerkskammer.billard.inner.domain.engine.rules.BillardRules;
import com.schneide.softwerkskammer.billard.inner.domain.model.composite.Game;
import com.schneide.softwerkskammer.billard.inner.domain.model.composite.Players;
import com.schneide.softwerkskammer.billard.inner.domain.model.composite.Table;

public class Match {

	private final Game game;

	private Match(
			Game game) {
		super();
		this.game = game;
	}

	public static Match startFor(Players players) {
		final Table playfield = new Table();
		final Game game = new Game(
				players,
				playfield,
				new BillardRules());
		return new Match(game);
	}

	public void play() {
		boolean gameCommences = true;
		while (gameCommences) {
			gameCommences = game.turn();
		}
	}
}
