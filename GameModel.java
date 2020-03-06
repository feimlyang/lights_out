
import java.util.Random;

public class GameModel {
	//The class GameModel is used to capture the current state of the board
	private boolean[][] board;
	private int width;
	private int height;
	private int numOfSteps;
	private boolean isSetSolution;
	private Solution solutionOnTheModel;

	//constructor
	public GameModel(int width, int height){
		//Creates an all OFF game of width width and of height height
		this.width = width;
		this.height = height;
		board = new boolean[height][width];
		for (int i = 0; i < width*height; i++){
			board[i/width][i%width] = false;
		}
		numOfSteps = 0;
		this.isSetSolution = false;
		this.solutionOnTheModel = null;
	}

	public int getHeight(){
		//getter for height
		return height;
	}


	public int getWidth(){
		//getter for width
		return width;
	}

	public boolean isON(int i, int j){
		//returns true if the location at row i and column j is ON, false otherwise.
			if (board[i][j] == false){
				return false;
		}
		return true;
	}

	public void reset(){
		//resets the model to all OFF.
		for (int i = 0; i < width*height; i++){
			board[i/width][i%width] = false;
		}
		this.unsetSolution();
		numOfSteps = 0;
    }

    public void set(int i, int j, boolean value){
    	//sets the location (i,j) of the model to “value” (be careful, that’s column i and row j)
    	board[j][i] = value;
	}
	
	public void click(int i, int j)
	{
		board[i][j] = !board[i][j];
		//upper
		if(i > 0)
		{
			board[i-1][j] = !board[i-1][j]; 
		}
		//left
		if(j > 0)
		{
			board[i][j-1] = !board[i][j-1];
		}
		//down
		if(i < height - 1)
		{
			board[i+1][j] = !board[i+1][j];
		}
		//right
		if(j < width - 1)
		{
			board[i][j+1] = !board[i][j+1];
		}
		++numOfSteps;
	}
	public int getNUmberOfSteps()
	{
		return numOfSteps;
	}
	public boolean isFinished()
	{
		for(int i = 0; i < width*height; i++){
			if(board[i/width][i%width] == false)
			{
				return false;
			}
		}
		return true;
	}
	public void randomize()
	{
		numOfSteps = 0;
		boolean solvable = false;
		Random random = new Random();
		while(solvable == false)
		{
			for (int i = 0; i < width*height; i++){
				board[i/width][i%width] = random.nextBoolean();
			}
			if(LightsOut.solveWithOneSolution(this) != null)
			{
				solvable = true;
			}
		}
	}

	public boolean isSolutionSet()
	{
		return isSetSolution;
	}
	public void setSolution()
	{
		isSetSolution = true;
		this.solutionOnTheModel = LightsOut.solveShortest(this);
	}
	public void unsetSolution()
	{
		// unchecked the solution checkbox.
		isSetSolution = false;
		this.solutionOnTheModel = null;
	}

	public boolean solutionSelects(int i, int j)
	{
		if(isSetSolution && solutionOnTheModel != null && solutionOnTheModel.get(j, i))
		{
			return true;
		}
		return false;
	}

    /**
     * returns a string representation of the current model
     *
     * @return
     *      the string representation
     */
    public String toString(){
    	//returns a String representation of the model
    	StringBuffer out = new StringBuffer();
        out.append("[");
        for(int i = 0; i < height; i++){
            out.append("[");
            for(int j = 0; j < width ; j++) {
                if (j>0) {
                    out.append(",");
                }
                out.append(board[i][j]);
            }
            out.append("]"+(i < height -1 ? ",\n" :""));
        }
        out.append("]");
        return out.toString();

	}
}
