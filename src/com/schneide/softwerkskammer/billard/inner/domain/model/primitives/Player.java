package com.schneide.softwerkskammer.billard.inner.domain.model.primitives;

public class Player {

	private final String name;
	private final Pocketing ability;

	public Player(String name, Pocketing pocketing) {
		super();
		this.name = name;
		this.ability = pocketing;
	}

	public String name() {
		return this.name;
	}

	@Override
	public String toString() {
		return "player " + name();
	}

	public Pocketing ability() {
		return this.ability;
	}
}
