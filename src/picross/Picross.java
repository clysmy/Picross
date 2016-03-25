/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picross;

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
        
        PicrossBoard game = new PicrossBoard( "src\\picross\\board1.txt" );
        
        while(true)
        {
            Scanner sc = new Scanner(System.in);
            game.printBoard();
            game.printHints();
            
            System.out.println("What would you like to do?");
            System.out.println(" (1) Add fill");
            System.out.println(" (2) Add Mark");
            System.out.println(" (3) Remove");
            
            System.out.println(" (0) Exit");
            int choice = 0;
           
            choice = sc.nextInt(); 

            System.out.println(choice);
            int i = 0;
            int j = 0;

            if(choice != 0)
            {
                System.out.print("Please enter i number: ");
                i = sc.nextInt(); 

                System.out.print("Please enter j number: ");
                j = sc.nextInt(); 

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
        }
        System.out.println("Thanks for playing!");
    }
    
}
