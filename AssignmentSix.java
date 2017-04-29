/**
 * Nick Tinsley
 * Assignment Six
 * April 25, 2017
 */
package assignmentsix;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Nick Tinsley
 */
public class AssignmentSix {

    /**
     * instantiate variables
     */
    int CITI;
    int[][] adjacency;
    int bestCost = Integer.MAX_VALUE;

    /**
     * Constructor with arguments.
     *
     * @param N : the number of cities in the tour
     */
    public AssignmentSix(int N) {
        CITI = N;
        adjacency = new int[N][N];
    }//TSP

    /**
     * This method is going to populate the two dimensional array with the files
     * loaded in the AssignmentSix folder. This is a symmetric matrix used for
     * calculating tour cost.
     */
    public void populateMatrix() {
        int value, i, j;
        File infile = new File("tsp" + CITI + ".txt");//calls the file
        try {
            Scanner input = new Scanner(infile);
            for (i = 0; i < CITI && input.hasNext(); i++) {
                for (j = i; j < CITI && input.hasNext(); j++) {
                    if (i == j) {
                        adjacency[i][j] = 0;//diagonal is 0
                    }//if
                    else {
                        value = input.nextInt();//cost at each position
                        adjacency[i][j] = value;
                        adjacency[j][i] = value;
                    }//else
                }//for
            }//for
        }//try
        catch (Exception e) {
            System.out.println(e);
        }//catch
    }//populateMatrix

    /**
     * This method checks each path to see what the minimum spanning tree for 
     * the number of cities is. This takes in the weights of all the edges.
     * 
     * @param N : number of cities being traveled
     */
    public void StackAlgorithm(int N) {
        //instantiate
        Stack<Integer> pathStack = new Stack<>();
        boolean[] visitedCities = new boolean[N];
        int closestCity;
        boolean minFlag;
        int currentCity;
        int min;
        int cost = 0;

        visitedCities[0] = true;//visited city 0
        pathStack.push(0);//push 0 on stack
        closestCity = 0;//closest city
        minFlag = false;
        System.out.print("0,");//starting city

        while (!pathStack.isEmpty()) {//not empty
            currentCity = pathStack.peek();//current is top of stack
            min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (adjacency[currentCity][i] != 0 && visitedCities[i] == false) {//not 0 and not visited
                    if (adjacency[currentCity][i] < min) {//distance less than min
                        min = adjacency[currentCity][i];
                        closestCity = i;
                        minFlag = true;
                    }//if
                }//if
            }//for
            if(minFlag){
                visitedCities[closestCity] = true;//visited
                pathStack.push(closestCity);//push to stack
                System.out.print(closestCity + ",");//output
                cost += min;
                minFlag = false;
            }//if
            pathStack.pop();//pop top element off stack
        }//while
        System.out.println("");
        System.out.println("Cost: " + cost);//output
    }//StackAlgorithm

    /**
     * This begins all execution.
     * 
     * @param args :command line arguments
     */
    public static void main(String[] args) {
        long start = System.nanoTime();//start 
        AssignmentSix test = new AssignmentSix(29);
        test.populateMatrix();
        test.StackAlgorithm(29);//run
        long elapsed = System.nanoTime() - start;//elapsed
        System.out.printf("Time: %3.10f\n", elapsed / (Math.pow(10, 9)));//time in seconds
    }//main   
}//class
