import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	private Primes m_Primes;
	private JTextField tfPrimeFileName = new JTextField();
	private JTextField tfCrossFileName = new JTextField();
	private JLabel lblPrimeCount = new JLabel();
	private JLabel lblCrossCount = new JLabel();
	private JLabel lblLargestPrime = new JLabel();
	private JLabel lblLargestCross = new JLabel();
	private JLabel lblStatus = new JLabel();
	
	MainWindow(String name, Primes p)
	{
		//Main window properties
		m_Primes = p;
		JFrame mainWin = new JFrame(name);
		mainWin.setTitle(name);
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.setBounds(5, 5, 1000, 400);
		Color bgc = new Color(80,0,0);
		mainWin.getContentPane().setBackground(bgc);
		mainWin.setLayout(new GridBagLayout());
		GridBagConstraints gbcMain = new GridBagConstraints();
		gbcMain.fill = GridBagConstraints.HORIZONTAL;
		gbcMain.gridx = 0;
		gbcMain.gridy = 0;
		gbcMain.anchor = GridBagConstraints.WEST;
		gbcMain.insets = new Insets(1,2,0,0);
		gbcMain.ipady = 10;
		gbcMain.weightx = .5;

		
		// ============= Panel for Primes ===================
		//setting up Panel One
		JPanel panelOne = new JPanel();
		panelOne.setLayout(new GridBagLayout());
		GridBagConstraints gbcPanelOne = new GridBagConstraints();
		gbcPanelOne.gridx = 0;
		gbcPanelOne.gridy = 0;
		gbcPanelOne.insets = new Insets(1,2,0,0);
		gbcPanelOne.fill = GridBagConstraints.HORIZONTAL;
		gbcPanelOne.anchor = GridBagConstraints.NORTHWEST;
		gbcPanelOne.weightx = .5;

		gbcPanelOne.gridwidth = 2;
		//Text Field
		tfPrimeFileName = new JTextField(Config.PRIMEFILNAME);
		tfPrimeFileName.setColumns(50);
		panelOne.add(tfPrimeFileName,gbcPanelOne);// adds textField to panel
		//Label for Primes Count
		lblPrimeCount = new JLabel("N/A");
		lblPrimeCount.setLabelFor(tfPrimeFileName);
		gbcPanelOne.gridwidth = 1;
		gbcPanelOne.gridx = 2;
		gbcPanelOne.fill = GridBagConstraints.NONE;
		gbcPanelOne.anchor = GridBagConstraints.CENTER;
		panelOne.add(lblPrimeCount,gbcPanelOne); //add counter text to panel
		
		//The label for Primes text
		JLabel primesFile = new JLabel("Primes File");
		primesFile.setFont(new Font("Tahoma",Font.PLAIN,16));

		gbcPanelOne.fill = GridBagConstraints.HORIZONTAL;
		gbcPanelOne.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanelOne.gridy = 1;
		gbcPanelOne.gridx = 0;
		panelOne.add(primesFile,gbcPanelOne); //adds Text to panel
		//The button for load
		JButton loadPrimeButton = new JButton("Load");
		loadPrimeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileAccess.loadPrimes(m_Primes, tfPrimeFileName.getText());
					updateStats();
					lblStatus.setText("File loaded successfully");
					
				}
				catch(FileNotFoundException err) {
					lblStatus.setText("File name does not exist. Try again");
					mainWin.dispose();
				}
			}
		});
		gbcPanelOne.anchor = GridBagConstraints.EAST;
		gbcPanelOne.gridx = 1;
		gbcPanelOne.fill = GridBagConstraints.NONE;
		panelOne.add(loadPrimeButton,gbcPanelOne);//adds button to panel
		
		//Save button
		JButton savePrimeButton = new JButton("Save");
		savePrimeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileAccess.savePrimes(m_Primes, tfPrimeFileName.getText());
					updateStats();
					lblStatus.setText("Prime list saved successfully.");
					
				}
				catch(IOException err) {
					lblStatus.setText("Error, something is wrong");
					mainWin.dispose();
				}
			}
		});
		gbcPanelOne.gridx = 2;
		panelOne.add(savePrimeButton,gbcPanelOne);//adds save button to panel
		
		mainWin.add(panelOne,gbcMain);//adds panel to main window
		
		// ============== Hexagonal Cross Panel =======================
		// setting up panel Two
		JPanel panelTwo = new JPanel();
		panelTwo.setLayout(new GridBagLayout());
		//textfield
		tfCrossFileName = new JTextField(Config.CROSSFILENAME);
		tfCrossFileName.setColumns(50);
		GridBagConstraints gbcPanelTwo = new GridBagConstraints();
		gbcPanelTwo.fill = GridBagConstraints.HORIZONTAL;
		gbcPanelTwo.insets = new Insets(1,2,0,0);
		gbcPanelTwo.gridwidth = 2;
		gbcPanelTwo.anchor = GridBagConstraints.NORTHWEST;
		gbcPanelTwo.gridx = 0;
		gbcPanelTwo.gridy = 0;
		gbcPanelTwo.weightx = .5;
		panelTwo.add(tfCrossFileName,gbcPanelTwo);
		//Label for Primes Count
		lblCrossCount = new JLabel("N/A");
		lblCrossCount.setLabelFor(tfCrossFileName);
		gbcPanelTwo.gridx = 2;
		gbcPanelTwo.fill = GridBagConstraints.NONE;
		gbcPanelTwo.gridwidth = 1;
		gbcPanelTwo.anchor = GridBagConstraints.CENTER;
		panelTwo.add(lblCrossCount,gbcPanelTwo); //add counter text to panel
		//Label for Hexagon File Text
		JLabel crossFile = new JLabel("Hexagon Cross File");
		crossFile.setFont(new Font("Tahoma",Font.PLAIN,16));
		gbcPanelTwo.fill = GridBagConstraints.HORIZONTAL;
		gbcPanelTwo.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanelTwo.gridx = 0;
		gbcPanelTwo.gridy = 1;
		panelTwo.add(crossFile,gbcPanelTwo);//adds label to Panel Two
		//button for loading cross file
		JButton loadCrossButton = new JButton("Load");
		loadCrossButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileAccess.loadCrosses(m_Primes,tfCrossFileName.getText());
					updateStats();
					lblStatus.setText("File loaded successfully");
					
				}
				catch(FileNotFoundException err) {
					lblStatus.setText("File name does not exist. Try again");
					mainWin.dispose();
				}
			}
		});
		gbcPanelTwo.gridx = 1;
		gbcPanelTwo.anchor = GridBagConstraints.EAST;
		gbcPanelTwo.fill = GridBagConstraints.NONE;
		panelTwo.add(loadCrossButton,gbcPanelTwo);//adds button to panel
		
		//load button for cross file
		JButton saveCrossButton = new JButton("Save");
		saveCrossButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileAccess.saveCrosses(m_Primes,tfCrossFileName.getText());
					updateStats();
					lblStatus.setText("Hexagon Cross list saved successfully.");
					
				}
				catch(IOException err) {
					lblStatus.setText("File name does not exist. Try again");
					mainWin.dispose();
				}
			}
		});
		gbcPanelTwo.gridx = 2;
		panelTwo.add(saveCrossButton,gbcPanelTwo);//adds save button to panel two
		gbcMain.gridy = 1;
		mainWin.add(panelTwo,gbcMain);//adds panel two to main panel
		// ====================== Panel Three ================
		//panel three
		JPanel panelThree = new JPanel();
		panelThree.setLayout(new GridBagLayout());
		//generate primes button
		JButton genPButton = new JButton("Generate Primes");
		genPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popupGeneratePrimes();
				updateStats();
				lblStatus.setText("Generated primes sucessfully");
			}
		});
		GridBagConstraints gbcPanelThree = new GridBagConstraints();
		gbcPanelThree.gridheight = 2;
		gbcPanelThree.gridx = 0;
		gbcPanelThree.gridy = 0;
		gbcPanelThree.insets = new Insets(1,2,0,0);
		gbcPanelThree.fill = GridBagConstraints.HORIZONTAL;
		gbcPanelThree.anchor = GridBagConstraints.WEST;
		panelThree.add(genPButton,gbcPanelThree);//adds button to panel
		//labels for text
		lblLargestPrime = new JLabel("The largest prime has 0 digits");
		crossFile.setFont(new Font("Tahoma",Font.PLAIN,12));
		gbcPanelThree.gridx = 1;
		gbcPanelThree.gridheight = 1;
		panelThree.add(lblLargestPrime,gbcPanelThree);//adds label to Panel Three
		lblLargestCross = new JLabel("The largest cross has 0 and 0 digits");
		crossFile.setFont(new Font("Tahoma",Font.PLAIN,12));
		gbcPanelThree.gridy = 1;
		panelThree.add(lblLargestCross,gbcPanelThree);//adds label to Panel Three
		
		//button for generate crosses
		JButton genCButton = new JButton("Generate Crosses");
		genCButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_Primes.generateTwinPrimes();
				m_Primes.generateHexPrimes();
				updateStats();
				lblStatus.setText("Generate cross primes successfully");
			}
		});
		gbcPanelThree.gridx = 2;
		gbcPanelThree.gridy = 0;
		gbcPanelThree.gridheight = 2;
		panelThree.add(genCButton,gbcPanelThree);//adds button to panel
		gbcMain.gridy = 2;
		mainWin.add(panelThree,gbcMain);//adds panel three to main panel
		
		// ================ Panel Four ====================
		JPanel panelFour = new JPanel();
		panelFour.setLayout(new GridBagLayout());
		//Panel for the status
		lblStatus = new JLabel("Status: Bored.");
		lblStatus.setHorizontalAlignment(JLabel.LEFT);
		lblStatus.setFont(new Font("Tahome", Font.PLAIN, 12));
		GridBagConstraints gbcPanelFour = new GridBagConstraints();
		gbcPanelFour.anchor = GridBagConstraints.WEST;
		gbcPanelFour.gridx = 0;
		gbcPanelFour.gridy = 3;
		panelFour.add(lblStatus,gbcPanelFour);//adds label to panel three
		
		
		gbcMain.gridy = 3;
		mainWin.add(panelFour,gbcMain);//adds label to panel three

		/////
		mainWin.pack();
		mainWin.setVisible(true);
		
		
	}
	protected void popupGeneratePrimes()
	{
		
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(52, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		gbcPanel.insets = new Insets(1,2,0,0);

		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		m_Primes.generatePrimes(start, count);
       		lblStatus.setText("Status: Excited. Primes have been generated.");
       		updateStats();
      		dPrimes.dispose();
      	}
      	catch(NumberFormatException ex)
      	{
      		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
      		dPrimes.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(200,200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}

	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats()
	{
		this.lblCrossCount.setText(String.valueOf(this.m_Primes.crossesCount()));
		this.lblPrimeCount.setText(String.valueOf(this.m_Primes.primeCount()));
		this.lblLargestPrime.setText("The largest prime has " + this.m_Primes.sizeofLastCross() + " digits");
		this.lblLargestCross.setText("The largest cross has " + this.m_Primes.sizeofLastCross().left() + " and " + this.m_Primes.sizeofLastCross().right());
		
		
 	}

}
