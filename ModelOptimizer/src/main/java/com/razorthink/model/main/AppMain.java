package com.razorthink.model.main;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import com.razorthink.model.constant.Constant;
import com.razorthink.model.core.ModelManager;
import com.razorthink.model.db4o.ModelDetailDao;
import com.razorthink.model.db4o.MutationDetailDao;
import com.razorthink.model.mutator.CostMutator;
import com.razorthink.model.mutator.OptimizerMutator;
import com.razorthink.model.pojo.Model;
import com.razorthink.model.pojo.MutationDetail;
import com.razorthink.model.utils.FileUtils;
import com.razorthink.model.utils.JSONUtil;

public class AppMain {

	private static ModelManager modelManager = ModelManager.getInstance();

	public static void main( String[] args ) throws Exception
	{
		String fileName = "application.properties";
		InputStream input = FileUtils.getFileInputStream(fileName);
		if( input == null )
		{
			System.out.println("Sorry, unable to find " + fileName);
			return;
		}

		try
		{
			Properties properties = new Properties();
			properties.load(input);
			Constant.TARGET_DIR = properties.getProperty("TARGET_DIR", Constant.TARGET_DIR);
		}
		catch( Exception e )
		{
			throw e;
		}

		String mutationTableName = "ModelMutation.data";
		String modelTableName = "Model.data";

		// delete previous run files are directories
		MutationDetailDao.truncateAndDelete(mutationTableName);
		MutationDetailDao.truncateAndDelete(modelTableName);
		org.apache.commons.io.FileUtils.deleteDirectory(new File(Constant.TARGET_DIR + "MutatedModels/"));

		String modelJSonFilePath = "Model.json";
		String jsonModel = FileUtils.getResourceFileAsString(modelJSonFilePath);

		Model sampleModel = JSONUtil.jacksonParse(jsonModel, Model.class);
		registerMutators();
		modelManager.runNMutation(10, sampleModel);

		modelManager.runNMutation(10, sampleModel);

		ModelDetailDao.printAllDBObject(modelTableName);

		Timer timer = new Timer();
		timer.schedule(new RankByAccuracy(), 0, 1000);

	}

	public static ArrayList<MutationDetail> getTopResults( double percent, List<MutationDetail> result )
	{
		if( result == null || result.isEmpty() )
			return null;
		int numberOfRecords = (int) (result.size() * percent / 100);
		System.out.println("Number of records to pull " + numberOfRecords);
		return new ArrayList<>(result.subList(0, numberOfRecords));
	}

	public static void registerMutators()
	{
		boolean selfRegister = true;
		new CostMutator("COST", selfRegister);
		new OptimizerMutator("OPTIMIZER", selfRegister);
	}

	public static class RankByAccuracy extends TimerTask {

		@Override
		public void run()
		{
			String mutationTableName = "ModelMutation.data";
			String modelTableName = "Model.data";
			MutationDetailDao.rankByAttribute(modelTableName, mutationTableName, "accuracy");

			//		ArrayList<MutationDetail> top10Per = getTopResults(50.0, result);
			//
			//		for( MutationDetail mutationDetail : top10Per )
			//		{
			//			mutationDetail.print();
			//		}

			MutationDetailDao.printAllDBObject(mutationTableName);
		}

	}

}
