package com.razorthink.model.main;

import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import com.razorthink.model.constant.Constant;
import com.razorthink.model.core.ModelManager;
import com.razorthink.model.db4o.ModelDetailDao;
import com.razorthink.model.db4o.MutationDetailDao;
import com.razorthink.model.mutator.CostMutator;
import com.razorthink.model.mutator.OptimizerMutator;
import com.razorthink.model.pojo.Model;
import com.razorthink.model.utils.FileUtils;
import com.razorthink.model.utils.JSONUtil;

public class AppMain {

	private static ModelManager modelManager = ModelManager.getInstance();

	public static void main(String[] args) throws Exception {
		String fileName = "application.properties";
		InputStream input = AppMain.class.getClassLoader().getResourceAsStream(fileName);
		if (input == null) {
			System.out.println("Sorry, unable to find " + fileName);
			return;
		}

		try (FileReader reader = new FileReader(fileName)) {
			Properties properties = new Properties();
			properties.load(reader);
			Constant.TARGET_DIR = properties.getProperty("TARGET_DIR", Constant.TARGET_DIR);
		} catch (Exception e) {
			throw e;
		}

		String modelJSonFilePath = "/home/rijo/Documents/Model.josn";
		String jsonModel = FileUtils.readContentAsString(modelJSonFilePath);
		Model sampleModel = JSONUtil.jacksonParse(jsonModel, Model.class);
		registerMutators();
		modelManager.runNMutation(10, sampleModel);

		ModelDetailDao.printAllDBObject("Model.data");

		MutationDetailDao.printAllDBObject("ModelMutation.data");
	}

	public static void registerMutators() {
		boolean selfRegister = true;
		new CostMutator("COST", selfRegister);
		new OptimizerMutator("OPTIMIZER", selfRegister);
	}

}
