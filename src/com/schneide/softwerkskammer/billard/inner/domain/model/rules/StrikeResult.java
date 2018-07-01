package com.schneide.softwerkskammer.billard.inner.domain.model.rules;

public interface StrikeResult {

	public GameState state();

	public NextPlayer successor();
}
