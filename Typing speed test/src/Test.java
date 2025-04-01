import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Test 
{
	JFrame frm = new JFrame ();
	int duration;
    int totalDuration; // Selected test duration
    long startTime;
    boolean testStart = false;
    Timer countdown;
    JLabel timerLabel = new JLabel();
    JTextArea textarea = new JTextArea();
 	JTextArea blank = new JTextArea();
 	String para1 = "The sky is blue, the sun is shining, and a cool breeze is blowing as people walk in the park, children laugh and play, birds fly high in the air, and everything feels calm and peaceful on this beautiful day.";
    String para2 = "As the rain gently falls, tiny droplets race down the window, creating winding paths that disappear as new ones take their place, while the soft rumble of thunder echoes in the distance, and the fresh scent of wet earth fills the air, bringing a sense of calm and comfort, as people sit indoors with a warm cup of tea, watching the world slow down for a while, enjoying the peaceful rhythm of nature’s soothing melody.";

	String originalText = "";
	
	Test()
	{
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(500,500);
		frm.setLocationRelativeTo(null);
		frm.setTitle("Typing Speed Tester");
		frm.setLayout(null);
		frm.getContentPane().setBackground(new Color(200, 230, 255));
	
		JLabel lbl = new JLabel();
		lbl.setText("Choose Test Duration: ");
		lbl.setBounds(20,10,170,20);
		lbl.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frm.add(lbl);
		
		String timeset[] = {"30 Seconds", "60 Seconds"};
		
		JComboBox combo = new JComboBox(timeset);
		combo.setBounds(180,10,130,20);
		combo.setFont(new Font("Times New Roman", Font.PLAIN, 14));  
		frm.add(combo);
		
		textarea = new JTextArea();
        textarea.setBounds(20, 50, 445, 120);
        textarea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setEditable(false);
        
        JLabel typ = new JLabel();
		typ.setText("Copy above text:");
		typ.setBounds(20, 200, 250, 20);
		typ.setFont(new Font("Times New Roman", Font.BOLD, 16));
		 
		timerLabel.setBounds(300, 200, 200, 20);
        timerLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        timerLabel.setText("Time Left:");
		
		blank.setBounds(20, 230, 445, 120);
    	blank.setFont(new Font("Times New Roman", Font.PLAIN, 14));
    	blank.setLineWrap(true);
    	blank.setWrapStyleWord(true);
    	blank.setEditable(true);
		
		combo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String select = (String) combo.getSelectedItem();
				if (select.equals("30 Seconds"))
				{
					duration = 30;
					frm.add(textarea);
					textarea.setText(para1);
					frm.add(typ);
					frm.add(timerLabel);
					frm.add(blank);
				}
				else 
				{
					duration = 60;
					frm.add(textarea);
					textarea.setText(para2);
					frm.add(typ);
					frm.add(timerLabel);
					frm.add(blank);
				}
				frm.revalidate();
				frm.repaint();
				resetTest();
			}
		});
		
		blank.addKeyListener(new KeyAdapter() 
		{
            @Override
            public void keyPressed(KeyEvent e) 
            {
                if (!testStart) 
                {
                    startTest();
                }
            }
        });
		frm.setVisible(true);
	}
	public void startTest() 
	{
        if (testStart) return;

        testStart = true;
        totalDuration = duration; // Store the selected total duration
        startTime = System.currentTimeMillis();

        countdown = new Timer(1000, e ->
        {
            duration--;
            timerLabel.setText("Time Left: " + duration + " seconds");

            if (duration <= 0) 
            {
                ((Timer) e.getSource()).stop();
                endTest();
            }
        });
        countdown.setInitialDelay(0);
        countdown.start();
    }

    public void resetTest() 
    {
        if (countdown != null) 
        {
            countdown.stop();
        }
        testStart = false;
        timerLabel.setText("Time Left: " + duration + " seconds");
        blank.setText(""); // Clear typing area
        blank.setEditable(true); // Enable typing again
    }

    public void endTest() 
    {
    	blank.setEditable(false);
        testStart = false;

        String typedText = blank.getText().trim();
        String originalText = textarea.getText().trim();

        int correctChars = 0;
        int totalChars = Math.min(typedText.length(), originalText.length());
        for (int i = 0; i < totalChars; i++) 
        {
            if (typedText.charAt(i) == originalText.charAt(i)) 
            	correctChars++;
        }
        double accuracy = (totalChars == 0) ? 0 : (correctChars / (double) totalChars) * 100;
        int wordCount = typedText.isEmpty() ? 0 : typedText.split("\\s+").length;
        int wpm = (int) ((wordCount / ((System.currentTimeMillis() - startTime) / 60000.0)));

        String typingLevel;
        if (wpm <= 30) 
        {
            typingLevel = "Beginner.";
        } 
        else if (wpm > 30 && wpm <=60) 
        {
            typingLevel = "Intermediate.";
        } 
        else if (wpm > 60 && wpm <= 80 ) 
        {
            typingLevel = "Advanced.";
        } 
        else 
        {
            typingLevel = "Expert.";
        }
        JOptionPane.showMessageDialog(frm, 
        		"WPM: " + wpm + "\nAccuracy: " + String.format("%.2f", accuracy) + "%\nLevel: " + typingLevel + "\n", 
        		"Results", JOptionPane.INFORMATION_MESSAGE);
        frm.dispose();
    }
}