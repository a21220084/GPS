package menugps.interfacegrafica;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*

Classname: CountTimerGUI
Version Information: v2
Date: 02/12/15 - 18:00
Author: João Pinho
Copyright notice:
   1. This class will receive the information about the time used
   2. Another aspect is the interface. This class will create a user interface

*/


public class CountTimerGUI implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    private JLabel timeLabel = new JLabel();

    private JButton startBtn = new JButton("Start");
    private JButton pauseBtn = new JButton("Pause");
    private JButton resumeBtn = new JButton("Resume");
    private JButton stopBtn = new JButton("Stop");
    private JButton resetBtn = new JButton("Reset");

    private JButton greenBtn = new JButton("Green");
    private JButton redBtn = new JButton("Red");

    private CountTimer cntd;
    private int h,m,s, tempo;
    private boolean first = true;

    public CountTimerGUI(int h, int m, int s) {
        this.h = h;
        this.m = m;
        this.s = s;
        setTimerText("         ");
        GUI();
    }
    
    /*
    Comment: Will position the buttons on screen
   
    */
    private void GUI() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(new Dimension(300, 400));

        panel.setLayout(new BorderLayout());
        //timeLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        timeLabel.setFont(timeLabel.getFont().deriveFont(50.0f));
        panel.add(timeLabel, BorderLayout.NORTH);

        startBtn.addActionListener(this);
        pauseBtn.addActionListener(this);
        resumeBtn.addActionListener(this);
        stopBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        greenBtn.addActionListener(this);
        redBtn.addActionListener(this);
        startBtn.setEnabled(true);
        resumeBtn.setEnabled(false);
        pauseBtn.setEnabled(false);
        resetBtn.setEnabled(false);

        JPanel cmdPanel = new JPanel();
        cmdPanel.setLayout(new GridLayout());

        cmdPanel.add(startBtn);
        cmdPanel.add(pauseBtn);
        cmdPanel.add(resumeBtn);
        //cmdPanel.add(stopBtn);
        cmdPanel.add(resetBtn);

        panel.add(cmdPanel, BorderLayout.SOUTH);

        JPanel clrPanel = new JPanel();
        clrPanel.setLayout(new GridLayout(0, 1));

        //clrPanel.add(greenBtn);
        //clrPanel.add(redBtn);
        panel.add(clrPanel, BorderLayout.EAST);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        //frame.pack();

        cntd = new CountTimer();

    }

    /*
    Comment: This method will add text in Label
    */
    private void setTimerText(String sTime) {
        timeLabel.setText(sTime);
    }

    /*
      Comment: This method will add background to label with time
    */
    private void setTimerColor(Color sColor) {
        timeLabel.setForeground(sColor);
    }

    /*
    Comment: This method will deal with button action
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        JButton btn = (JButton) e.getSource();

        if (btn.equals(redBtn)) {
            setTimerColor(Color.RED);
        } else if (btn.equals(startBtn)) {
            cntd.start();
        } else if (btn.equals(pauseBtn)) {
            cntd.pause();
        } else if (btn.equals(resumeBtn)) {
            cntd.resume();
        } 
    }
/*
    
Classname: CountTimer
Version Information: v1
Date: 02/12/15 - 17:30
Author: João Pinho
Copyright notice:
   1. This class will be Responsible for the time while the program already
configurated and in execution.
   2.if the user press "start"/"Pause"/"Reset" button, this class will deal with it.
*/

    
    private class CountTimer implements ActionListener {

        private static final int ONE_SECOND = 1000;
        private int count = 0;
        private boolean isTimerActive = false;
        private Timer tmr = new Timer(ONE_SECOND, this);

        public CountTimer() {
            tempo = (h*3600) + (m*60) + s;
            //System.out.println("tempo = " + tempo);
            count = tempo;
            setTimerText(TimeFormat(count));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isTimerActive) {
                count--;
                setTimerText(TimeFormat(count));
            }
        }

        /*
        Comment: This method will be used when the user press Button "Start"
        After the used call this method, the time will be started and the rest of the button will be
        configurated.
        */
        public void start() {
            count = tempo;
            isTimerActive = true;
            tmr.start();
            startBtn.setEnabled(false);
            pauseBtn.setEnabled(true);
            resumeBtn.setEnabled(false);
            resetBtn.setEnabled(true);
        }

        /*
        Comment: This method will start the time again and reconfigurate the buttons
        */
        public void resume() {
            isTimerActive = true;
            tmr.restart();
            startBtn.setEnabled(false);
            pauseBtn.setEnabled(true);
            resumeBtn.setEnabled(false);
            resetBtn.setEnabled(true);
        }

        /*public void stop() {
         tmr.stop();
         }*/
        
        
        /*
        
        Coloca o tempo em pausa e altera as propriedades dos botões de configuração
        */
        public void pause() {
            isTimerActive = false;
            startBtn.setEnabled(false);
            pauseBtn.setEnabled(false);
            resumeBtn.setEnabled(true);
            resetBtn.setEnabled(true);
        }
        /*
        public void reset() {
            count = 0;
            isTimerActive = true;
            //tmr.restart();
            tmr.stop();
            setTimerText(TimeFormat(count));
            startBtn.setEnabled(true);
            resumeBtn.setEnabled(false);
            pauseBtn.setEnabled(false);
            resetBtn.setEnabled(false);
        }*/

    }

    /*
    Comment: This method will format the time to string and will be used on
    label time
    */
    private String TimeFormat(int count) {

        int hours = count / 3600;
        int minutes = (count - hours * 3600) / 60;
        int seconds = count - (minutes * 60) - (hours * 3600);
        
        if (first == true) {
            seconds=s;
            first=false;
        }
        
        //System.out.println("seconds" + seconds + "count = " + count + "minutos = " + minutes + "horas = " + hours);

        return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }
}
