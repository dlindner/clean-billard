package com.schneide.softwerkskammer.billard.inner.usecase;

import com.schneide.softwerkskammer.billard.inner.domain.event.MatchStarts;
import com.schneide.softwerkskammer.billard.inner.domain.event.PlayerParticipates;
import com.schneide.softwerkskammer.billard.inner.domain.model.composites.BallSet;
import com.schneide.softwerkskammer.billard.inner.domain.model.composites.Game;
import com.schneide.softwerkskammer.billard.inner.domain.model.composites.Players;
import com.schneide.softwerkskammer.billard.inner.domain.model.composites.Table;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;
import com.schneide.softwerkskammer.billard.inner.domain.model.rules.BillardRules;
import com.schneide.softwerkskammer.billard.inner.usecase.requirements.GameNarrator;
import com.schneide.softwerkskammer.billard.inner.usecase.requirements.PlayerRecruiter;

public class Match {

	private final Game game;
	private final GameNarrator narrator;

	private Match(
			Game game,
			GameNarrator narrator) {
		super();
		this.game = game;
		this.narrator = narrator;
	}

	public static Match startWith(
			PlayerRecruiter recruiter,
			GameNarrator narrator) {
		final Players players = recruitFrom(recruiter);
		players.forEach(player -> narrator.announce(new PlayerParticipates(player)));
		final Table playfield = new Table();
		final Game game = new Game(
				players,
				playfield,
				new BillardRules());
		final BallSet balls = BallSet.create();
		final Game startedGame = game.startWith(balls);
		narrator.announce(new MatchStarts());
		return new Match(
				startedGame,
				narrator);
	}

	public void play() {
		boolean gameCommences = true;
		while (gameCommences) {
			gameCommences = game.turn(this.narrator);
		}
	}

	private static Players recruitFrom(PlayerRecruiter recruiter) {
		Player first = recruiter.willingToPlay();
		Player second = recruitSomebodyNotNamed(first.name(), recruiter);
		return new Players(first, second);
	}

	private static Player recruitSomebodyNotNamed(String blacklisted, PlayerRecruiter recruiter) {
		Player result = null;
		do {
			result = recruiter.willingToPlay();
		} while (blacklisted.equals(result.name()));
		return result;
	}
}
