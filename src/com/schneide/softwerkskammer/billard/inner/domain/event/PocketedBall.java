package com.schneide.softwerkskammer.billard.inner.domain.event;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Ball;
import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;

public class PocketedBall implements GameEvent {

	private Ball ball;
	private Player player;

	public PocketedBall(Ball ball, Player by) {
		super();
		this.ball = ball;
		this.player = by;
	}

	@Override
	public String describeAsText() {
		return this.ball.denotation() + " pocketed by " + this.player.name();
	}
}
