public class Test {

public static void main ( String [ ] args ) {

System.out.println("Test1");	
Solution solution ;
solution = new Solution ( 3 , 2 ) ;
solution.setNext ( true ) ;
solution.setNext ( true ) ;
solution.setNext ( false ) ;
solution.setNext ( true ) ;
solution.setNext ( true ) ;
solution.setNext ( false ) ;
//solution.setNext (true); //error message
System.out.println ( "Solution is ready: " + solution.isReady()) ;
System.out.println ( "The solution is:" ) ;
System.out.println ( solution ) ;
System.out.println ( "Solution is successful: " + solution.isSuccessful() ) ;

System.out.println("Test2");
solution = new Solution ( 3 , 2 ) ;
solution.setNext ( true ) ;
solution.setNext ( false ) ;
solution.setNext ( true ) ;
System.out.println ( "Midway - Solution is ready: " + solution.isReady() ) ;
solution.setNext ( false) ;
solution.setNext ( true ) ;
solution.setNext ( false ) ;
System.out.println ( "Solution is ready: " + solution.isReady() ) ;
System.out.println ( "The solution is:" ) ;
System.out.println ( solution ) ;
System.out.println ( "Solution is successful: " + solution.isSuccessful() ) ;

}


}