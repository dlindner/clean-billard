package com.schneide.softwerkskammer.billard.inner.domain.model.primitives;

public enum Suit {

	full {
		@Override
		public Suit other() {
			return half;
		}
	},
	half {
		@Override
		public Suit other() {
			return full;
		}
	},
	white {
		@Override
		public Suit other() {
			return black;
		}
	},
	black {
		@Override
		public Suit other() {
			return white;
		}
	};

	public abstract Suit other();
}
