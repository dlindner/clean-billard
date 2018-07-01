package com.schneide.softwerkskammer.billard.inner.domain.model.composites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Ball;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;

public class Table {

	private final Set<Ball> placed;

	public Table() {
		super();
		this.placed = new HashSet<>();
	}

	public void clear() {
		this.placed.clear();
	}

	public void place(BallSet balls) {
		balls.forEach(this.placed::add);
	}

	public Iterable<Ball> strikeBy(Player activePlayer) {
		final List<Ball> result = new ArrayList<>();
		for (Ball each : this.placed) {
			if (activePlayer.ability().isPocketed(each)) {
				result.add(each);
			}
		}
		this.placed.removeAll(result);
		this.placed.add(Ball.white);
		return result;
	}

	public int ballCount() {
		return this.placed.size();
	}

	@Override
	public String toString() {
		return "Table with " + this.placed.size() + " balls";
	}
}
