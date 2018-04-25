package test;

import java.io.IOException;

import controller.Controller;
import controller.ExperimentParameters;

public class GetStopListFromFileTest implements ITest {

	@Override
	public boolean run() {
		ExperimentParameters params = new ExperimentParameters();
		params.wordStopListFilename = "data/test_stoplist.txt";
		Controller ctrl = new Controller(params);
		String result;
		try {
			result = ctrl.getWordStoplistAsString();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		if(result.equals("abc\ncde\nfgh\nxyz"))
			return true;
		else
			return false;
	}

	@Override
	public String getName() {
		return "Get word stop list from a file";
	}

}
