package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;


public class GUI {

	private JFrame frame;
	private JTextField textFieldProc;
	private JTextField textFieldReadyQueue;
	private JTextField textFieldCPU;
	private JTextField textFieldIter;
	private int execution = 0;
	private JTable table;
	private JTextField textFieldIterDelay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1285, 573);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//ArrayList<Integer> cpuPoints = new ArrayList<>();
		
		JScrollPane scrollPaneOutput = new JScrollPane();
		scrollPaneOutput.setBounds(8, 252, 563, 234);
		frame.getContentPane().add(scrollPaneOutput);
		
		JTextArea textAreaOutput = new JTextArea();
		scrollPaneOutput.setViewportView(textAreaOutput);
		
		JLabel lblProcessManagmentSimulation = new JLabel("Process Managment Simulation Application");
		lblProcessManagmentSimulation.setBounds(0, 0, 434, 19);
		lblProcessManagmentSimulation.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblProcessManagmentSimulation.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblProcessManagmentSimulation);
		

		
		JLabel lblCpuQuantum = new JLabel("CPU Quantum");
		lblCpuQuantum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpuQuantum.setBounds(32, 99, 96, 14);
		frame.getContentPane().add(lblCpuQuantum);
		
		JLabel lblTimeLimits = new JLabel("Time Limit (s)");
		lblTimeLimits.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTimeLimits.setBounds(32, 49, 115, 14);
		frame.getContentPane().add(lblTimeLimits);
		
		JLabel label_1 = new JLabel("Ready Queue Size");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(32, 74, 115, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Max Iterations");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(32, 124, 96, 14);
		frame.getContentPane().add(label_2);
		
		textFieldProc = new JTextField();
		textFieldProc.setText("10");
		textFieldProc.setBounds(160, 47, 86, 20);
		frame.getContentPane().add(textFieldProc);
		textFieldProc.setColumns(10);
		
		textFieldReadyQueue = new JTextField();
		textFieldReadyQueue.setText("5");
		textFieldReadyQueue.setColumns(10);
		textFieldReadyQueue.setBounds(160, 72, 86, 20);
		frame.getContentPane().add(textFieldReadyQueue);
		
		textFieldCPU = new JTextField();
		textFieldCPU.setText("4");
		textFieldCPU.setColumns(10);
		textFieldCPU.setBounds(160, 97, 86, 20);
		frame.getContentPane().add(textFieldCPU);
		
		textFieldIter = new JTextField();
		textFieldIter.setText("5000");
		textFieldIter.setColumns(10);
		textFieldIter.setBounds(160, 122, 86, 20);
		frame.getContentPane().add(textFieldIter);
		
		textFieldIterDelay = new JTextField();
		textFieldIterDelay.setText("50");
		textFieldIterDelay.setColumns(10);
		textFieldIterDelay.setBounds(160, 146, 86, 20);
		frame.getContentPane().add(textFieldIterDelay);
		
		
		JPanel panelSummary = new JPanel();
		panelSummary.setBounds(284, 49, 304, 164);
		frame.getContentPane().add(panelSummary);
		
		JTextArea textAreaSummary = new JTextArea();
		panelSummary.add(textAreaSummary);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ProcessMgmtApp newApp = new ProcessMgmtApp();
				String[] args = {"-h"};
				String[] results = ProcessMgmtApp.mainRun(args, execution);
				textAreaSummary.setText(results[0]);
				
			}
		});
		btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnHelp.setBounds(32, 173, 76, 23);
		frame.getContentPane().add(btnHelp);
		

		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOutput.setBounds(229, 227, 46, 14);
		frame.getContentPane().add(lblOutput);
		
		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSummary.setBounds(434, 24, 68, 14);
		frame.getContentPane().add(lblSummary);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(598, 49, 628, 438);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		//TableModel dataModel = new TableModel();
		DefaultTableModel dataModel = new DefaultTableModel(0,7);
		
		// add header of the table
		String headers[] = new String[] { "Execution","Processes", "Quantum", "Queue",
		            "Avg Delay", "Throughput", "CPU Utilization" };
		dataModel.setColumnIdentifiers(headers);
		table.setModel(dataModel);
		

	
		
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String quantum = textFieldCPU.getText();
				String procs = textFieldProc.getText();
				String iter = textFieldIter.getText();
				String readyQueue = textFieldReadyQueue.getText();
				String delay = textFieldIterDelay.getText();
				
				//ProcessMgmtApp newApp = new ProcessMgmtApp();
				execution ++;
				String[] args = {"-p", procs,"-q", readyQueue, "-c",quantum,"-l",iter,"-d", delay};
				// Async thread
				new Thread(new Runnable() {
				    public void run() {
				    	System.setOut(new PrintStreamCapturer(textAreaOutput, System.out));
						//System.setErr(new PrintStreamCapturer(textAreaOutput, System.err, "[ERROR] "));
						String[] results = ProcessMgmtApp.mainRun(args,execution);
						//textAreaSummary.setText(results);
						if (results.length > 1) {
							dataModel.addRow(new Object[] {String.format("%d", execution), results[1], results[2],
									results[3], results[4], results[5], results[6]});
							textAreaSummary.setText(results[0]);
						}else {
							textAreaSummary.setText(results[0]);
						}
						//float cpu = Float.parseFloat(results[6]);
						//int cpu2 = Math.round(cpu);
						//cpuPoints.add(cpu2);

				    }
				}).start();
				
				
			}
			
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStart.setBounds(170, 173, 76, 23);
		frame.getContentPane().add(btnStart);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(466, 490, 102, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnClearText = new JButton("Clear Text");
		btnClearText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaOutput.setText("");
			}
		});
		btnClearText.setBounds(354, 490, 102, 23);
		frame.getContentPane().add(btnClearText);
		
		JLabel label_3 = new JLabel("Iteration Delay (ms)");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(32, 148, 115, 14);
		frame.getContentPane().add(label_3);
		
		JLabel lblResultsHistory = new JLabel("Results History");
		lblResultsHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultsHistory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblResultsHistory.setBounds(788, 24, 124, 14);
		frame.getContentPane().add(lblResultsHistory);
		
		JButton btnClearTable = new JButton("Clear Table");
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int count = dataModel.getRowCount();
				for(int i = 0; i < count; i++) {
					dataModel.removeRow(i);
				}
			}
		});
		btnClearTable.setBounds(1124, 498, 102, 23);
		frame.getContentPane().add(btnClearTable);
		
		
		/*
		
		JButton btnPlot = new JButton("Plot");
		btnPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(10, 542, 561, 376);
				scrollPane_1.add(scrollPane_1);
				scrollPane_1.setVisible(true);
				
				MyPlot plot = new MyPlot(cpuPoints);
				scrollPane_1.setViewportView(plot);
			}
		});
		
		btnPlot.setBounds(8, 489, 89, 23);
		frame.getContentPane().add(btnPlot);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(18, 525, 553, 427);
		frame.getContentPane().add(scrollPane_1);
		*/
		
		
		
		

		
		
		
		
	}
}
