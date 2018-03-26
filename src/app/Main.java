package app;

import java.awt.EventQueue;

import gui.MainWindow;

public class Main {

	public static void main(String[] args) {
		System.out.print("hello world");
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
