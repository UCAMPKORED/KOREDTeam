package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.AnimalController;
import exception.AnoDoubleException;
import exception.AnoNotExistException;
import model.dao.AnimalDAO;
import model.domain.Animal;

@SuppressWarnings("serial")
public class AnimalDetailView extends JFrame {
	private JLabel lb_title = new JLabel();
	
	private JButton bt_edit = new JButton("EDIT");
	private JButton bt_back = new JButton("BACK");
	private DetailPanel dPanel;
	private Container con;
	
	private Animal animal;

	public AnimalDetailView(Animal newAnimal) {
		animal = newAnimal;
		init();
		this.setTitle("KORED과 함께하는 멸종위기동물을 알아봅시다!");
		
		setSize(600, 600);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - getHeight() / 2);

		setLocation(xpos, ypos);
		setResizable(false);
		setVisible(true);

	}

	public void init() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		con = this.getContentPane();
		con.setLayout(null);
		lb_title.setText("멸종동물 상세정보");
		lb_title.setBounds(50, 0, 300, 50);
		lb_title.setFont(new Font("Default", Font.BOLD, 20));

		con.add(lb_title);
		
		dPanel = new DetailPanel(animal);
		con.add(dPanel);
		
		bt_edit.setBounds(0, 500, 300, 80);
		bt_edit.setFont(new Font("Default", Font.BOLD, 20));
		bt_edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				Animal tempAni = null;
				if (button.getText().equals("EDIT")) {
					button.setText("SAVE");
					dPanel.setEdit(true);
				} else {
					button.setText("EDIT");
					dPanel.setEdit(false);
					try {
						tempAni = AnimalDAO.searchByaName(animal.getaName());
						if (tempAni == null) {
							AnimalDAO.insert(dPanel.getAnimal());
						} else {
							AnimalDAO.update(dPanel.getAnimal());
						}
						animal = dPanel.getAnimal();
					} catch (AnoDoubleException e1) {
						e1.printStackTrace();
					} catch (AnoNotExistException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
					
					AnimalController.getAnimalAll();

				}
			}
		});

		con.add(bt_edit);

		bt_back.setBounds(300, 500, 300, 80);
		bt_back.setFont(new Font("Default", Font.BOLD, 20));
		bt_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		con.add(bt_back);
	}

}
