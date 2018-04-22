package app;

import java.awt.EventQueue;

import engine.knn.KnnAlgorithm;
import engine.knn.Sample;
import engine.knn.KnnAlgorithm.DistanceMethod;
import gui.MainWindow;

public class Main {

	public static void main(String[] args) {

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

}
