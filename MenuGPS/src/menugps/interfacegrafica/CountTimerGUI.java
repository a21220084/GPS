package menugps.interfacegrafica;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import menugps.VariaveisGlobais;

/*
 Classname: CountTimerGUI
 Version Information: v2
 Date: 02/12/15 - 18:00
 Author: João Pinho
 Copyright notice:
 1. This class will receive the information about the time used
 2. Another aspect is the interface. This class will create a user interface

 */
public class CountTimerGUI implements ActionListener{
    
    private JFrame frame;
    private JPanel panel;
    
    private JLabel timeLabel = new JLabel();
    
    private JButton startBtn = new JButton("Start");
    private JButton pauseBtn = new JButton("Pause");
    private JButton resumeBtn = new JButton("Resume");
    private JButton newBtn = new JButton("Novo");
    private CountTimer cntd;
    private int h, m, s, tempo;
    private boolean first = true;
    
    public CountTimerGUI(int h, int m, int s) {
        this.h = h;
        this.m = m;
        this.s = s;
        this.tempo = 0;
        try {
            GUI();
        } catch (Exception ex) {
            System.out.println("Erro" + ex);
        }
    }

    /*
     Comment: Este método é usado para tratar da informaçao grafica apresentada
     A forma mais correcta que criamos para fazer isto, foi colocando butões a
     a escuta de determinadas ações
   
     */
    private void GUI() {
        frame = new JFrame();
        panel = new JPanel();
        try {
            
            frame.setSize(new Dimension(300, 150));
            frame.setLocation(700, 100);
            panel.setLayout(new BorderLayout());
            
            timeLabel.setFont(timeLabel.getFont().deriveFont(50.0f));
            panel.add(timeLabel, BorderLayout.NORTH);
            
            startBtn.addActionListener(this);
            pauseBtn.addActionListener(this);
            resumeBtn.addActionListener(this);
            newBtn.addActionListener(this);
            
            startBtn.setEnabled(true);
            resumeBtn.setEnabled(false);
            pauseBtn.setEnabled(false);
            newBtn.setEnabled(true);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        JPanel cmdPanel = new JPanel();
        cmdPanel.setLayout(new GridLayout());
        
        cmdPanel.add(startBtn);
        cmdPanel.add(pauseBtn);
        cmdPanel.add(resumeBtn);
        cmdPanel.add(newBtn);
        
        panel.add(cmdPanel, BorderLayout.SOUTH);
        
        JPanel clrPanel = new JPanel();
        clrPanel.setLayout(new GridLayout(0, 1));
        
        panel.add(clrPanel, BorderLayout.EAST);
        try {
            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        cntd = new CountTimer();
        
    }

    /*
     Comment: está class irá tratar da atribuição do tempo
     Input: String sTime
     Output: não existe
     */
    private void setTimerText(String sTime) {
        try {
            if (sTime.length() != 0) {
                timeLabel.setText(sTime);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     Comment: está class irá tratar da atribuição da cor
     Input: Color sColor
     Output: não existe
     */
    private void setTimerColor(Color sColor) {
        try {
            if (sColor != null) {
                timeLabel.setForeground(sColor);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     Comment: esta class irá tratar das ações do butão, que por sua vez, ira
     invocar os respectivos métodos.
     Input: ActionEvent e
     Output: não existe
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        JButton btn = (JButton) e.getSource();
        
        if (btn.equals(startBtn)) {
            cntd.start();
        } else if (btn.equals(pauseBtn)) {
            cntd.pause();
        } else if (btn.equals(resumeBtn)) {
            cntd.resume();
        } else if (btn.equals(newBtn)){
            cntd.newTask();
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
     2.if the user press "start"/"Pause" button, this class will deal with it.
     */
    
    private class CountTimer implements ActionListener {
        
        private static final int ONE_SECOND = 1000;
        private int count = 0;
        private boolean isTimerActive = false;
        private Timer tmr = new Timer(ONE_SECOND, this);
        
        public CountTimer() {
            tempo = (h * 3600) + (m * 60) + s;
            
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
         Comment: este metodo irá ser invocado quando ocorre uma ação no botão "start",
         a utilização deste método é para configuração dos restantes butões
         Input: não existe
         Output: não existe
         */
        public void start() {
            count = tempo;
            isTimerActive = true;
            tmr.start();
            try {
                startBtn.setEnabled(false);
                pauseBtn.setEnabled(true);
                resumeBtn.setEnabled(false);
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }

        /*
         Comment: este metodo irá ser invocado quando ocorre uma ação no botão "resume",
         a utilização deste método é para voltar a reconfigurar os butões.
         Input: não existe
         Output: não existe
         */
        public void resume() {
            isTimerActive = true;
            tmr.restart();
            try {
                startBtn.setEnabled(false);
                pauseBtn.setEnabled(true);
                resumeBtn.setEnabled(false);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        /*
         Comment: este metodo irá ser invocado quando ocorre uma ação no botão "pause",
         a utilização deste método é para para o sistema e colocar em forma de espera por novas instruções
         Input: não existe
         Output: não existe
     
         */
        public void pause() {
            isTimerActive = false;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd/MM/yyyy");
                startBtn.setEnabled(false);
                pauseBtn.setEnabled(false);
                resumeBtn.setEnabled(true);
                VariaveisGlobais.timePauses.add(TimeFormat(count));
                VariaveisGlobais.systemTimePauses.add(sdf.format(new Date()));
            } catch (Exception e) {
                System.out.println(e);
            } 
        }
        
        /*
         Comment: este metodo irá ser invocado quando ocorre uma ação no botão "Novo",
         a utilização deste método é para o sistema permitir que o utilizador crie uma nova tarefa à parte da atual.
         Input: não existe
         Output: Abertura de uma nova janela de configurações iniciais de uma nova tarefa
     
         */
        
        public void newTask() {
            try {
                Temporizador temp = new Temporizador();
            } catch (Exception e) {
                System.out.println(e);
            } 
        }
    }

    /*
     Comment: o Objectivo deste método é formatar o contador de forma a aparecer 
     na label 
     Input: String count
     Output: String 
     */
    private String TimeFormat(int count) {
        
        int hours = count / 3600;
        int minutes = (count - hours * 3600) / 60;
        int seconds = count - (minutes * 60) - (hours * 3600);
        
        if (first == true) {
            seconds = s;
            first = false;
        }
        return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }
}
