package com.schneide.softwerkskammer.billard.outer.main.plugin;

import java.util.Random;

import com.schneide.softwerkskammer.billard.inner.domain.model.composites.Players;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;
import com.schneide.softwerkskammer.billard.inner.usecase.Match;

public final class Main {

	private Main() {
		super();
	}

	public static void main(String[] arguments) throws InterruptedException {
		final Random randomness = createRandomnessFor(arguments);
		final Players players = new Players(
				Player.randomBy(randomness),
				Player.randomBy(randomness));
		Match someMatch = Match.startFor(players);
		someMatch.play();

//		boolean gameCommences = true;
//		while (gameCommences) {
//			gameCommences = game.turn();
//			Thread.sleep(2000L);
//			System.out.println("-----");
//		}
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
