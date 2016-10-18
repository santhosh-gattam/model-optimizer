package com.razorthink.model.pojo;

public class ModelDetail {

	private int modelId = 0;
	private String modelName;

	public ModelDetail(String name) {
		modelName = name;
		modelId++;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public static void printHeader() {
		System.out.format("%16s|%16s", "ID", "NAME");
	}

	public void print() {
		System.out.format("%16d|%16s", modelId, modelName);
	}
}
