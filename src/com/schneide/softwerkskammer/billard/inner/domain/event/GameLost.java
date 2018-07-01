package com.schneide.softwerkskammer.billard.inner.domain.event;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;

public class GameLost implements GameEvent {

	private Player player;

	public GameLost(Player by) {
		super();
		this.player = by;
	}

	@Override
	public String describeAsText() {
		return "The match was lost by " + this.player.name();
	}
}
