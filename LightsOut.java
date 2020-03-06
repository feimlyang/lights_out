

import java.util.ArrayList;


/**
 * The class <b>LightsOut</b> launches the game
 *
 * 
 */
public class LightsOut {


     /**
     * default width of the game.
     */
    public static final int DEFAULT_WIDTH = 10;
     /**
     * default height of the game.
     */
    public static final int DEFAULT_HEIGTH = 8;


    // COMPLETE THE CLASS HERE (AS PER Q1)

    /**
     * The method <b>solve</b> finds all the 
     * solutions to the <b>Lights Out</b> game 
     * for an initially completely ``off'' board 
     * of size <b>widthxheight</b>, using a  
     * Breadth-First Search algorithm. 
     *
     * It returns an <b>ArrayList&lt;Solution&gt;</b> 
     * containing all the valid solutions to the 
     * problem.
     *
     * This version does not continue exploring a 
     * partial solution that is known to be
     * impossible. It will also attempt to complete
     * a solution as soon as possible
     *
     * During the computation of the solution, the 
     * method prints out a message each time a new 
     * solution  is found, along with the total time 
     * it took (in milliseconds) to find that solution.
     *
     * @param width
     *  the width of the board
     * @param height
     *  the height of the board
     * @return
     *  an instance of <b>ArrayList&lt;Solution&gt;</b>
     * containing all the solutions
     */
    public static ArrayList<Solution> solve(GameModel model)
    {
        Queue<Solution> queue = new QueueImplementation<Solution>();
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        queue.enqueue(new Solution(model.getWidth(), model.getHeight()));
        long start = System.currentTimeMillis();
        while(!queue.isEmpty()) {
            Solution s = (Solution)(queue.dequeue());
            if(s.isReady()){
            	if(s.isSuccessful(model))
            	{
            		solutions.add(s);
            	}
            }
            else
            {
                boolean withTrue = s.stillPossible(true, model);
                boolean withFalse = s.stillPossible(false, model);
                if(withTrue && withFalse)
                {
                    Solution s2 = new Solution(s);
                    s.setNext(true);
                    queue.enqueue(s);
                    s2.setNext(false);
                    queue.enqueue(s2);
                }
                else if(withTrue)
                {
                    s.setNext(true);
                    if(s.finish(model)) {
                        queue.enqueue(s);
                    }
                }
                else if(withFalse) {
                    s.setNext(false);
                    if(s.finish(model)) {
                        queue.enqueue(s);
                    }

                }
            }
        }
        return solutions;
    }


    public static Solution solveWithOneSolution(GameModel model)
    {
        Queue<Solution> queue = new QueueImplementation<Solution>();
        queue.enqueue(new Solution(model.getWidth(), model.getHeight()));
        long start = System.currentTimeMillis();
        while(!queue.isEmpty()) {
            Solution s = (Solution)(queue.dequeue());
            if(s.isReady()){
            	if(s.isSuccessful(model))
            	{
            		return s;
            	}
            }
            else
            {
                boolean withTrue = s.stillPossible(true, model);
                boolean withFalse = s.stillPossible(false, model);
                if(withTrue && withFalse)
                {
                    Solution s2 = new Solution(s);
                    s.setNext(true);
                    queue.enqueue(s);
                    s2.setNext(false);
                    queue.enqueue(s2);
                }
                else if(withTrue)
                {
                    s.setNext(true);
                    if(s.finish(model)) {
                        queue.enqueue(s);
                    }
                }
                else if(withFalse) {
                    s.setNext(false);
                    if(s.finish(model)) {
                        queue.enqueue(s);
                    }

                }
            }
        }
        return null;
    }

    public static Solution solveShortest(GameModel model)
    {
        ArrayList<Solution> solutions = solve(model);
        if(solutions.isEmpty()) return null;
        int shortestSize = solutions.get(0).getSize();
        int numOfSolutions = solutions.size();
        int shortestSolutionIndex = 0;
        for(int i = 0; i < numOfSolutions; ++i)
        {
            int currentSize = solutions.get(i).getSize();
            if(currentSize < shortestSize)
            {
                shortestSize = currentSize;
                shortestSolutionIndex = i;
            }
        }
        return solutions.get(shortestSolutionIndex);
    }
   /**
     * <b>main</b> of the application. Creates the instance of  GameController 
     * and starts the game. If two parameters width and height
     * are passed, they are used. 
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (less than 1).
     * 
     * @param args
     *            command line parameters
     */
     public static void main(String[] args) {
        int width   = DEFAULT_WIDTH;
        int height  = DEFAULT_HEIGTH;
 

        if (args.length == 2) {
            try{
                width = Integer.parseInt(args[0]);
                if(width<1){
                    System.out.println("Invalid argument, using default...");
                    width = DEFAULT_WIDTH;
                }
                height = Integer.parseInt(args[1]);
                if(height<1){
                    System.out.println("Invalid argument, using default...");
                    height = DEFAULT_HEIGTH;
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid argument, using default...");
                width   = DEFAULT_WIDTH;
                height  = DEFAULT_HEIGTH;
            }
        }
        GameController gameController = new GameController(width, height);
    }


}
