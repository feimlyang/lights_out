

//The interface SolutionQueue is a specialized Queue which handles instances of Solution

public interface SolutionQueue {

    boolean isEmpty();
    //Returns true if the Queue is currenly empty

    void enqueue(Solution s);
    //Add the reference to Solution at the rear of the queue.
    //Assumes s is not null. 
    // Param s: The (non null) reference to the new element

    Solution dequeue();
    //Removes the reference to Solution at the front of the queue.
    //Assumes the queue is not empty. 
    //returns The reference to removed Solution

}
