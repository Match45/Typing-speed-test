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
		
		JLabel inst = new JLabel();
		inst.setText("Instructions");
		inst.setBounds(180,15,200,20);
		inst.setFont(new Font("Times New Roman", Font.ITALIC+Font.BOLD, 20));
		frame.add(inst);
		
		JTextArea details = new JTextArea(
				"1. Read the instructions carefully before you begin the test.\n" +
			    "2. This is a basic test which will calculate words typed per minute.\n" +
			    "3. The test machinism is simply to copy and type the paragraph\n" + 
			    "   given above the blank text box.\n" +
			    "4. The test will be set for 1 minute (60 seconds).\n" +
			    "5. Click the start button and a timer will begin.\n" + 
			    "6. Type the paragraph as fast as you can.\n" + 
			    "7. Click submit to get your wpm (words per minute)."
		);
		details.setBounds(20,50,450,170);
		details.setFont(new Font("Times New Roman", Font.ITALIC, 16));	
		details.setEditable(false);
		frame.add(details);
		
		JButton b1 = new JButton();
		b1.setText("Go to Test");
		b1.setBounds(190,250,100,30);
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
