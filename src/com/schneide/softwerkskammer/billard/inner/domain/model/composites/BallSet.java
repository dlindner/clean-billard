package com.schneide.softwerkskammer.billard.inner.domain.model.composites;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Ball;

public class BallSet implements Iterable<Ball> {

	public static final int size = 16;

	private final List<Ball> balls;

	private BallSet() {
		super();
		this.balls = new ArrayList<>();
	}

	private void add(Ball ball) {
		this.balls.add(ball);
	}

	public static BallSet create() {
		final BallSet result = new BallSet();
		for (int i = 0; i < size; i++) {
			result.add(Ball.createFor(i));
		}
		return result;
	}

	@Override
	public Iterator<Ball> iterator() {
		return this.balls.iterator();
	}
}
