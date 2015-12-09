package menugps.interfacegrafica;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class FrameProGest extends JFrame implements Observer{
    
    private Container cp;
    private JButton gerirProjeto;
    private JButton sair;
    private JPanel painelInicial;
    private JLabel tituloInicial;
    
    public FrameProGest(int x, int y, int largura, int altura){
        setLocation(0, 20);
        setSize(largura, altura);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        dispoeComponentes();
    }
    
    public final void dispoeComponentes() {
        painelInicial = new JPanel(new GridLayout(4, 3));
        painelInicial.setOpaque(false);
        painelInicial.setSize(200, 100);
        
        tituloInicial = new JLabel("ProGest");
        tituloInicial.setFont(tituloInicial.getFont().deriveFont(50.0f));
        
        gerirProjeto = new JButton("Iniciar Gestão de Projeto");
        
        painelInicial.add(tituloInicial);
        painelInicial.add(gerirProjeto);
        painelInicial.setVisible(true);
        
        cp = getContentPane();
        cp.add(painelInicial);
        
        setResizable(false);
        validate();
        //pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
