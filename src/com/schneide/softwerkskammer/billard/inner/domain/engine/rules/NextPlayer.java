package com.schneide.softwerkskammer.billard.inner.domain.engine.rules;

import com.schneide.softwerkskammer.billard.inner.domain.model.composite.Players;

public enum NextPlayer {

	stay {
		@Override
		public void applyTo(Players players) {
			// does nothing so nothing changes
		}
	},
	change {
		@Override
		public void applyTo(Players players) {
			players.next();
		}
	};

	public abstract void applyTo(Players players);
}
