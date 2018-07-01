package com.schneide.softwerkskammer.billard.inner.domain.model.primitives;

public final class Ball {

	public static final Ball white = new Ball(0, Suit.white);

	private int number;
	private Suit suit;

	private Ball(int number, Suit suit) {
		super();
		this.number = number;
		this.suit = suit;
	}

	public boolean hasSuit(Suit required) {
		return this.suit.equals(required);
	}

	public String denotation() {
		return "Ball " + this.number + " (" + this.suit + ")";
	}

	@Override
	public String toString() {
		return denotation();
	}

	public static Ball createFor(int number) {
		if ((number < 0) || (number > 15)) {
			throw new IllegalArgumentException("Balls need to have a number between 0 and 15, not " + number);
		}
		if (0 == number) {
			return white;
		}
		if (8 == number) {
			return new Ball(8, Suit.black);
		}
		if (number < 8) {
			return new Ball(number, Suit.full);
		}
		return new Ball(number, Suit.half);
	}
}
