package com.schneide.softwerkskammer.billard.inner.domain.event;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;

public class GameWon implements GameEvent {

	private Player player;

	public GameWon(Player by) {
		super();
		this.player = by;
	}

	@Override
	public String describeAsText() {
		return "The match was won by " + this.player.name();
	}
}
