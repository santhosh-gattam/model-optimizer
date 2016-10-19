package com.razorthink.model.pojo;

import java.util.UUID;

public class ModelDetail {

	private String modelId;
	private String modelName;

	public ModelDetail( String name )
	{
		modelName = name;
		modelId = UUID.randomUUID().toString();
	}

	public String getModelId()
	{
		return modelId;
	}

	public void setModelId( String modelId )
	{
		this.modelId = modelId;
	}

	public String getModelName()
	{
		return modelName;
	}

	public void setModelName( String modelName )
	{
		this.modelName = modelName;
	}

	public static void printHeader()
	{
		System.out.format("%16s|%16s\n", "ID", "NAME");
	}

	public void print()
	{
		System.out.format("%16s|%16s\n", modelId, modelName);
	}
}
