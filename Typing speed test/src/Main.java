import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Main {

	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Typing Speed Tester");
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(200, 230, 255)); // Light Pink 
		
		JLabel inst = new JLabel();
		inst.setText("Instructions");
		inst.setBounds(180,15,200,20);
		inst.setFont(new Font("Times New Roman", Font.ITALIC+Font.BOLD, 20));
		frame.add(inst);
		
		JTextArea details = new JTextArea(
				"1. Read the instructions carefully before you begin the test.\n" +
			    "2. This is a basic test which will calculate words typed per minute.\n" +
			    "3. The test requires the user to simply type the paragraph given in \n" + 
			    "   the text box above the typing area.\n" +
			    "4. Select your preferred test duration from given options \n" +
			    "   (30 seconds or 60 seconds).\n" +
			    "5. Click on the blank text area. The timer will begin as soon as \n" +
			    "   you start typing. \n" + 
			    "6. Type the paragraph as fast as you can.\n" + 
			    "7. Your score will be displayed when the timer runs out."
		);
		details.setBounds(20,50,450,220);
		details.setFont(new Font("Times New Roman", Font.ITALIC, 16));	
		details.setEditable(false);
		frame.add(details);
		
		JButton b1 = new JButton();
		b1.setText("Go to Test");
		b1.setBounds(190,300,100,30);
		b1.setBackground(Color.CYAN); 
		frame.add(b1);
		
		b1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource()==b1)
				{
					frame.dispose();
					Test testpage = new Test();
				}		
			}
		});
		frame.setVisible(true);
	}
}
