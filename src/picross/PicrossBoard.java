/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picross;

import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Clyde
 */

// array of ints?
// 0 for empty
// 1 for filled
// addFilled (check if mark
// remove (for both filled and mark) - set [i,j] to 0
// 
public class PicrossBoard {
    private int solution[][];
    private int board[][];
    private int row = 10;
    private int col = 10;
    private int fillCount = 0;
    private int fill = 0;
    
    PicrossBoard()
    {
        // read file
        // just 10 x 10 boards for now
        // separated by space?
        solution = new int[10][10];
        board = new int[10][10];
        buildBoards(); // initialize boards
    }
    
    PicrossBoard(String fileName)
    {
        this(); // call default constructor
        readFile(fileName);
    }
    
    
    void readFile(String fileName)
    {
        FileReader fr;
        Scanner inFile;
        try
        {
            fr = new FileReader(fileName);
            inFile = new Scanner(fr);
             
            for(int i = 0; i < row; i++)
            {
                for(int j = 0; j < col; j++)
                {
                    solution[i][j] = inFile.nextInt();
                    if(solution[i][j] == 1)
                        fill++;
                }
            }
            
            inFile.close();
        }catch(Exception e)
        {
            System.err.println("Unable to read file");
            System.exit(-1);
        }
    }
    
    // initializes the board that the user interacts with
    void buildBoards()
    {
        for( int i = 0; i < row; i++ )
        {
            for( int j = 0; j < col; j++ )
            {
                board[i][j] = 0;
                solution[i][j] = 0;
            }
        }
    }
    
    // no matter what, change space to empty ( 0 )
    void remove( int i, int j )
    {
        if(board[i][j] == 1)
            fillCount--;
        
        board[i][j] = 0;
    }
    
    // check bool
    // true for fill
    // false for mark
    // for my implementation, no need to check
    // [i][j] element, just replace it
    boolean add( int i, int j, boolean state )
    {
        if( board[i][j] == 1 )
                fillCount--;
        
        if( state )
        {
            board[i][j] = 1;
            fillCount++;
        }
        else
        {
            board[i][j] = 2;
        }
        
        if( fillCount == fill )
            return checkSolution();
        else
            return false;
        
    } 
    
    
    // loop through and compare solution to board
    // if same, return true
    // else return false
    boolean checkSolution()
    {
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(solution[i][j] != board[i][j])
                    return false;
            }
        }
        
        return true;
    }
    
    void printBoard()
    {
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if(board[i][j] == 1)
                    System.out.print( "O " );
                else if (board[i][j] == 2)
                    System.out.print( "X " );
                else
                    System.out.print( "  " );
            }
            System.out.println();
        }
    }
}
