
import java.util.ArrayList;

public class ArrayListSolutionQueue implements SolutionQueue {


    //queue stores the references of the elements currentluy in the queue
    public ArrayList<Solution> queue;

    //constructor
    public ArrayListSolutionQueue() {
        queue = new ArrayList<Solution>(); 
    }


    public void enqueue(Solution value) {
        //System.out.println(queue.size());
        //the implementation of the method enqueue from the interface SolutionQueue
        queue.add(value);
    }

    public Solution dequeue() {
        //System.out.println(queue.size());
        //the implementation of the method dequeue from the interface SolutionQueue
        return queue.remove(0);      
    }


    public boolean isEmpty() {
        //the implementation of the method isEmpty from the interface SolutionQueue.
        if (queue.size() == 0){
            return true;
        }
        return false;
    }

}
