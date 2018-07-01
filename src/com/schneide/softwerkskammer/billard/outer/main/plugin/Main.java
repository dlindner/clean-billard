package com.schneide.softwerkskammer.billard.outer.main.plugin;

import java.util.Random;

import com.schneide.softwerkskammer.billard.inner.usecase.Match;
import com.schneide.softwerkskammer.billard.inner.usecase.requirements.GameNarrator;
import com.schneide.softwerkskammer.billard.inner.usecase.requirements.PlayerRecruiter;
import com.schneide.softwerkskammer.billard.outer.database.player.adapter.PlayerFromDatabase;
import com.schneide.softwerkskammer.billard.outer.database.player.plugin.HardcodedNames;
import com.schneide.softwerkskammer.billard.outer.gui.plugin.ConsoleDisplay;

public final class Main {

	private Main() {
		super();
	}

	public static void main(String[] arguments) throws InterruptedException {
		final Random randomness = createRandomnessFor(arguments);

		final PlayerRecruiter playerPool = new PlayerFromDatabase(
				new HardcodedNames(randomness),
				randomness);
		final GameNarrator display = new ConsoleDisplay();

		Match someMatch = Match.startWith(playerPool, display);
		someMatch.play();
	}

	private static Random createRandomnessFor(String[] arguments) {
		final long randomSeed = randomSeedFrom(arguments);
		System.out.println("Random seed: " + randomSeed);
		final Random result = new Random(randomSeed);
		return result;
	}

	private static long randomSeedFrom(String[] arguments) {
		if (arguments.length > 0) {
			return Long.parseLong(arguments[0]);
		}
		final Random seedGenerator = new Random();
		final long randomSeed = seedGenerator.nextLong();
		return randomSeed;
	}
}
