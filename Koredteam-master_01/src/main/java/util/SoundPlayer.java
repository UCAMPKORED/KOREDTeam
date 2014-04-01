package util;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SoundPlayer extends JPanel implements ActionListener
{
	Button b1 = null;
	Button b2 = null;

	MidiPlayer midiPlayer = null; 

	public SoundPlayer() {
		this.setSize(100, 70);
		this.setVisible(true);
		
		
		b1 = new Button("start");
		b2 = new Button("stop");

		b1.addActionListener(this); 
		b2.addActionListener(this);

		Panel centerP = new Panel();
		centerP.setLayout(new BorderLayout());
		Panel bottomP = new Panel();
		bottomP.add(b1);
		bottomP.add(b2);
		centerP.add(bottomP);

		this.add(centerP);

		 
		midiPlayer = new MidiPlayer();
		midiPlayer.start();
	}

	public void actionPerformed(ActionEvent e) { 
		Object o = e.getSource();
		if (o == b1) { 
			midiPlayer.start(); 
		} else if (o == b2) { 
			midiPlayer.stop();
		}
	}
}

class MidiPlayer {
	private Sequencer sequencer = null;
	private Sequence sequence = null; 
	MidiPlayer() {
		try {
			sequence = MidiSystem.getSequence(new File("sound/circle.mid"));
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setSequence(sequence);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void start() {
		try {
			sequencer.stop();
			sequencer.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void stop() {
		try {
			sequencer.stop();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
