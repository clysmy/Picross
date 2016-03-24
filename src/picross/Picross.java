/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picross;

import java.io.Console;
import java.util.Scanner;
/**
 *
 * @author Clyde
 */
public class Picross {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        PicrossBoard game = new PicrossBoard("C:\\Users\\Clyde\\Documents\\NetBeansProjects\\Picross\\src\\picross\\board1.txt");
        //game.printBoard();
        while(true)
        {
            Scanner sc = new Scanner(System.in);
            game.printBoard();
            //InputStreamReader cin = new InputStreamReader(System.in);
            System.out.println("What would you like to do?");
            System.out.println(" (1) Add fill");
            System.out.println(" (2) Add Mark");
            System.out.println(" (3) Remove");
            //System.out.println(" (3) Print Board");
            System.out.println(" (0) Exit");
            int choice = 0;
           // try{
                choice = sc.nextInt(); //System.in.read();
                //choice = choice - 48;
                System.out.println(choice);
                int i = 0;
                int j = 0;
                
                if(choice != 0)
                {
                    System.out.print("Please enter i number: ");
                    i = sc.nextInt(); //System.in.read();
                    //i = i - 48;
                    System.out.print("Please enter j number: ");
                    j = sc.nextInt(); //System.in.read();
                    //j = j - 48;
                   
                    if(choice == 1)
                    {
                        if(game.add(i, j, true))
                        {
                            System.out.println("Success!");
                            game.printBoard();
                        }      
                    }
                    else if(choice == 2)
                    {
                        game.add(i, j, false);
                    }
                    else
                    {
                        game.remove(i, j);
                    }
                }
                else
                {
                    break;
                } 
           // }catch(Exception e)
            //{
          //      System.err.println("Unable to read input");
           //     System.exit(-1);
           // }
        }
        System.out.println("Thanks for playing!");
    }
    
}
