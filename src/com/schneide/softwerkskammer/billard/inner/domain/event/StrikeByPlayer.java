package com.schneide.softwerkskammer.billard.inner.domain.event;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;

public class StrikeByPlayer implements GameEvent {

	private Player player;

	public StrikeByPlayer(Player player) {
		super();
		this.player = player;
	}

	@Override
	public String describeAsText() {
		return "Player " + this.player.name() + " strikes!";
	}
}
