package gui;

import javax.swing.JFrame;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
		
		JLabel labelFeatureExtract = new JLabel("Ekstrakcja cech:");
		labelFeatureExtract.setFont(new Font("Dialog", Font.BOLD, 14));
		labelFeatureExtract.setBounds(12, 12, 165, 15);
		featureExtractPanel.add(labelFeatureExtract);
		
		JCheckBox chckbxStemizacja = new JCheckBox("stemizacja");
		chckbxStemizacja.setBounds(22, 35, 180, 23);
		featureExtractPanel.add(chckbxStemizacja);
		
		JCheckBox chckbxCzarnaListaSw = new JCheckBox("czarna lista słów");
		chckbxCzarnaListaSw.setBounds(22, 89, 180, 23);
		featureExtractPanel.add(chckbxCzarnaListaSw);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 116, 180, 148);
		featureExtractPanel.add(scrollPane);
		
		JTextArea textAreaCzarnaListaSw = new JTextArea();
		scrollPane.setViewportView(textAreaCzarnaListaSw);
		textAreaCzarnaListaSw.setText("the");
		
		JCheckBox removeNumbers = new JCheckBox("usunięcie liczb");
		removeNumbers.setBounds(22, 62, 180, 23);
		featureExtractPanel.add(removeNumbers);
		
		JPanel similarityPanel = new JPanel();
		similarityPanel.setLayout(null);
		similarityPanel.setBackground(UIManager.getColor("Button.select"));
		similarityPanel.setBounds(251, 12, 370, 132);
		getContentPane().add(similarityPanel);
		
		JLabel labelSimilarity = new JLabel("Miara podobieństwa:");
		labelSimilarity.setFont(new Font("Dialog", Font.BOLD, 14));
		labelSimilarity.setBounds(12, 12, 186, 15);
		similarityPanel.add(labelSimilarity);
		
		JRadioButton rdbtnBinarna = new JRadioButton("binarna");
		rdbtnBinarna.setSelected(true);
		buttonGroupSimilarity.add(rdbtnBinarna);
		rdbtnBinarna.setBounds(22, 35, 136, 23);
		similarityPanel.add(rdbtnBinarna);
		
		JRadioButton rdbtnOdlegoLoewensteina = new JRadioButton("Loewensteina");
		buttonGroupSimilarity.add(rdbtnOdlegoLoewensteina);
		rdbtnOdlegoLoewensteina.setBounds(22, 62, 136, 23);
		similarityPanel.add(rdbtnOdlegoLoewensteina);
		
		JRadioButton rdbtnNgramw = new JRadioButton("n-gramów");
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
		
		JLabel labelMetric = new JLabel("Metryka:");
		labelMetric.setFont(new Font("Dialog", Font.BOLD, 14));
		labelMetric.setBounds(12, 12, 186, 15);
		panel.add(labelMetric);
		
		JRadioButton rdbtnEuklidesowa = new JRadioButton("euklidesowa");
		rdbtnEuklidesowa.setSelected(true);
		buttonGroupMetric.add(rdbtnEuklidesowa);
		rdbtnEuklidesowa.setBounds(22, 35, 213, 23);
		panel.add(rdbtnEuklidesowa);
		
		JRadioButton rdbtnCzebyszewa = new JRadioButton("Czebyszewa");
		buttonGroupMetric.add(rdbtnCzebyszewa);
		rdbtnCzebyszewa.setBounds(22, 62, 213, 23);
		panel.add(rdbtnCzebyszewa);
		
		JRadioButton rdbtnTakswkowa = new JRadioButton("taksówkowa");
		buttonGroupMetric.add(rdbtnTakswkowa);
		rdbtnTakswkowa.setBounds(22, 89, 213, 23);
		panel.add(rdbtnTakswkowa);
	}
}
