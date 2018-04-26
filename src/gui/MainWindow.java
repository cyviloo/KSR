package gui;

import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import javax.xml.bind.JAXBException;

import controller.Controller;
import controller.ExperimentParameters;
import engine.datainput.DataInputElement.EtiquetteType;
import engine.knn.KnnAlgorithm.DistanceMethod;
import engine.similarity.ISimilarityMeasurer.SimilarityMethod;
import engine.stemmer.IStemmer.StemMethod;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.Color;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroupSimilarity = new ButtonGroup();
	private final ButtonGroup buttonGroupMetric = new ButtonGroup();

	public MainWindow() {
		setTitle("KSR");
		params = new ExperimentParameters();
		params.wordStopListFilename = null;
		params.stemming = StemMethod.no_stemmer;
		params.similarity = SimilarityMethod.binary;
		params.etiquette = EtiquetteType.reuters_places;
		params.metric = DistanceMethod.euclidean;
		params.minAcceptableSimilarity = 1;
		params.xmlDataFilename = null;

		setBounds(new Rectangle(0, 0, 640, 570));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel featureExtractPanel = new JPanel();
		featureExtractPanel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		featureExtractPanel.setBounds(12, 12, 227, 521);
		getContentPane().add(featureExtractPanel);
		featureExtractPanel.setLayout(null);
		
		JLabel labelFeatureExtract = new JLabel("Feature extraction:");
		labelFeatureExtract.setFont(new Font("Dialog", Font.BOLD, 14));
		labelFeatureExtract.setBounds(12, 12, 165, 15);
		featureExtractPanel.add(labelFeatureExtract);
		
		JCheckBox chckbxStemming = new JCheckBox("stemming");
		chckbxStemming.setBounds(22, 35, 180, 23);
		featureExtractPanel.add(chckbxStemming);
		
		JCheckBox chckbxStopList = new JCheckBox("word stoplist");
		chckbxStopList.setBounds(22, 89, 180, 23);
		params.stopList = null;
		featureExtractPanel.add(chckbxStopList);
		
		JScrollPane featureExtractscrollPane = new JScrollPane();
		featureExtractscrollPane.setBounds(22, 116, 180, 393);
		featureExtractPanel.add(featureExtractscrollPane);
		
		JTextArea textAreaStopList = new JTextArea();
		featureExtractscrollPane.setViewportView(textAreaStopList);
		textAreaStopList.setText("the");
		textAreaStopList.setEnabled(false);
		
		JCheckBox chckBoxremoveNumbers = new JCheckBox("remove numbers");
		chckBoxremoveNumbers.setBounds(22, 62, 180, 23);
		params.numbersCleaning = chckBoxremoveNumbers.isSelected();
		featureExtractPanel.add(chckBoxremoveNumbers);
		
		JPanel similarityPanel = new JPanel();
		similarityPanel.setLayout(null);
		similarityPanel.setBackground(UIManager.getColor("Button.select"));
		similarityPanel.setBounds(251, 12, 370, 132);
		getContentPane().add(similarityPanel);
		
		JLabel labelSimilarity = new JLabel("Similarity measure:");
		labelSimilarity.setFont(new Font("Dialog", Font.BOLD, 14));
		labelSimilarity.setBounds(12, 12, 186, 15);
		similarityPanel.add(labelSimilarity);
		
		JRadioButton rdbtnBinarySim = new JRadioButton("binary");
		rdbtnBinarySim.setSelected(true);
		buttonGroupSimilarity.add(rdbtnBinarySim);
		rdbtnBinarySim.setBounds(22, 35, 136, 23);
		similarityPanel.add(rdbtnBinarySim);
		
		JRadioButton rdbtnLevenshteinSim = new JRadioButton("Levenshtein's");
		buttonGroupSimilarity.add(rdbtnLevenshteinSim);
		rdbtnLevenshteinSim.setBounds(22, 62, 136, 23);
		similarityPanel.add(rdbtnLevenshteinSim);
		
		JRadioButton rdbtnNgramw = new JRadioButton("N-grams");
		buttonGroupSimilarity.add(rdbtnNgramw);
		rdbtnNgramw.setBounds(22, 89, 136, 23);
		similarityPanel.add(rdbtnNgramw);
		
		JLabel lblMax = new JLabel("min:");
		lblMax.setBounds(166, 66, 41, 15);
		similarityPanel.add(lblMax);
		
		JSpinner LevenshteinSpinner = new JSpinner();
		LevenshteinSpinner.setModel(new SpinnerNumberModel(1.0, 0.0, 1.0, 0.01));
		LevenshteinSpinner.setBounds(202, 64, 75, 20);
		similarityPanel.add(LevenshteinSpinner);
		
		JLabel lblMin = new JLabel("min:");
		lblMin.setBounds(166, 94, 41, 15);
		similarityPanel.add(lblMin);
		
		JSpinner nGramMinSpinner = new JSpinner();
		nGramMinSpinner.setModel(new SpinnerNumberModel(1.0, 0.0, 1.0, 0.01));
		nGramMinSpinner.setBounds(202, 92, 75, 20);
		similarityPanel.add(nGramMinSpinner);
		
		JLabel lblNGram = new JLabel("n:");
		lblNGram.setBounds(289, 94, 14, 15);
		similarityPanel.add(lblNGram);
		
		JSpinner nGramNSpinner = new JSpinner();
		nGramNSpinner.setModel(new SpinnerNumberModel(3, 0, 8, 1));
		nGramNSpinner.setBounds(305, 92, 41, 20);
		params.N = (int)nGramNSpinner.getValue();
		similarityPanel.add(nGramNSpinner);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(UIManager.getColor("Button.select"));
		panel.setBounds(251, 156, 227, 132);
		getContentPane().add(panel);
		
		JLabel labelMetric = new JLabel("Metric:");
		labelMetric.setFont(new Font("Dialog", Font.BOLD, 14));
		labelMetric.setBounds(12, 12, 186, 15);
		panel.add(labelMetric);
		
		JRadioButton rdbtnEuclidean = new JRadioButton("Euclidean");
		rdbtnEuclidean.setSelected(true);
		buttonGroupMetric.add(rdbtnEuclidean);
		rdbtnEuclidean.setBounds(22, 35, 137, 23);
		panel.add(rdbtnEuclidean);
		
		JRadioButton rdbtnChebyshev = new JRadioButton("Chebyshev's");
		buttonGroupMetric.add(rdbtnChebyshev);
		rdbtnChebyshev.setBounds(22, 62, 137, 23);
		panel.add(rdbtnChebyshev);
		
		JRadioButton rdbtnTaxi = new JRadioButton("Taxi");
		buttonGroupMetric.add(rdbtnTaxi);
		rdbtnTaxi.setBounds(22, 89, 137, 23);
		panel.add(rdbtnTaxi);
		
		JPanel dataGatherPanel = new JPanel();
		dataGatherPanel.setLayout(null);
		dataGatherPanel.setBackground(UIManager.getColor("Button.select"));
		dataGatherPanel.setBounds(251, 300, 227, 233);
		getContentPane().add(dataGatherPanel);
		
		JLabel getDataLbl = new JLabel("Gather data:");
		getDataLbl.setFont(new Font("Dialog", Font.BOLD, 14));
		getDataLbl.setBounds(64, 12, 113, 15);
		dataGatherPanel.add(getDataLbl);
		
		JButton getReutersXmlBtn = new JButton("Choose XML file");
		getReutersXmlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getReutersXmlBtn.setBounds(29, 70, 165, 25);
		dataGatherPanel.add(getReutersXmlBtn);
		
		JLabel reutersLbl = new JLabel("Reuters:");
		reutersLbl.setBounds(79, 55, 61, 15);
		dataGatherPanel.add(reutersLbl);
		
		JLabel lblOwnData = new JLabel("Own data:");
		lblOwnData.setBounds(64, 114, 113, 15);
		dataGatherPanel.add(lblOwnData);
		
		JButton myOwnDataBtn = new JButton("Choose XML file");
		myOwnDataBtn.setBounds(29, 129, 165, 25);
		dataGatherPanel.add(myOwnDataBtn);
		
		JSlider trainingPctSlider = new JSlider();
		trainingPctSlider.setBounds(12, 205, 200, 16);
		trainingPctSlider.setValue(60);
		trainingPctSlider.setMaximum(99);
		trainingPctSlider.setMinimum(1);
		params.trainingSetPctSize = trainingPctSlider.getValue();
		dataGatherPanel.add(trainingPctSlider);
		
		JLabel lblTrainingData = new JLabel("Training data");
		lblTrainingData.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblTrainingData.setBounds(12, 189, 73, 15);
		dataGatherPanel.add(lblTrainingData);
		
		JLabel lblTrainingSetSize = new JLabel(String.valueOf(trainingPctSlider.getValue()) + " %");
		lblTrainingSetSize.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTrainingSetSize.setBounds(166, 188, 46, 15);
		dataGatherPanel.add(lblTrainingSetSize);
		
		JPanel experimentPanel = new JPanel();
		experimentPanel.setLayout(null);
		experimentPanel.setBackground(UIManager.getColor("Button.select"));
		experimentPanel.setBounds(490, 408, 131, 125);
		getContentPane().add(experimentPanel);
		
		JLabel runExperimentLbl = new JLabel("Experiment:");
		runExperimentLbl.setFont(new Font("Dialog", Font.BOLD, 14));
		runExperimentLbl.setBounds(12, 12, 107, 15);
		experimentPanel.add(runExperimentLbl);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(24, 39, 76, 25);
		experimentPanel.add(btnRun);
		
		JLabel lblRunning = new JLabel("Running...");
		lblRunning.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblRunning.setBounds(30, 76, 101, 25);
		experimentPanel.add(lblRunning);
		
		JLabel lblDone = new JLabel("Done!");
		lblDone.setForeground(new Color(0, 100, 0));
		lblDone.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lblDone.setBounds(30, 101, 70, 15);
		experimentPanel.add(lblDone);
		
		JPanel etiqPanel = new JPanel();
		etiqPanel.setBackground(UIManager.getColor("Button.select"));
		etiqPanel.setBounds(490, 300, 131, 99);
		getContentPane().add(etiqPanel);
		etiqPanel.setLayout(null);
		
		JLabel etiquetteLbl = new JLabel("Etiquette:");
		etiquetteLbl.setBounds(27, 12, 77, 17);
		etiquetteLbl.setFont(new Font("Dialog", Font.BOLD, 14));
		etiqPanel.add(etiquetteLbl);
		
		JRadioButton rdbtnEtiq1 = new JRadioButton("1");
		etiqButtonGroup.add(rdbtnEtiq1);
		rdbtnEtiq1.setBounds(22, 37, 82, 23);
		rdbtnEtiq1.setSelected(true);
		etiqPanel.add(rdbtnEtiq1);
		
		JRadioButton rdbtnEtiq2 = new JRadioButton("2");
		etiqButtonGroup.add(rdbtnEtiq2);
		rdbtnEtiq2.setBounds(22, 64, 82, 23);
		etiqPanel.add(rdbtnEtiq2);
		
		JPanel neighboursPanel = new JPanel();
		neighboursPanel.setLayout(null);
		neighboursPanel.setBackground(UIManager.getColor("Button.select"));
		neighboursPanel.setBounds(490, 156, 131, 132);
		getContentPane().add(neighboursPanel);
		
		JLabel neighboursLbl = new JLabel("Neighbours:");
		neighboursLbl.setFont(new Font("Dialog", Font.BOLD, 14));
		neighboursLbl.setBounds(12, 12, 107, 17);
		neighboursPanel.add(neighboursLbl);
		
		JSpinner neighboursSpinner = new JSpinner();
		neighboursSpinner.setBounds(48, 66, 51, 20);
		neighboursSpinner.setModel(new SpinnerNumberModel(3, 1, 100, 1));
		params.kNeighbours = (int)neighboursSpinner.getValue();
		neighboursPanel.add(neighboursSpinner);
		
		JLabel lblK = new JLabel("k:");
		lblK.setBounds(27, 68, 14, 15);
		neighboursPanel.add(lblK);
		lblRunning.setVisible(false);
		lblDone.setVisible(false);


		// action listeners
		trainingPctSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int sliderVal = trainingPctSlider.getValue();
				lblTrainingSetSize.setText(String.valueOf(sliderVal) + " %");
			}
		});

		chckbxStemming.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxStemming.isSelected())
					params.stemming = StemMethod.porter_stemmer;
				else
					params.stemming = StemMethod.no_stemmer;
			}
		});

		chckBoxremoveNumbers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				params.numbersCleaning = chckBoxremoveNumbers.isSelected();
			}
		});

		chckbxStopList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaStopList.setEnabled(chckbxStopList.isSelected());

			}
		});

		getReutersXmlBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnEtiq1.setText("PLACES");
				rdbtnEtiq2.setText("TOPICS");
				JFileChooser jfc = new JFileChooser();
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					params.xmlDataFilename = selectedFile.getAbsolutePath();
				}
			}
		});

		myOwnDataBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnEtiq1.setText("My 1");
				rdbtnEtiq2.setText("My 2");
				JFileChooser jfc = new JFileChooser();
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					params.xmlDataFilename = selectedFile.getAbsolutePath();
				}
			}
		});

		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblDone.setVisible(false);
				lblRunning.setVisible(true);
				repaint();
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						params.numbersCleaning = chckBoxremoveNumbers.isSelected();
						if(chckbxStemming.isSelected())
							params.stemming = StemMethod.porter_stemmer;
						else
							params.stemming = StemMethod.no_stemmer;
						if(chckbxStopList.isSelected()) {
							params.stopList = new HashSet<>();
							String[] words = textAreaStopList.getText().split("[^a-zA-Z]+");
							for(int i = 0; i < words.length; ++i)
								params.stopList.add(words[i]);
						}
						else
							params.stopList = null;

						if(rdbtnEtiq1.getText().equals("REUTERS")) {
							if(rdbtnEtiq1.isSelected())
								params.etiquette = EtiquetteType.reuters_places;
							else
								params.etiquette = EtiquetteType.reuters_topics;
						}
						params.kNeighbours = (int)neighboursSpinner.getValue();
						if(rdbtnLevenshteinSim.isSelected()) {
							params.similarity = SimilarityMethod.levenshtein;
							params.minAcceptableSimilarity = (double)LevenshteinSpinner.getValue();
						}
						else if(rdbtnNgramw.isSelected()) {
							params.similarity = SimilarityMethod.ngram;
							params.N = (int)nGramNSpinner.getValue();
							params.minAcceptableSimilarity = (double)nGramMinSpinner.getValue();
						}
						else
							params.similarity = SimilarityMethod.binary;

						if(rdbtnEuclidean.isSelected())
							params.metric = DistanceMethod.euclidean;
						else if(rdbtnChebyshev.isSelected())
							params.metric = DistanceMethod.chebyshev;
						else
							params.metric = DistanceMethod.taxi;

						params.trainingSetPctSize = trainingPctSlider.getValue();
						params.kNeighbours = (int)neighboursSpinner.getValue();

						if(params.xmlDataFilename == null)
							JOptionPane.showMessageDialog(null, "Choose XML file!");

						try {
							new Controller(params).runExperiment();
						} catch (JAXBException e) {
							JOptionPane.showMessageDialog(null, "Problem encountered when parsing XML!");
							e.printStackTrace();
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Problem opening the file!");
							e.printStackTrace();
						}
						
						lblRunning.setVisible(false);
						lblDone.setVisible(true);
					}
				});
			}
		});
	}

	private ExperimentParameters params;
	private final ButtonGroup etiqButtonGroup = new ButtonGroup();
}
