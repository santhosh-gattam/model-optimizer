package com.razorthink.model.mutator;

import com.razorthink.model.pojo.Model;

public class OptimizerMutator extends Mutator {

	private final static String[] possibleValues = { "ADAM", "GRADIENT_DESCENT", "MOMENTUM", "ADA_GRAD", "RMS_PROP" };

	public OptimizerMutator( String attributeName, boolean selfRegister )
	{
		super(attributeName, selfRegister);
	}

	@Override
	public String mutate( Model model )
	{
		String random = pickRandomFromList(possibleValues);
		model.getHyperParameters().setOptimizer(random);
		return random;
	}

}
