import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.GridLayout;
//creates a window that displays the data foud in a grid



@SuppressWarnings("serial")
public class ResultsWindow extends JPanel {
	
	//data to be displayed
	private Object[][] data;
	Object[] inputColumnNames;
	
	JScrollPane scrollPane;
	
	
	public ResultsWindow(Object[][] inputData, String[] inputColumnNames){
		this.data = inputData;
		this.inputColumnNames =inputColumnNames;

		
		//make table
		DefaultTableModel tableModel = new DefaultTableModel(data, inputColumnNames);
		JTable table = new JTable(tableModel);
		table.setAutoCreateRowSorter(true);
		//create scoll pane
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		//create window
		createWindow();
		
	}
	

	public void createWindow(){
		JFrame window = new JFrame("Search Results");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.add(scrollPane);
		window.setSize(500,500);
		window.setBounds(500, 400, 500, 500);
		window.setAlwaysOnTop(true);
		window.show();
		
	}
	

	}

