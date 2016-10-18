package com.razorthink.model.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Model {

	private String description;

	private Map<String, Layers> layers = new HashMap<String, Layers>();

	private String name;

	@JsonProperty( "hyper_parameters" )
	private HyperParameters hyperParameters;

	@JsonProperty( "run_parameters" )

	private RunParameters runParameters;

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public Map<String, Layers> getLayers()
	{
		return layers;
	}

	public void setLayers( Map<String, Layers> layers )
	{
		this.layers = layers;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public HyperParameters getHyperParameters()
	{
		return hyperParameters;
	}

	public void setHyperParameters( HyperParameters hyperParameters )
	{
		this.hyperParameters = hyperParameters;
	}

	public RunParameters getRunParameters()
	{
		return runParameters;
	}

	public void setRunParameters( RunParameters runParameters )
	{
		this.runParameters = runParameters;
	}

	@Override
	public String toString()
	{
		return "Model [description = " + description + ", layers = " + layers + ", name = " + name
				+ ", hyperParameters = " + hyperParameters + ", runParameters = " + runParameters + "]";
	}

	public static class Layers {

		@JsonProperty( "layer_parameters" )
		private LayerParameters layerParameters;
		@JsonProperty( "layer_name" )
		private String layerName;
		@JsonProperty( "layer_type" )
		private String layerType;
		@JsonProperty( "file_parameters" )
		private FileParameters fileParameters;

		public String getLayerName()
		{
			return layerName;
		}

		public void setLayerName( String layerName )
		{
			this.layerName = layerName;
		}

		public String getLayerType()
		{
			return layerType;
		}

		public void setLayerType( String layerType )
		{
			this.layerType = layerType;
		}

		public LayerParameters getLayerParameters()
		{
			return layerParameters;
		}

		public void setLayerParameters( LayerParameters layerParameters )
		{
			this.layerParameters = layerParameters;
		}

		@Override
		public String toString()
		{
			return "Layers [layerParameters = " + layerParameters + ", layerName = " + layerName + ", layer_type = "
					+ layerType + " , FileParameters = " + fileParameters + " ]";
		}

		public static class LayerParameters {

			@JsonProperty( "layer_bias" )
			private LayerBias layerBias;

			@JsonProperty( "layer_weights" )
			private LayerWeights layerWeights;

			@JsonProperty( "layer_activation" )
			private String layerActivation;

			@JsonProperty( "layer_nodes" )
			private String layerNodes;

			public LayerBias getLayerBias()
			{
				return layerBias;
			}

			public void setLayerBias( LayerBias layerBias )
			{
				this.layerBias = layerBias;
			}

			public LayerWeights getLayerWeights()
			{
				return layerWeights;
			}

			public void setLayerWeights( LayerWeights layerWeights )
			{
				this.layerWeights = layerWeights;
			}

			public String getLayerActivation()
			{
				return layerActivation;
			}

			public void setLayerActivation( String layerActivation )
			{
				this.layerActivation = layerActivation;
			}

			public String getLayerNodes()
			{
				return layerNodes;
			}

			public void setLayerNodes( String layerNodes )
			{
				this.layerNodes = layerNodes;
			}

			@Override
			public String toString()
			{
				return "LayerParameters [layerBias = " + layerBias + ", layerWeights = " + layerWeights
						+ ", layerActivation = " + layerActivation + ", layerNodes = " + layerNodes + "]";
			}

			public static class LayerBias {

				@JsonProperty( "initializer_parameters" )
				private LayerWeights.InitializerParameters initializerParameters;

				@JsonProperty( "initializer_type" )
				private String initializerType;

				public LayerWeights.InitializerParameters getInitializerParameters()
				{
					return initializerParameters;
				}

				public void setInitializerParameters( LayerWeights.InitializerParameters initializerParameters )
				{
					this.initializerParameters = initializerParameters;
				}

				public String getInitializerType()
				{
					return initializerType;
				}

				public void setInitializerType( String initializerType )
				{
					this.initializerType = initializerType;
				}

				@Override
				public String toString()
				{
					return new StringBuilder().append("LayerBias [initializerParameters = ")
							.append(initializerParameters).append(", initializerType = ").append(initializerType)
							.append("]").toString();
				}
			}

			public static class LayerWeights {

				@JsonProperty( "initializer_parameters" )
				private InitializerParameters initializerParameters;

				@JsonProperty( "initializer_type" )
				private String initializerType;

				public InitializerParameters getInitializerParameters()
				{
					return initializerParameters;
				}

				public void setInitializerParameters( InitializerParameters initializerParameters )
				{
					this.initializerParameters = initializerParameters;
				}

				public String getInitializerType()
				{
					return initializerType;
				}

				public void setInitializerType( String initializerType )
				{
					this.initializerType = initializerType;
				}

				@Override
				public String toString()
				{
					return new StringBuilder().append("LayerWeights [initializerParameters = ")
							.append(initializerParameters).append(", initializerType = ").append(initializerType)
							.append("]").toString();
				}

				public static class InitializerParameters {

					@JsonProperty( "max_val" )
					private String maxVal;

					@JsonProperty( "seed" )
					private String seed;

					@JsonProperty( "min_val" )
					private String minVal;

					public String getMaxVal()
					{
						return maxVal;
					}

					public void setMaxVal( String maxVal )
					{
						this.maxVal = maxVal;
					}

					public String getSeed()
					{
						return seed;
					}

					public void setSeed( String seed )
					{
						this.seed = seed;
					}

					public String getMinVal()
					{
						return minVal;
					}

					public void setMinVal( String minVal )
					{
						this.minVal = minVal;
					}

					@Override
					public String toString()
					{
						return new StringBuilder().append("InitializerParameters [maxVal = ").append(maxVal)
								.append(", seed = ").append(seed).append(", minVal = ").append(minVal).append("]")
								.toString();
					}
				}
			}
		}

		public static class FileParameters {

			private String randomize;

			private String delimiter;

			private String normalize;

			@JsonProperty( "split_ratio" )
			private String splitRatio;

			@JsonProperty( "label_vector" )
			private String labelVector;
			@JsonProperty( "file_name" )
			private String fileName;

			public String getRandomize()
			{
				return randomize;
			}

			public void setRandomize( String randomize )
			{
				this.randomize = randomize;
			}

			public String getDelimiter()
			{
				return delimiter;
			}

			public void setDelimiter( String delimiter )
			{
				this.delimiter = delimiter;
			}

			public String getNormalize()
			{
				return normalize;
			}

			public void setNormalize( String normalize )
			{
				this.normalize = normalize;
			}

			public String getSplitRatio()
			{
				return splitRatio;
			}

			public void setSplitRatio( String splitRatio )
			{
				this.splitRatio = splitRatio;
			}

			public String getLabelVector()
			{
				return labelVector;
			}

			public void setLabelVector( String labelVector )
			{
				this.labelVector = labelVector;
			}

			public String getFileName()
			{
				return fileName;
			}

			public void setFileName( String fileName )
			{
				this.fileName = fileName;
			}

			@Override
			public String toString()
			{
				return new StringBuilder().append("FileParameters [randomize = ").append(randomize)
						.append(", delimiter = ").append(delimiter).append(", normalize = ").append(normalize)
						.append(", splitRatio = ").append(splitRatio).append(", labelVector = ").append(labelVector)
						.append(", fileName = ").append(fileName).append("]").toString();
			}
		}

	}

	public static class HyperParameters {

		@JsonProperty( "training_mode" )
		private String trainingMode;

		private String optimizer;

		private String cost;

		public String getTrainingMode()
		{
			return trainingMode;
		}

		public void setTrainingMode( String trainingMode )
		{
			this.trainingMode = trainingMode;
		}

		public String getOptimizer()
		{
			return optimizer;
		}

		public void setOptimizer( String optimizer )
		{
			this.optimizer = optimizer;
		}

		public String getCost()
		{
			return cost;
		}

		public void setCost( String cost )
		{
			this.cost = cost;
		}

		@Override
		public String toString()
		{
			return new StringBuilder().append("HyperParameters [trainingMode = ").append(trainingMode)
					.append(", optimizer = ").append(optimizer).append(", cost = ").append(cost).append("]").toString();
		}
	}

	public static class RunParameters {

		@JsonProperty( "display_step" )
		private String displayStep;

		private String epoch;

		@JsonProperty( "learning_rate" )
		private String learningRate;

		public String getDisplayStep()
		{
			return displayStep;
		}

		public void setDisplayStep( String displayStep )
		{
			this.displayStep = displayStep;
		}

		public String getEpoch()
		{
			return epoch;
		}

		public void setEpoch( String epoch )
		{
			this.epoch = epoch;
		}

		public String getLearning_rate()
		{
			return learningRate;
		}

		public void setLearning_rate( String learningRate )
		{
			this.learningRate = learningRate;
		}

		@Override
		public String toString()
		{
			return new StringBuilder().append("RunParameters [displayStep = ").append(displayStep).append(", epoch = ")
					.append(epoch).append(", learningRate = ").append(learningRate).append("]").toString();
		}
	}

}