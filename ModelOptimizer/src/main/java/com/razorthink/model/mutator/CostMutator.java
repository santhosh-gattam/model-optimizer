package com.razorthink.model.mutator;

import com.razorthink.model.pojo.Model;

public class CostMutator extends Mutator {

	private final static String[] possibleValues = { "SOFTMAX_CROSS_ENTROPY", "MEAN_SQUARE_ERROR",
			"SOFTMAX_CROSS_ENTROPY_LOGITS" };

	public CostMutator( String attributeName, boolean selfRegister )
	{
		super(attributeName, selfRegister);
	}

	@Override
	public String mutate( Model model )
	{
		String random = pickRandomFromList(possibleValues);
		model.getHyperParameters().setCost(random);
		return random;
	}

}
