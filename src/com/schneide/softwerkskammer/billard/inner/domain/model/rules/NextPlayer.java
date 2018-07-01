package com.schneide.softwerkskammer.billard.inner.domain.model.rules;

import com.schneide.softwerkskammer.billard.inner.domain.model.composites.Players;

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
