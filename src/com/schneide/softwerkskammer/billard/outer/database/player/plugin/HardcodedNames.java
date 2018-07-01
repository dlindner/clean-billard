package com.schneide.softwerkskammer.billard.outer.database.player.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.schneide.softwerkskammer.billard.outer.database.player.adapter.PlayerCharacteristicsProvider;

public class HardcodedNames implements PlayerCharacteristicsProvider {

	private Random randomness;

	public HardcodedNames(Random randomness) {
		super();
		this.randomness = randomness;
	}

	@Override
	public double abilityOf(int playerIndex) {
		final double abilityFactor = randomness.nextDouble() * 0.05D + 0.02D;
		return abilityFactor;
	}

	@Override
	public String nameOf(int playerIndex) {
		return availableNames.get(playerIndex);
	}

	@Override
	public int playerCount() {
		return availableNames.size();
	}

	private static final List<String> availableNames = new ArrayList<>(
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
