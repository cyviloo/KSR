package app;

import java.awt.EventQueue;

import engine.knn.KnnAlgorithm;
import engine.knn.Sample;
import engine.knn.KnnAlgorithm.DistanceMethod;
import gui.MainWindow;

public class Main {

	public static void main(String[] args) {

		KnnAlgorithm knn = new KnnAlgorithm();
		Sample s = new Sample();
		s.features.add(112.1);
		s.label = 1;
		
		knn.addSample(s);
		knn.addSample(s);
		knn.addSample(s);
		knn.judge(s, 2, DistanceMethod.euclidean);

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
