
import java.util.ArrayList;

public class ArrayListSolutionQueue implements SolutionQueue {
    //queue stores the references of the elements currentluy in the queue
    private ArrayList<Solution> queue;

    //constructor
    public ArrayListSolutionQueue() {
        queue = new ArrayList<Solution>(); 
    }
    public void enqueue(Solution value) {
        queue.add(value);
    }

    public Solution dequeue() {
        return queue.remove(0);      
    }
    public boolean isEmpty() {
        if (queue.size() == 0){
            return true;
        }
        return false;
    }

}
