package com.texteditorproject;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings("unused")
public class BasicTextEditor {

	private JFrame frmGerkEditor;
	JMenuItem nmFOFF = new JMenuItem("Normal Italic");
	JMenuItem nmFON = new JMenuItem("Arial Bold");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicTextEditor window = new BasicTextEditor();
					window.frmGerkEditor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BasicTextEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGerkEditor = new JFrame();
		frmGerkEditor.setTitle("Gerk Editor");
		//make center the form
		//frame.setLocationRelativeTo(null);
		frmGerkEditor.setLocationRelativeTo(null);
		frmGerkEditor.setBounds(100, 100, 450, 386);
		frmGerkEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGerkEditor.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 2);
		frmGerkEditor.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 0, 434, 348);
		frmGerkEditor.getContentPane().add(scrollPane_1);
		
		JTextArea txt = new JTextArea();
		txt.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txt.setForeground(Color.WHITE);
		txt.setBackground(SystemColor.textHighlight);
		scrollPane_1.setViewportView(txt);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(51, 0, 51));
		menuBar.setForeground(Color.WHITE);
		frmGerkEditor.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setForeground(Color.WHITE);
		menuBar.add(mnFile);
		
		//New
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				txt.setText("");
			}
		});
		mnFile.add(mntmNew);
		
		//Open
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser open = new JFileChooser();
				int choice = open.showOpenDialog(frmGerkEditor);
				
				if(choice == JFileChooser.APPROVE_OPTION) {
					
					try {
						@SuppressWarnings("resource")
						Scanner sc = new Scanner(new FileReader(open.getSelectedFile().getPath()));
						
						while(sc.hasNext()) {
							
							txt.append(sc.nextLine() + "\n");
						}
						
						
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser save = new JFileChooser();
				int choice = save.showSaveDialog(frmGerkEditor);
				
				if(choice == JFileChooser.APPROVE_OPTION) {
					
					try {
						BufferedWriter bfIn = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
						
						bfIn.write(txt.getText());
						bfIn.close();
						
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
					
				}
			}
		});
		mnFile.add(mntmSave);
		
		//Close
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txt.setText("");
			}
		});
		mnFile.add(mntmClose);
		
		//Exit
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//exit the editor
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnNewMenu = new JMenu("Format");
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		
		nmFON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txt.setFont(new Font("Arial", Font.BOLD, 20));
				
				
				//txt.setWrapStyleWord(true);
				txt.setLineWrap(true); 
				
			}
		});
		mnNewMenu.add(nmFON);
		nmFOFF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txt.setFont(new Font("Arial", Font.ITALIC, 16));
				txt.setLineWrap(true);
			}
		});
		
		
		mnNewMenu.add(nmFOFF);
	}
}
