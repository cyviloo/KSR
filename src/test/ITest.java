package test;

public interface ITest {

	/*
	 *  runs the test and returns the boolean value:
	 *  true  for PASS
	 *  false for FAIL
	 */
	public boolean run();

	/*
	 * returns the name of the test
	 */
	public String getName();
}
