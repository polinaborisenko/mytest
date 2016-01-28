package my.company.tests;

//import java.io.File;

public class Calculator {
    private int first;
    private int second;

    public Calculator() {
       this.first = this.second = 0;
    }
    
    public Calculator(int first, int second) {
       this.first = first;
       this.second = second;
    }

    public int getSubResult(){
       return first - second;
    }    

    public int getAddResult(){
       return first + second;
    }    
    public int getMulResult(){
       return first * second;
    }    
    public int getDivResult(){
	int res = 0;
	try {
		 res = first / second;
	}
	catch (ArithmeticException ae) {
        	System.out.println("ArithmeticException occured!");
	}
	return res;
    }
}
