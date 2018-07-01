package com.schneide.softwerkskammer.billard.outer.database.player.adapter;

import java.util.Random;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Ball;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Pocketing;
import com.schneide.softwerkskammer.billard.inner.usecase.requirements.PlayerRecruiter;

public class PlayerFromDatabase implements PlayerRecruiter {

	private PlayerCharacteristicsProvider database;
	private Random randomness;

	public PlayerFromDatabase(
			PlayerCharacteristicsProvider database,
			Random randomness) {
		super();
		this.database = database;
		this.randomness = randomness;
	}

	@Override
	public Player willingToPlay() {
		final int currentlyAvailable = this.database.playerCount();
		final int chosen = this.randomness.nextInt(currentlyAvailable);
		return new Player(
				this.database.nameOf(chosen),
				new Ability(
						this.database.abilityOf(chosen),
						this.randomness));
	}

	private static class Ability implements Pocketing {

		private final double factor;
		private final Random randomness;

		public Ability(
				final double factor,
				final Random randomness) {
			super();
			this.factor = factor;
			this.randomness = randomness;
		}

		@Override
		public boolean isPocketed(Ball ball) {
			final double currentRandomValue = randomness.nextDouble();
			return (currentRandomValue < this.factor);
		}
	}
}
