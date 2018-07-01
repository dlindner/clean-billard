package com.schneide.softwerkskammer.billard.inner.domain.engine.rules;

public interface StrikeResult {

	public GameState state();

	public NextPlayer successor();
}
