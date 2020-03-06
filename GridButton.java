
import javax.lang.model.util.ElementScanner6;
import javax.swing.ImageIcon;
import javax.swing.JButton;



public class GridButton extends JButton {


    private ImageIcon [] Icons;
    private ImageIcon currentIcon;
    private int column;
    private int row;
    private boolean isOn;
    private boolean isClicked;

    /**
     * Constructor used for initializing a GridButton at a specific
     * Board location.
     * 
     * @param column
     *            the column of this Cell
     * @param row
     *            the row of this Cell
     */

    public GridButton(int column, int row) {

        // YOUR CODE HERE
        this.Icons = new ImageIcon[4];
        this.Icons[0] = new ImageIcon("./Icons/Light-0.png");
        this.Icons[1] = new ImageIcon("./Icons/Light-1.png");
        this.Icons[2] = new ImageIcon("./Icons/Light-2.png");
        this.Icons[3] = new ImageIcon("./Icons/Light-3.png");
        this.currentIcon = this.Icons[1];
        this.column = column;
        this.row = row;
        this.setIcon(this.currentIcon);
        this.setVisible(true);
    }

   /**
    * sets the icon of the button to reflect the
    * state specified by the parameters
    * @param isOn true if that location is ON
    * @param isClicked true if that location is
    * tapped in the model's current solution
    */ 
    public void setState(boolean isOn, boolean isClicked) {
        if(isOn && isClicked)
        {
            this.setIcon(this.Icons[2]);
        }
        else if(isOn && !isClicked)
        {
            this.setIcon(this.Icons[0]);
        }
        else if(!isOn && isClicked)
        {
            this.setIcon(this.Icons[3]);
        }
        else
        {
            this.setIcon(this.Icons[1]);
        }
    }
    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {

        return row;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
        
        return column;
    }

    
}
