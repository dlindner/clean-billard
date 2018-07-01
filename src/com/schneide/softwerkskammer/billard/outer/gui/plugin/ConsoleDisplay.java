package com.schneide.softwerkskammer.billard.outer.gui.plugin;

import com.schneide.softwerkskammer.billard.inner.domain.event.GameEvent;
import com.schneide.softwerkskammer.billard.inner.usecase.requirements.GameNarrator;

public class ConsoleDisplay implements GameNarrator {

	public ConsoleDisplay() {
		super();
	}

	@Override
	public void announce(GameEvent change) {
		System.out.println(change.describeAsText());
	}
}
