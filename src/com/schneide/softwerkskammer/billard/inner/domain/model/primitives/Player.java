package com.schneide.softwerkskammer.billard.inner.domain.model.primitives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {

	private final String name;
	private final Pocketing ability;

	public Player(String name, Ability pocketing) {
		super();
		this.name = name;
		this.ability = pocketing;
	}

	public String name() {
		return this.name;
	}

	@Override
	public String toString() {
		return "player " + name();
	}

	public Pocketing ability() {
		return this.ability;
	}

	public static Player randomBy(Random randomness) {
		Collections.shuffle(remainingNames, randomness);
		final String currentName = remainingNames.remove(0);
		final double abilityFactor = randomness.nextDouble() * 0.05D + 0.02D;
		System.out.println(currentName + ": " + abilityFactor);
		return new Player(
				currentName,
				new Ability(
						abilityFactor,
						randomness));
	}

	public static class Ability implements Pocketing {

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

	private static final List<String> remainingNames = new ArrayList<>(
			Arrays.asList(
				"Anne",
				"Bert",
				"Christine",
				"Dave",
				"Emil",
				"Franziska",
				"Gustav",
				"Hanna",
				"Ingo",
				"Konstanze",
				"Ludwig",
				"Marie",
				"Norbert",
				"Olga",
				"Patrick",
				"Roswitha",
				"Steffen",
				"Tanja",
				"Ullrich",
				"Veronica"
			));
}
