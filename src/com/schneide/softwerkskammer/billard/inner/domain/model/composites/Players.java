package com.schneide.softwerkskammer.billard.inner.domain.model.composites;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.schneide.softwerkskammer.billard.inner.domain.model.primitives.Player;

public class Players implements Iterator<Player> {

	private final List<Player> asList;
	private volatile int currentIndex;

	public Players(Player one, Player two) {
		super();
		this.asList = Arrays.asList(one, two);
		this.currentIndex = 0;
	}

	public Player current() {
		return playerAt(this.currentIndex);
	}

	public Player next() {
		this.currentIndex++;
		return current();
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	private Player playerAt(int index) {
		return this.asList.get(index % 2);
	}

	public Player first() {
		return playerAt(0);
	}

	public Player second() {
		return playerAt(1);
	}
}
