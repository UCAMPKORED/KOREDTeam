package viewSwing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.AnimalController;
import model.domain.Animal;
import util.SoundPlayer;
import view.AnimalDetailView;

public class AnimalStartMainView extends JFrame {

	private JPanel contentPane;
	ImageIcon image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		AnimalController.deleteAll();
		AnimalController.insert(new Animal(1, "꾸구리", "어류", "2급", "물의 흐름이 빠르고 자갈이 깔린 하천의 상류에 서식하며 수서곤충을 섭식한다."));
		AnimalController.insert(new Animal(2, "날개하늘나리", "육상식물", "2급", "강원도 오대산, 설악산, 태백산, 대암산 등 강원도 이북 지방에 주로 분포한다"));
		AnimalController.insert(new Animal(3, "닻무늬앞잡이", "곤충류", "2급", "몸길이 12-15mm이고, 가슴의 등면은 갈색과 녹색을 띠고 있다.)"));
		AnimalController.insert(new Animal(4, "모래주사", "어류", "2급", "체장은 10-12cm이며, 체형은 가늘고 길며 옆으로 약간 납작하다."));
		AnimalController.insert(new Animal(5, "밤수지맨드라미", "무척추동물", "2급", "우산형 군체. 군체는 약간 납작하고 작다"));
		AnimalController.insert(new Animal(6, "암매", "육상식물", "1급", "상록성 관목으로 높이 5cm 정도이고, 방석처럼 모여 자란다. "));
		AnimalController.insert(new Animal(7, "여울마자", "어류", "1급", "서식지 현황: 하천 중·하류의 모래와 자갈이 섞인 여울부의 아래쪽 유속이 빠른 곳에서 산다. "));
		AnimalController.insert(new Animal(8, "꼬치동자개", "어류", "1급", "물이 맑고 자갈과 큰돌이 있는 하천의 상류에 서식한다."));
		AnimalController.insert(new Animal(9, "느시", "조류", "2급", "광활한 평야, 건조한 구릉의 초지, 초원 등지에 서식한다"));
		AnimalController.insert(new Animal(10, "광릉요강꽃", "육상식물", "1급", "해발 300-1,100m 산지 사면의 햇빛이 들며 배수가 양호한 음지에서 자란다."));
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimalStartMainView frame = new AnimalStartMainView();
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
	public AnimalStartMainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 50, 771, 564);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		image = new ImageIcon("image/main.jpg");
		//this.setIconImage(image.getImage());
		//contentPane.add()
		JButton button = new JButton(image);
		button.setSize(734, 466);
		button.setLocation(12, 50);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setBounds(12, 10, 170, 30);
		btnNewButton.addActionListener(new ActionButtonS());
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("멸종동물 입력");
		btnNewButton_1.setBounds(196, 10, 170, 30);
		btnNewButton_1.addActionListener(new ActionButtonI());
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("종료");
		btnNewButton_3.setBounds(378, 10, 170, 30);
		btnNewButton_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
				
			}
		});
		contentPane.add(btnNewButton_3);
		
		SoundPlayer sp = new SoundPlayer();
		sp.setLocation(643, 10);
		contentPane.add(sp);
		
		
		
	}
	
	class  ActionButtonS implements ActionListener  {
		public void actionPerformed(ActionEvent a) {
			AnimaInfoView h = new AnimaInfoView();
		}
	}
	
	class  ActionButtonI implements ActionListener  {
		public void actionPerformed(ActionEvent a) {
			AnimalDetailView newAt = new AnimalDetailView(new Animal());
		}
	}
	
	
}
