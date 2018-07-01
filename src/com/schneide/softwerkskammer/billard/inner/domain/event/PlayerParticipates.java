package com.schneide.softwerkskammer.billard.inner.domain.event;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;

public class PlayerParticipates implements GameEvent {

	private Player participant;

	public PlayerParticipates(Player participant) {
		super();
		this.participant = participant;
	}

	@Override
	public String describeAsText() {
		return "Player " + this.participant.name() + " joins the game";
	}
}
