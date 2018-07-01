package com.schneide.softwerkskammer.billard.inner.domain.event;

public class MatchStarts implements GameEvent {

	public MatchStarts() {
		super();
	}

	@Override
	public String describeAsText() {
		return "The billard match starts.";
	}
}
