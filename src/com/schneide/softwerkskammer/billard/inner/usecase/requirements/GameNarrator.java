package com.schneide.softwerkskammer.billard.inner.usecase.requirements;

import com.schneide.softwerkskammer.billard.inner.domain.event.GameEvent;

public interface GameNarrator {

	public void announce(GameEvent change);
}
