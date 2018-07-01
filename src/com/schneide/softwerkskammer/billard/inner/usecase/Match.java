package com.schneide.softwerkskammer.billard.inner.usecase;

import com.schneide.softwerkskammer.billard.inner.domain.model.composites.Game;
import com.schneide.softwerkskammer.billard.inner.domain.model.composites.Players;
import com.schneide.softwerkskammer.billard.inner.domain.model.composites.Table;
import com.schneide.softwerkskammer.billard.inner.domain.model.rules.BillardRules;

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
