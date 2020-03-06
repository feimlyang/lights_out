
import java.util.ArrayList;

public class QueueImplementation<E> implements Queue<E> {

	private ArrayList<E> queue;

	public QueueImplementation(){
		queue = new ArrayList<E>();
	}

    //return if the queue is empty
    public boolean isEmpty(){
    	return queue.isEmpty();

    }
    

    //add elem to the queue
    public void enqueue(E newElement){

	    if(newElement == null) {
	        throw new NullPointerException("can enqueue a null reference");
        }
        queue.add(newElement);  
    }

    //return and remove the first elme in the queue
    public E dequeue(){
	    if(isEmpty()) {
	    	throw new IllegalStateException("call to dequeue on an empty queue... can't make it!");
        }
        return queue.remove(0);
    }
}
