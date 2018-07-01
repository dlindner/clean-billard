package com.schneide.softwerkskammer.billard.outer.database.player.adapter;

public interface PlayerCharacteristicsProvider {

	public int playerCount();

	public String nameOf(int playerIndex);

	public double abilityOf(int playerIndex);
}
