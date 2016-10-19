package com.razorthink.model.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.razorthink.dl.model.executor.core.ModelExecutor;
import com.razorthink.dl.model.executor.core.ModelRunResult;
import com.razorthink.dl.model.generator.core.ModelGenerator;
import com.razorthink.model.constant.Constant;
import com.razorthink.model.db4o.ModelDetailDao;
import com.razorthink.model.db4o.MutationDetailDao;
import com.razorthink.model.mutator.Mutator;
import com.razorthink.model.pojo.Model;
import com.razorthink.model.pojo.ModelDetail;
import com.razorthink.model.pojo.MutationDetail;
import com.razorthink.model.utils.FileUtils;
import com.razorthink.model.utils.JSONUtil;

public class ModelManager {

	private static ModelManager instance = null;

	private ModelGenerator modelGenerator = new ModelGenerator();
	private Map<String, Mutator> mutatorRegistry = new HashMap<>();

	public static ModelManager getInstance()
	{
		if( instance == null )
		{
			instance = new ModelManager();
		}
		return instance;
	}

	/**
	 * Register a mutator to registry.
	 * 
	 * @param attributeName
	 * @param mutator
	 * @return
	 */
	public boolean registerMutator( String attributeName, Mutator mutator )
	{

		if( mutator == null )
			return false;
		System.out.println("Registering mutator for " + attributeName);
		mutatorRegistry.put(attributeName, mutator);
		return true;
	}

	/**
	 * Calls mutate method for all registered mutators
	 * 
	 * @param model
	 * @return
	 */
	private Model mutateModel( Model model )
	{

		for( Entry<String, Mutator> attributeName : mutatorRegistry.entrySet() )
		{
			attributeName.getValue().mutate(model);
		}
		return model;
	}

	public void runNMutation( int n, Model model, int id )
	{
		ModelDetail modelDetails = new ModelDetail(model.getName(), id);
		ModelDetailDao.insertToDB4O(modelDetails, Constant.TARGET_DIR + "Model.data");
		int parentId = modelDetails.getModelId();
		for( int i = 0; i < n; i++ )
		{

			mutateModel(model);
			String pythonScript = modelGenerator.generateModel(JSONUtil.stringify(model));
			ModelRunResult result = ModelExecutor.run(pythonScript);
			String basePath = Constant.TARGET_DIR + "MutatedModels/" + model.getName() + "/" + i + "/";
			writeMutatedModelAndScriptToFileSystem(basePath, model, pythonScript);

			MutationDetail mutationDetail = new MutationDetail(i, parentId, basePath, result.getCost(),
					result.getAccuracy());
			MutationDetailDao.insertToDB4O(mutationDetail, Constant.TARGET_DIR + "ModelMutation.data");
		}
	}

	private void writeMutatedModelAndScriptToFileSystem( String basePath, Model model, String pythonScript )
	{
		File directory = new File(basePath);
		if( !directory.exists() )
			directory.mkdirs();

		FileUtils.appendToFile(basePath + "Model.json", JSONUtil.stringify(model));
		FileUtils.appendToFile(basePath + "Script.py", pythonScript);

	}
}
