import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {
    JFrame frame = new JFrame();
    JPanel topPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel scorePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JLabel scoreField = new JLabel();
    JButton[] gameButtons = new JButton[9];
    JButton reset = new JButton();
    Random rand = new Random();
    boolean player1Turn;
    int[] score = new int[3];

    public TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(Color.DARK_GRAY);
        textField.setForeground(Color.ORANGE);
        textField.setFont(new Font("Ink Free", Font.BOLD, 60));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        reset.setText("New Game");
        reset.setBackground(Color.DARK_GRAY);
        reset.setFont(new Font("Ink Free", Font.BOLD, 45));
        reset.setForeground(Color.ORANGE);
        reset.setBorder(new EmptyBorder(0,0,0,0));
        reset.addActionListener(this);

        score[0] = 0;
        score[1] = 0;
        score[2] = 0;

        scoreField.setBackground(Color.DARK_GRAY);
        scoreField.setForeground(Color.ORANGE);
        scoreField.setFont(new Font("Ink Free", Font.BOLD, 45));
        scoreField.setText("X: " + score[0] + "      O: " + score[1] + "      D: " + score[2]);
        scoreField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(800,50));

        scorePanel.setPreferredSize(new Dimension(800, 50));
        scorePanel.setLayout(new BorderLayout());

        buttonPanel.setLayout(new GridLayout(3,3));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < 9; i++){
            gameButtons[i] = new JButton();
            buttonPanel.add(gameButtons[i]);
            gameButtons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            gameButtons[i].setFocusable(false);
            gameButtons[i].setBackground(Color.PINK);
            gameButtons[i].setBorder(new LineBorder(Color.BLACK, 2));
            gameButtons[i].addActionListener(this);
        }

        topPanel.setLayout(new GridLayout(2,1));

        titlePanel.add(textField, BorderLayout.CENTER);

        scorePanel.add(scoreField, BorderLayout.CENTER);
        scorePanel.add(reset, BorderLayout.EAST);

        topPanel.add(titlePanel);
        topPanel.add(scorePanel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == reset){
            for (int i = 0; i < 9; i++) {
                gameButtons[i].setText("");
                gameButtons[i].setBackground(Color.PINK);
                gameButtons[i].setEnabled(true);
            }
            firstTurn();
        }

        for (int i = 0; i < 9; i++){
            if (e.getSource() == gameButtons[i]) {
                if (player1Turn){
                    if (gameButtons[i].getText() == ""){
                        gameButtons[i].setForeground(Color.BLUE);
                        gameButtons[i].setText("X");
                        player1Turn = false;
                        textField.setText("O Turn");
                        check();
                    }
                }
                else {
                    if (gameButtons[i].getText() == "") {
                        gameButtons[i].setForeground(Color.RED);
                        gameButtons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X Turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn(){
        if (rand.nextInt(2) == 0) {
            player1Turn = true;
            textField.setText("X turn");
        }
        else {
            player1Turn = false;
            textField.setText("O Turn");
        }
    }

    public void check(){
        if ((gameButtons[0].getText() == "X") && (gameButtons[1].getText() == "X") && (gameButtons[2].getText() == "X")){
            xWins(0,1,2);
        }
        if ((gameButtons[3].getText() == "X") && (gameButtons[4].getText() == "X") && (gameButtons[5].getText() == "X")){
            xWins(3,4,5);
        }
        if ((gameButtons[6].getText() == "X") && (gameButtons[7].getText() == "X") && (gameButtons[8].getText() == "X")){
            xWins(6,7,8);
        }
        if ((gameButtons[0].getText() == "X") && (gameButtons[3].getText() == "X") && (gameButtons[6].getText() == "X")){
            xWins(0,3,6);
        }
        if ((gameButtons[1].getText() == "X") && (gameButtons[4].getText() == "X") && (gameButtons[7].getText() == "X")){
            xWins(1,4,7);
        }
        if ((gameButtons[2].getText() == "X") && (gameButtons[5].getText() == "X") && (gameButtons[8].getText() == "X")){
            xWins(2,5,8);
        }
        if ((gameButtons[0].getText() == "X") && (gameButtons[4].getText() == "X") && (gameButtons[8].getText() == "X")){
            xWins(0,4,8);
        }
        if ((gameButtons[2].getText() == "X") && (gameButtons[4].getText() == "X") && (gameButtons[6].getText() == "X")){
            xWins(2,4,6);
        }


        if ((gameButtons[0].getText() == "O") && (gameButtons[1].getText() == "O") && (gameButtons[2].getText() == "O")){
            oWins(0,1,2);
        }
        if ((gameButtons[3].getText() == "O") && (gameButtons[4].getText() == "O") && (gameButtons[5].getText() == "O")){
            oWins(3,4,5);
        }
        if ((gameButtons[6].getText() == "O") && (gameButtons[7].getText() == "O") && (gameButtons[8].getText() == "O")){
            oWins(6,7,8);
        }
        if ((gameButtons[0].getText() == "O") && (gameButtons[3].getText() == "O") && (gameButtons[6].getText() == "O")){
            oWins(0,3,6);
        }
        if ((gameButtons[1].getText() == "O") && (gameButtons[4].getText() == "O") && (gameButtons[7].getText() == "O")){
            oWins(1,4,7);
        }
        if ((gameButtons[2].getText() == "O") && (gameButtons[5].getText() == "O") && (gameButtons[8].getText() == "O")){
            oWins(2,5,8);
        }
        if ((gameButtons[0].getText() == "O") && (gameButtons[4].getText() == "O") && (gameButtons[8].getText() == "O")){
            oWins(0,4,8);
        }
        if ((gameButtons[2].getText() == "O") && (gameButtons[4].getText() == "O") && (gameButtons[6].getText() == "O")){
            oWins(2,4,6);
        }

        int empty = 9;
        for (int i = 0; i < 9; i++){
            if (gameButtons[i].getText() != "")
                empty--;
        }
        if (empty == 0) {
            textField.setText("Draw!");
            score[2] = score[2] + 1;
            scoreField.setText("X: " + score[0] + "      O: " + score[1] + "      D: " + score[2]);
        }
    }

    public void xWins(int a, int b, int c){
        gameButtons[a].setBackground(Color.GREEN);
        gameButtons[b].setBackground(Color.GREEN);
        gameButtons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++){
            gameButtons[i].setEnabled(false);
        }
        textField.setText("X wins!");
        score[0] = score[0] + 1;
        scoreField.setText("X: " + score[0] + "      O: " + score[1] + "      D: " + score[2]);
    }

    public void oWins(int a, int b, int c){
        gameButtons[a].setBackground(Color.GREEN);
        gameButtons[b].setBackground(Color.GREEN);
        gameButtons[c].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++){
            gameButtons[i].setEnabled(false);
        }
        textField.setText("O wins!");
        score[1] = score[1] + 1;
        scoreField.setText("X: " + score[0] + "      O: " + score[1] + "      D: " + score[2]);
    }
}
