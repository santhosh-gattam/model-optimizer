package com.razorthink.model.pojo;

public class MutationDetail implements Comparable<MutationDetail> {

	private int mutationId = 0;
	private String parentId;
	private String path;
	private Double cost;
	private Double accuracy;
	private int rank;

	public MutationDetail( int mutationId, String parentId, String path, Double cost, Double accuracy )
	{
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

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId( String parentId )
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
		System.out.format("%16s|%16s|%32s|%16s|%16s|%16s\n", "ID", "PARENT_ID", "FILE_PATH", "COST", "ACCURACY",
				"RANK");

	}

	public void print()
	{
		System.out.format("%16d|%16s|%32s|%16f|%16f|%16d\n", mutationId, parentId, path, cost, accuracy, rank);

	}

	@Override
	public boolean equals( Object obj )
	{
		MutationDetail other = (MutationDetail) obj;
		return (other.getMutationId() == this.getMutationId() && other.getParentId() == this.getParentId());
	}

	@Override
	public int compareTo( MutationDetail o )
	{
		if( o.getAccuracy() > this.accuracy )
			return 1;
		return 0;
	}

	public int getRank()
	{
		return rank;
	}

	public void setRank( int rank )
	{
		this.rank = rank;
	}

}
