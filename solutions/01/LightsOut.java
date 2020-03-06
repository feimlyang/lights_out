
import java.util.ArrayList;

public class LightsOut {

    public static ArrayList<Solution> solve(int width, int height){
        ArrayListSolutionQueue processingQueue = new ArrayListSolutionQueue(); 
        ArrayList<Solution> arrayOfSolutions = new ArrayList<Solution>();  

        //init the (0,0) to true/false; the init is not empty
        Solution firstIn = new Solution(width, height);
        Solution secondIn = new Solution(width, height);

        firstIn.setNext(true);
        secondIn.setNext(false);
        processingQueue.enqueue(firstIn);
        processingQueue.enqueue(secondIn);

        long currentms = 0; 
        long startms = System.currentTimeMillis();

        while (processingQueue.isEmpty() == false){
            //start at (0,1)
            Solution out = processingQueue.dequeue();
            
            if (out.isSuccessful() == true){
                currentms = System.currentTimeMillis() - startms;
                System.out.println("Solution found in " + currentms + " ms");
                arrayOfSolutions.add(out);

            }
            else if (out.isReady() == false){
                Solution firstSplit = new Solution(out);

                firstSplit.setNext(true);
                out.setNext(false); //secondSplit 
                processingQueue.enqueue(firstSplit);
                processingQueue.enqueue(out);
            }
        }
        //isEmpty == true. all solutions found
        return arrayOfSolutions;      
    }

    public static void main(String[] args) { 
        int width = 3;
        int height = 3;
        if (args.length != 0){
            try{
                if (Integer.valueOf(args[0]) > 0 && Integer.valueOf(args[1]) > 0){
                    width = Integer.valueOf(args[0]);
                    height  = Integer.valueOf(args[1]);
                }
                else{
                    throw new ArithmeticException("invalid size");
                }
            }
            catch(Exception e){
                System.out.println("Invalid width, using default...");
                System.out.println("Invalid height, using default...");

            }
        }

        ArrayList<Solution> finalSolutions = solve(width, height);

        for (int i = 0; i < finalSolutions.size(); i++){
            System.out.println(finalSolutions.get(i).toString() + "\n****");
        }
        System.out.println("In a board of " + width + "x" + height + ": " + Integer.valueOf(finalSolutions.size()) + " solutions.");
    }
}