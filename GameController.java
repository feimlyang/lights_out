
import javax.swing.*;
import java.awt.event.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * 
 */


public class GameController implements ActionListener, ItemListener {

    private GameModel gameModel;
    private GameView gameView;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     */
    public GameController(int width, int height) {
        this.gameModel = new GameModel(width, height);
        this.gameView = new GameView(this.gameModel, this);
    }

    /**
     * Callback used when the user clicks a button (reset, 
     * random or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton)(e.getSource());
        // disable the main windows when updating.
        this.gameView.lock();
        if(button instanceof GridButton)
        {
            GridButton clickedButton = (GridButton)button;
            int width = clickedButton.getColumn();
            int row  = clickedButton.getRow();
            this.gameModel.click(row, width);
        }
        else
        {
            if(button.getText().equals("Reset"))
            {
                this.gameModel.reset();

            }
            else if(button.getText().equals("Random"))
            {
                System.out.println("Random clicked!");
                this.gameModel.randomize();

            }
            else if(button.getText().equals("Quit"))
            {
                System.exit(0);
            }
            else if(button.getText().equals("Play again"))
            {
                this.gameModel.reset();
            }
        }

        if(this.gameView.solutionShown())
        {
            this.gameModel.setSolution();
        }

        this.gameView.update();
    
        if(this.gameModel.isFinished())
        {
            this.gameView.winnerScene();
        }
        // enable the window after finishing update.
        this.gameView.unlock();
}

    /**
     * Callback used when the user select/unselects
     * a checkbox
     *
     * @param e
     *            the ItemEvent
     */

    public void  itemStateChanged(ItemEvent e){

        // disable the main windows when updating.
        this.gameView.lock();
        JCheckBox checkbox = null;
        if(e.getSource() instanceof JCheckBox)
        {
            checkbox = (JCheckBox)(e.getSource());
            checkbox.setEnabled(false);
            if(this.gameView.solutionShown())
            {
                this.gameModel.setSolution();
            }
            else
            {
                this.gameModel.unsetSolution();
            }
        }
        this.gameView.update();
        if(checkbox != null)
        {
            checkbox.setEnabled(true);
        }
        // enable the window after finishing update.
        this.gameView.unlock();
    }

    /**
     * reset the game.
     */
    public void reset()
    {
        this.gameView.lock();
        this.gameModel.reset();
        this.gameView.update();
        this.gameView.unlock();
    }
    /**
     * reset the game.
     */
    public void endGame()
    {
        System.exit(0);
    }
}
