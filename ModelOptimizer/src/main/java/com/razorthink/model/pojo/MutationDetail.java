package com.razorthink.model.pojo;

public class MutationDetail {

	private int mutationId = 0;
	private int parentId;
	private String path;
	private Double cost;
	private Double accuracy;

	

	public MutationDetail(int mutationId, int parentId, String path, Double cost, Double accuracy) {
		super();
		this.mutationId = mutationId;
		this.parentId = parentId;
		this.path = path;
		this.cost = cost;
		this.accuracy = accuracy;
	}

	public int getMutationId()
	{
		return mutationId;
	}

	public void setMutationId( int mutationId )
	{
		this.mutationId = mutationId;
	}

	public int getParentId()
	{
		return parentId;
	}

	public void setParentId( int parentId )
	{
		this.parentId = parentId;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath( String path )
	{
		this.path = path;
	}

	public Double getCost()
	{
		return cost;
	}

	public void setCost( Double cost )
	{
		this.cost = cost;
	}

	public Double getAccuracy()
	{
		return accuracy;
	}

	public void setAccuracy( Double accuracy )
	{
		this.accuracy = accuracy;
	}

	public static void printHeader()
	{
		System.out.format("%16s|%16s|%32s|%16s|%16s", "ID", "PARENT_ID", "FILE_PATH", "COST", "ACCURACY");

	}

	public void print()
	{
		System.out.format("%16d|%16d|%32s|%16f|%16f", mutationId, parentId, path, cost, accuracy);

	}

	@Override
	public boolean equals( Object obj )
	{
		MutationDetail other = (MutationDetail) obj;
		return (other.getMutationId() == this.getMutationId() && other.getParentId() == this.getParentId());
	}
}