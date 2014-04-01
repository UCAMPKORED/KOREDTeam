package viewSwing;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.dao.AnimalDAO;
import model.domain.Animal;

import javax.swing.JTextPane;

import controller.AnimalController;
import view.AnimalDetailView;
import exception.AnoNotExistException;

import java.awt.Button;

public class AnimaInfoView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private String choiceName;
	DefaultTableModel title;
	private Object[][] tableData;
	static int countnum = 0;
	JTextPane textPane;
	String aname;
	String ano;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimaInfoView frame = new AnimaInfoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AnimaInfoView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 50, 600, 600);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("입 력");
		lblNewLabel.setBounds(193, 16, 65, 37);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		Choice choice = new Choice();
		choice.setFont(new Font("굴림", Font.BOLD, 16));
		choice.setBounds(10, 20, 162, 26);
		choice.add("choice");
		choice.add("animal all list");
		choice.add("name");
		choice.addItemListener(new itemStateCheck());
		contentPane.add(choice);
		
		textField = new JTextField();
		textField.setBounds(261, 17, 214, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 16));
		btnNewButton.setBounds(487, 18, 85, 31);
		btnNewButton.addActionListener(new ActionButton());
		contentPane.add(btnNewButton);
		
		title = new DefaultTableModel();	
		JTable table = new JTable(title);
		JScrollPane scroll = new JScrollPane(table);
		title.addColumn("DB내용");
		table.setFont(new Font("굴림", Font.BOLD, 16));
		table.addMouseListener(new MyMouseListener()); 
		scroll.setSize(562, 399);
		scroll.setLocation(10, 63);
		table.setSize(600, 400);
		setVisible(true);
		contentPane.add(scroll); 
		
		textPane = new JTextPane();
		textPane.setBounds(10, 515, 562, 37);
		contentPane.add(textPane);
		
		JButton button = new JButton("상세정보");
		button.setFont(new Font("굴림", Font.BOLD, 12));
		button.setBounds(10, 472, 127, 41);
		button.addActionListener(new ActionButtoninfo());
		contentPane.add(button);
		
		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.setBounds(445, 472, 127, 41);
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionButtonExit());
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("삭제");
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 12));
		btnNewButton_2.setBounds(159, 472, 127, 41);
		btnNewButton_2.addActionListener(new ActionButtonD());
		contentPane.add(btnNewButton_2);
	 
		
		
	}

	class itemStateCheck implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			
			String check1 =(String) e.getItem();
			if(check1.equals("choice")){
				choiceName="choice";
				textPane.setText("choice");
			}
			else if(check1.equals("animal all list")){
				choiceName = "animal all list";
				
			}else if(check1.equals("name")){
				choiceName = "name";
				
			}	
		}	
	}
	
	class  ActionButton implements ActionListener  {
		public void actionPerformed(ActionEvent a) {
				showAnimal();		
		}
	}
	
	public void showAnimal(){
		//System.out.println("title1:"+title.getColumnCount());
		textPane.setText("");
		if(countnum != 0){	
			for(int i=countnum-1; i>=0; i--){			
				title.removeRow(i);
			}
			countnum=0;		
		}

		//System.out.println(textField.getText());
		ArrayList<Animal> animalAll;
		Iterator<Animal> ie;
		if(choiceName == "animal all list"){
			try {
				 animalAll = AnimalDAO.getAnimalAll();
				 ie = animalAll.iterator(); 

					for(int index=0; index<animalAll.size();index++){
								
						Vector temp = new Vector();
						temp.add(animalAll.get(index));
						System.out.println(temp);
						title.addRow(temp);
						countnum++;					
					}
	
				} catch (SQLException e) {
					e.printStackTrace();
				}textPane.setText("모든 정보 검색 완료");
			}else if(choiceName == "name"){
				try {
					Animal animalName= AnimalDAO.searchByaName(textField.getText());
					Vector temp = new Vector();
					temp.add(animalName);
					title.addRow(temp.toArray());
					countnum++;
		
					} catch (SQLException | AnoNotExistException e) {
						e.printStackTrace();
					}
				}else{
					textPane.setText("검색할 조건을 선택하세요~!");
				}
				contentPane.repaint();	
	}
	
	class  ActionButtoninfo implements ActionListener  {
		public void actionPerformed(ActionEvent a) {
			if(aname != null){
				try {
					Animal animal = AnimalDAO.searchByaName(aname);
					System.out.println(aname);
					if(animal != null){
						AnimalDetailView At = new AnimalDetailView(animal);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (AnoNotExistException e) {
					e.printStackTrace();
				}
			}else{
				textPane.setText("선택하세요");
			}
		}
	}
	
	 private class MyMouseListener extends MouseAdapter{
		  public void mouseClicked(MouseEvent e){
		   if(e.getButton() == 1){
		    JTable table = (JTable)e.getSource();
		    int row = table.getSelectedRow();
		    if(row != -1 ){
		    	String temp = table.getValueAt(row, 0).toString();
		    	aname =temp.substring(temp.lastIndexOf("aName=")+6, temp.lastIndexOf(", aType"));
		    	//System.out.println(aname);
		    	ano = temp.substring(temp.lastIndexOf("aNo=")+4, temp.lastIndexOf(", aName"));
		    	//System.out.println(ano);
		    }
		  }   
		}
	 }
	 class  ActionButtonExit implements ActionListener  {
			public void actionPerformed(ActionEvent a) {
				dispose();
			}
		}
	 
	 class  ActionButtonD implements ActionListener  {
			public void actionPerformed(ActionEvent a) {
				if(ano != null){
					System.out.println("delete");
					AnimalController.delete(Integer.parseInt(ano));
					showAnimal();
				}else{
					textPane.setText("삭제할 동식물을 선택하세요");
				}
				
			}
		}
}
