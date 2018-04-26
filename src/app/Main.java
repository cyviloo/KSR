package app;

import java.awt.EventQueue;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import controller.Controller;
import gui.MainWindow;

public class Main {

	public static void main(String[] args) {
		/*
		 * Interactive mode (when no parameters given).
		 */
		if(args.length == 0) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainWindow window = new MainWindow();
						window.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		/*
		 * Non-interactive mode. For automated run.
		 */
		else {
			Controller controller = new Controller(
					ProgramArgsToExperimentParams.prepareParamsFromArgs(args));
			try {
				controller.runExperiment();
			} catch (JAXBException e) {
				e.printStackTrace();
				System.out.println("Something went wrong during experiment!");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Something went wrong during experiment!");
			}
		}
	}
}
