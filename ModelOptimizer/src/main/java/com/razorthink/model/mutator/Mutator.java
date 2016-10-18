package com.razorthink.model.mutator;

import java.util.Random;

import com.razorthink.model.core.ModelManager;
import com.razorthink.model.pojo.Model;

public abstract class Mutator {

	private String attributeName;
	private boolean isSelfRegister;
	public ModelManager modelManager = ModelManager.getInstance();
	private Random randomGenerator = new Random();

	public Mutator( String attributeName, boolean selfRegister )
	{
		this.attributeName = attributeName;
		this.isSelfRegister = selfRegister;
		if( selfRegister )
		{
			modelManager.registerMutator(attributeName, this);
		}
	}

	/**
	 * Util methods to pick a random value from the passed list
	 * 
	 * @param list
	 * @return
	 */
	public String pickRandomFromList( String[] list )
	{

		int index = randomGenerator.nextInt(list.length);
		return list[index];

	}

	/**
	 * Abstract method apply mutation on model object passed and returns the mutated value.
	 * 
	 * @param model
	 * @return
	 */
	public abstract String mutate( Model model );

	public String getAttributeName()
	{
		return attributeName;
	}

	public void setAttributeName( String attributeName )
	{
		this.attributeName = attributeName;
	}

	public boolean isSelfRegister()
	{
		return isSelfRegister;
	}

	public void setSelfRegister( boolean isSelfRegister )
	{
		this.isSelfRegister = isSelfRegister;
	}

}
