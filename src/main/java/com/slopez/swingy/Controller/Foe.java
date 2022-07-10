package com.slopez.swingy.Controller;

import com.slopez.swingy.Model.FoeModel;

public class Foe {
	private FoeModel foe;

	public Foe(FoeModel foeModel) {
		this.foe = foeModel;
	}

	public FoeModel getModel() {
		return this.foe;
	}
}
