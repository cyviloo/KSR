package gui;

import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroupSimilarity = new ButtonGroup();
	private final ButtonGroup buttonGroupMetric = new ButtonGroup();
	public MainWindow() {
		setBounds(new Rectangle(0, 0, 640, 570));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel featureExtractPanel = new JPanel();
		featureExtractPanel.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		featureExtractPanel.setBounds(12, 12, 227, 276);
		getContentPane().add(featureExtractPanel);
		featureExtractPanel.setLayout(null);
		
		JLabel labelFeatureExtract = new JLabel("Feature extraction:");
		labelFeatureExtract.setFont(new Font("Dialog", Font.BOLD, 14));
		labelFeatureExtract.setBounds(12, 12, 165, 15);
		featureExtractPanel.add(labelFeatureExtract);
		
		JCheckBox chckbxStemizacja = new JCheckBox("stemming");
		chckbxStemizacja.setBounds(22, 35, 180, 23);
		featureExtractPanel.add(chckbxStemizacja);
		
		JCheckBox chckbxStopList = new JCheckBox("word stoplist");
		chckbxStopList.setBounds(22, 89, 180, 23);
		featureExtractPanel.add(chckbxStopList);
		
		JScrollPane featureExtractscrollPane = new JScrollPane();
		featureExtractscrollPane.setBounds(22, 116, 180, 148);
		featureExtractPanel.add(featureExtractscrollPane);
		
		JTextArea textAreaCzarnaListaSw = new JTextArea();
		featureExtractscrollPane.setViewportView(textAreaCzarnaListaSw);
		textAreaCzarnaListaSw.setText("the");
		
		JCheckBox removeNumbers = new JCheckBox("remove numbers");
		removeNumbers.setBounds(22, 62, 180, 23);
		featureExtractPanel.add(removeNumbers);
		
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
		similarityPanel.add(nGramNSpinner);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(UIManager.getColor("Button.select"));
		panel.setBounds(251, 156, 370, 132);
		getContentPane().add(panel);
		
		JLabel labelMetric = new JLabel("Metric:");
		labelMetric.setFont(new Font("Dialog", Font.BOLD, 14));
		labelMetric.setBounds(12, 12, 186, 15);
		panel.add(labelMetric);
		
		JRadioButton rdbtnEuclidean = new JRadioButton("Euclidean");
		rdbtnEuclidean.setSelected(true);
		buttonGroupMetric.add(rdbtnEuclidean);
		rdbtnEuclidean.setBounds(22, 35, 213, 23);
		panel.add(rdbtnEuclidean);
		
		JRadioButton rdbtnChebyshev = new JRadioButton("Chebyshev's");
		buttonGroupMetric.add(rdbtnChebyshev);
		rdbtnChebyshev.setBounds(22, 62, 213, 23);
		panel.add(rdbtnChebyshev);
		
		JRadioButton rdbtnTaxi = new JRadioButton("Taxi");
		buttonGroupMetric.add(rdbtnTaxi);
		rdbtnTaxi.setBounds(22, 89, 213, 23);
		panel.add(rdbtnTaxi);
		
		JPanel dataGatherPanel = new JPanel();
		dataGatherPanel.setLayout(null);
		dataGatherPanel.setBackground(UIManager.getColor("Button.select"));
		dataGatherPanel.setBounds(12, 300, 227, 233);
		getContentPane().add(dataGatherPanel);
		
		JLabel getDataLbl = new JLabel("Gather data:");
		getDataLbl.setFont(new Font("Dialog", Font.BOLD, 14));
		getDataLbl.setBounds(12, 12, 165, 15);
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
		
		JSlider slider = new JSlider();
		slider.setBounds(12, 205, 200, 16);
		slider.setValue(60);
		dataGatherPanel.add(slider);
		
		JLabel lblTrainingData = new JLabel("Training data");
		lblTrainingData.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblTrainingData.setBounds(12, 189, 73, 15);
		dataGatherPanel.add(lblTrainingData);
		
		JLabel lblTrainingSetSize = new JLabel(String.valueOf(slider.getValue()) + " %");
		lblTrainingSetSize.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTrainingSetSize.setBounds(166, 188, 46, 15);
		dataGatherPanel.add(lblTrainingSetSize);

		// action listeners
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				lblTrainingSetSize.setText(String.valueOf(slider.getValue()) + " %");
			}
		});
	}
}
