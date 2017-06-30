import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

		//table.setEnabled(false);
		
		//create scoll pane
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(2);
		table.setAutoCreateRowSorter(true);
		//create window
		createWindow();
		
	}
	

	public void createWindow(){
		JFrame window = new JFrame("Search Results");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.add(scrollPane);
		window.setSize(800,500);
		window.setBounds(500, 400, 500, 500);
		//window.setAlwaysOnTop(true);
		window.setVisible(true);
		
	}
	

	}

