import java.awt.Button;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {
	
	
	GridLayout masterGrid = new GridLayout(3,11);
	JFrame window;
	int rows = 11;
	int columns = 2;
	int rgap = 2;
	int hgap = 2;
	
	JPanel panel;
	
	
	public MainMenu(){
		
		//setup the main container //gtg
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600, 400);
		
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(rows,columns,hgap,rgap));
		//panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		window.add(panel);
		
		//add components to container panel
		addComponents();
		window.setVisible(true);
	}
	
	public void addComponents(){
		
		//first column
		panel.add(new Label("Criteria"));
		panel.add(new Label("Max Price"));
		panel.add(new Label("Min Price"));
		panel.add(new Label("Make or model"));
		panel.add(new Label("Max displacement"));
		panel.add(new Label("Min displacement"));
		panel.add(new Label("Max year"));
		panel.add(new Label("Min year"));
		panel.add(new Label("Max miles"));
		panel.add(new Label("Min miles"));
		//panel.add(new Button("Search and display"));
		
		//second collumn
		panel.add(new Label("Data"));
		//panel.add(new Label("Data"));
		
		//
		
		
		
	
		
	}
}
