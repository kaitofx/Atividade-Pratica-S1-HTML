//Curso de Engenharia de Software - UniEVANGÉLICA 
//Disciplina de Programação Web 
//Dev: Gustavo Gomes dos santos - 2111267
//01/04/23

<!--Tipo de documento-->
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JogoDeMemoria extends JFrame implements ActionListener {

    private JPanel painelTabuleiro;
    private JLabel labelTempo;
    private JButton[] botoes;
    private int[] numeros;
    private int carta1 = -1;
    private int carta2 = -1;
    private int contadorPares = 0;
    private Timer timer;
    private int tempoRestante = 60; // tempo em segundos

    public JogoDeMemoria() {
        setTitle("Jogo de Memória");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        setResizable(false);
        setLocationRelativeTo(null);

        // painel com o tabuleiro do jogo
        painelTabuleiro = new JPanel();
        painelTabuleiro.setLayout(new GridLayout(4, 4, 10, 10));
        painelTabuleiro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // botões do tabuleiro
        botoes = new JButton[16];
        numeros = new int[16];
        for (int i = 0; i < 16; i++) {
            botoes[i] = new JButton();
            botoes[i].addActionListener(this);
            botoes[i].setBackground(Color.BLUE);
            botoes[i].setFont(new Font("Arial", Font.BOLD, 48));
            botoes[i].setForeground(Color.WHITE);
            painelTabuleiro.add(botoes[i]);
            numeros[i] = i % 8;
        }

        // label com o tempo restante
        labelTempo = new JLabel("Tempo restante: " + tempoRestante);
        labelTempo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTempo.setHorizontalAlignment(JLabel.CENTER);

        // painel principal com o tabuleiro e o label do tempo
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.add(painelTabuleiro, BorderLayout.CENTER);
        painelPrincipal.add(labelTempo, BorderLayout.SOUTH);

        add(painelPrincipal);
        pack();

        // embaralha os números das cartas
        embaralharNumeros();

        // inicia o timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempoRestante--;
                labelTempo.setText("Tempo restante: " + tempoRestante);
                if (tempoRestante == 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Tempo esgotado! Você perdeu.");
                    reiniciarJogo();
                }
            }
        });
        timer.start();
    }

    private void embaralharNumeros() {
        ArrayList<Integer> listaNumeros = new ArrayList<Integer>();
        for (int i = 0; i < numeros.length; i++) {
            listaNumeros.add(numeros[i]);
        }
        Collections.shuffle(listaNumeros);
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = listaNumeros.get(i);
        }
    }

   
