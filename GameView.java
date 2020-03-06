
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javafx.scene.layout.BorderWidths;

import java.awt.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out a matrix of <b>GridButton</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * 
 */

public class GameView extends JFrame {

    private int boardWidth;
    private int boardHeight;
    private GameModel myModel;
    private GameController myController;
    private JPanel mainPanel;

    private JPanel upperPanel;
    private JPanel textPanel;
    private int numOfSteps;
    private JLabel numsLable;

    private JPanel gridPanel;
    private JPanel optionPanel;

    private JButton resetButton;
    private JButton randomButton;
    private JCheckBox solutionCheckBox;
    private JButton quitButton;

    private LinkedList<GridButton> buttons;


    private JOptionPane winningPane;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {

        this.boardHeight = gameModel.getHeight();
        this.boardWidth = gameModel.getWidth();
        this.myController = gameController;
        this.myModel = gameModel;
        this.numOfSteps = this.myModel.getNUmberOfSteps();

        this.setTitle("Lights Out -- the ITI 1121 version");        
        //set the object to the middle of our screen
        this.setLocationRelativeTo(null);
        //end program when user closes the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // background panel serving other panels;
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));

        // grid and buttons container
        this.upperPanel = new JPanel();
        this.upperPanel.setLayout(new BoxLayout(this.upperPanel, BoxLayout.X_AXIS));
        
        // text panel(down panel)
        this.textPanel = new JPanel();
        this.numsLable = new JLabel("Number of step: " +  String.valueOf(this.numOfSteps));
        this.textPanel.add(this.numsLable);

        // grid
        this.gridPanel = new JPanel();
        GridLayout layout = new GridLayout(boardHeight, boardWidth);
        layout.setHgap(0);
        layout.setVgap(0);
        this.gridPanel.setLayout(layout);
        buttons = new LinkedList<GridButton>();
        int numOfElement = boardWidth * boardHeight;
        for(int i = 0; i < numOfElement ; ++i)
        {
            JButton jButton = new GridButton(i % boardWidth, i / boardWidth);
            jButton.setOpaque(false);
            jButton.setContentAreaFilled(false);
            jButton.setBorderPainted(false);
            jButton.setBorder(new EmptyBorder(3,3,3,3));
            jButton.addActionListener(this.myController);
            this.gridPanel.add(jButton);
            this.buttons.add((GridButton)jButton);
        }
        this.upperPanel.add(this.gridPanel);

        this.optionPanel = new JPanel();
        this.optionPanel.setLayout(new BoxLayout(this.optionPanel, BoxLayout.Y_AXIS));
        this.optionPanel.setBorder(new EmptyBorder(10,10,10,10));

        this.resetButton = new JButton("Reset");
        this.resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.resetButton.setBorder(new EmptyBorder(5,5,5,5));
        this.resetButton.setBorderPainted(false);
        this.resetButton.addActionListener(this.myController);

        this.randomButton = new JButton("Random");
        this.randomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.randomButton.setBorder(new EmptyBorder(5,5,5,5));
        this.randomButton.setBorderPainted(false);
        this.randomButton.addActionListener(this.myController);

        this.solutionCheckBox = new JCheckBox("Solution", false);
        this.solutionCheckBox.setBorder(new EmptyBorder(5,5,5,5));
        this.solutionCheckBox.setBorderPainted(false);
        this.solutionCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.solutionCheckBox.addItemListener(this.myController);

        this.quitButton = new JButton("Quit");
        this.quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.quitButton.setBorder(new EmptyBorder(5,5,5,5));
        this.quitButton.setBorderPainted(false);
        this.quitButton.addActionListener(this.myController);


        this.optionPanel.add(this.resetButton);
        this.optionPanel.add(this.randomButton);
        this.optionPanel.add(this.solutionCheckBox);
        this.optionPanel.add(this.quitButton);

        this.upperPanel.add(this.optionPanel);

        this.mainPanel.add(this.upperPanel);
        this.mainPanel.add(this.textPanel);

        //add the panel to the frame
        this.setBounds(500,500,500,500);
        this.add(this.mainPanel, BorderLayout.NORTH);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /**
     * updates the status of the board's GridButton instances based 
     * on the current game model, then redraws the view
     */
    public void lock(){
        this.setEnabled(false);
    }
    public void unlock(){
        this.setEnabled(true);
    }

    public void update(){

        this.numOfSteps = this.myModel.getNUmberOfSteps();
        this.numsLable.setText("Number of step: " +  String.valueOf(this.numOfSteps));
        int numOfElement = this.boardHeight * this.boardWidth;
        if(this.solutionShown( ) == false)
        {
            for(int i = 0; i < numOfElement; ++i)
            {
                boolean isOn = this.myModel.isON(i / this.boardWidth, i % this.boardWidth);
                boolean isClicked = false;
                this.buttons.get(i).setState(isOn, isClicked);
                this.solutionCheckBox.setSelected(this.myModel.isSolutionSet());
            }
        }
        else
        {
            for(int i = 0; i < numOfElement; ++i)
            {
                boolean isOn = this.myModel.isON(i / this.boardWidth, i % this.boardWidth);
                boolean isClicked = this.myModel.solutionSelects(i / this.boardWidth, i % this.boardWidth);
                this.buttons.get(i).setState(isOn, isClicked);
                this.solutionCheckBox.setSelected(this.myModel.isSolutionSet());
            }
        }
    }

    public void winnerScene(){

        final Object [] options = {"Quit", "Play Again"};
        final String winningMessage = "Congratulations, you won in " + String.valueOf(this.numOfSteps) + " steps!\n Would you like to play again?";
        int option = JOptionPane.showOptionDialog(this, winningMessage, "Won", JOptionPane.YES_NO_OPTION,
                                                  JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if(option == JOptionPane.YES_OPTION)
        {
            // as if controller is listener
            this.myController.endGame();
        }
        else if(option == JOptionPane.NO_OPTION)
        {
            this.myController.reset();
        }
    }

    /**
     * returns true if the ``solution'' checkbox
     * is checked
     *
     * @return the status of the ``solution'' checkbox
     */

    public boolean solutionShown(){
        return solutionCheckBox.isSelected();
    }
}
