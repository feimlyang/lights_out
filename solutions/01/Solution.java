      
// ArrayList utils is not allowed in this solution
public class Solution {
    private boolean[][] board;
    private boolean[][] solution;
    private int width;
    private int height;
    private int m; //current height postion  
    private int n; //current width postion 

    public Solution(int width, int height) {
        //creates an instance of Solution for a board of size widthxheight. 
        //That solution does not have any board position value explicitly specified yet.
        this.width = width;
        this.height = height;
        this.board = new boolean [height][width];
        this.solution = new boolean [height][width];
        this.m = 0;
        this.n = 0;
    }

    public Solution(Solution other) {
        //creates an instance of Solution which is the same as the one received as a parameter.
        //It will be for a board of the same dimension, and the same board positions are specified, with the same values. 
        //In other words, the new instance is a deep copy of the instance other.

        this(other.width, other.height);
        this.m = other.m;
        this.n = other.n;

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                this.board[i][j] = other.board[i][j];
                this.solution[i][j] = other.solution[i][j];
            }
        }     
    }

    public boolean equals(Object other){
        //returns true if and only the parameter other is referencing an instance of a Solution 
        //which is the “same” as this instance of Solution
        //Make sure that your implementation is as reliable as possible and handle all possible cases1.
        if (!(other instanceof Solution)){
            return false;
        }
        Solution o = (Solution)other;

        if (height != o.height || width != o.width){
            return false;
        }

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (board[i][j] != o.board[i][j]){
                    return false;
                }
            }
        }
        return true;
        
    }

    public boolean isReady(){
        //returns true if the solution has been entirely specified, false otherwise.
        if (m == height && n == 0){
            return true;
        }
        return false;

    }

    public void setNext(boolean nextValue) {
        //specifies the “next” value of the solution to nextValue.
        //If setNext is called more times than there are positions on the board, 
        //an error message is printed out and the call is ignored.
/*
//test
System.out.println(m);
System.out.println(n);
*/

        if (m <= height-1){
            solution[m][n] = nextValue;
            
            if (nextValue == true){    
                board[m][n] = !(board[m][n]);

                if (m == 0){
                    board[m+1][n] = !(board[m+1][n]);
                    
                    if (n == 0){
                        board[m][n+1] = !(board[m][n+1]);
                    }
                    else if (n == width-1){
                        board[m][n-1] = !(board[m][n-1]); 
                    }
                    else{
                        board[m][n-1] = !(board[m][n-1]);
                        board[m][n+1] = !(board[m][n+1]);
                    }
                }

                else if (m == height-1){
                    board[m-1][n] = !(board[m-1][n]);

                    if (n == 0){
                        board[m][n+1] = !(board[m][n+1]);
                    }
                    else if (n == width-1){
                        board[m][n-1] = !(board[m][n-1]);
                    }
                    else{
                        board[m][n-1] = !(board[m][n-1]);
                        board[m][n+1] = !(board[m][n+1]);
                    }
                }

                else{
                    board[m+1][n] = !(board[m+1][n]);
                    board[m-1][n] = !(board[m-1][n]);

                    if (n == 0){
                        board[m][n+1] = !(board[m][n+1]);
                    }
                    else if (n == width-1){
                        board[m][n-1] = !(board[m][n-1]);
                    }
                    else{
                        board[m][n-1] = !(board[m][n-1]);
                        board[m][n+1] = !(board[m][n+1]);
                    }
                }
            }

/*
// test board value 
String myString = "";

myString += "[";
for (int i = 0; i < height; i++){
    myString += "[";
    for (int j = 0; j < width; j++){
        if (board[i][j] == true){
            myString += "true";
        }
        else{
            myString += "false";
        }

        if (j != width-1){
            myString += ",";
        }
    }
    myString += "]";
    if (i != height-1){
        myString += ",";
        myString += "\n";
    }
}
myString += "]";
System.out.println(myString);
*/

            if (n < width-1){
                n++;
            }
            else if (n == width-1){
                n = 0;
                m++;
            }

        }

        else{
            System.out.println("out of board! this call is ignored.");

        }
        
    }
    
    public boolean isSuccessful(){
        //returns true if the solution is completely specified and is indeed working
        //it will bring a board of the specified dimensions from being entirely “off” to being entirely “on”.
        if (isReady() == false){
            return false;
        }
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (board[i][j] == false){
                    return false;
                }
            }
        }
        return true;

    }

    public String toString() {
        //returns a String representation of the solution
        String myString = "";
        
        myString += "[";
        for (int i = 0; i < height; i++){
            myString += "[";
            for (int j = 0; j < width; j++){
                if (solution[i][j] == true){
                    myString += "true";
                }
                else{
                    myString += "false";
                }

                if (j != width-1){
                    myString += ",";
                }
            }
            myString += "]";
            if (i != height-1){
                myString += ",";
                myString += "\n";
            }
        }
        myString += "]";
        return myString;
  
    }

}
