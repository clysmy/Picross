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
    private int rowHints[][];
    private int colHints[][];
    private int row = 10;
    private int rowHintMax = 5;
    private int col = 10;
    private int colHintMax = 5;
    private int fillCount = 0;
    private int fill = 0;
    
    /*PicrossBoard()
    {
        // read file
        // just 10 x 10 boards for now
        // separated by space?
        //solution = new int[10][10];
        //board = new int[10][10];
        //rowHints = new int[10][5];
        //colHints = new int[10][5];
        buildBoards(); // initialize boards
    }*/
    
    PicrossBoard( String fileName )
    {
        //this(); // call default constructor
        readFile( fileName );
        fillHints();
    }
    
    // hints for 10x10 is at most five in a line (1 1 1 1 1)
    void fillHints()
    {
        int hint = 0;
        int hintNum = 0;
        
        // fill row hints first
        for( int i = 0; i < row; i++ )
        {
            for( int j = 0; j < col; j++ )
            {
                // loop until there is a mark, then count adjacent ones
                if( solution[i][j] == 1 )
                    hint++;
                else
                {
                    // otherwise no mark
                    if( hintNum < rowHintMax && hint > 0)
                    {
                        // check if currently in a mark sequence
                        rowHints[i][hintNum] = hint;
                        hintNum++;
                        hint = 0;
                    }
                    
                }
            }
            if( hint > 0 )
            {
                rowHints[i][hintNum] = hint;
            }
            hintNum = 0;
            hint = 0;
        }
        
        hint = 0;
        hintNum = 0;
        
        // fill col hints
        for( int j = 0; j < col; j++ )
        {
            for( int i = 0; i < row; i++ )
            {
                if( solution[i][j] == 1 )
                    hint++;
                else
                {
                    if( hintNum < colHintMax && hint > 0)
                    {
                        colHints[j][hintNum] = hint;
                        hintNum++;
                        hint = 0;
                    }
                    
                }
            }
            if( hint > 0 )
            {
                colHints[j][hintNum] = hint;
            }
            hintNum = 0;
            hint = 0;
        }
    }
    
    void printHints()
    {
        System.out.println( "Rows" );
        for( int i = 0; i < row; i++ )
        {
            for( int j = 0; j < rowHintMax; j++ )
            {
                if( rowHints[i][j] != 0 )
                    System.out.print(rowHints[i][j] );
            }
            if( rowHints[i][0] == 0 && rowHints[i][rowHintMax - 1] == 0 )
                System.out.print( 0 );
            
            System.out.println();
        }
        
        System.out.println( "Columns" );
        for( int i = 0; i < col; i++ )
        {
            for( int j = 0; j < colHintMax; j++)
            {
                if( colHints[i][j] != 0 )
                    System.out.print( colHints[i][j] );
            }
            if( colHints[i][0] == 0 && colHints[i][colHintMax - 1] == 0 )
                System.out.print( 0 );
            
            System.out.print( " " );
        }
        System.out.println();
        
    }
    
    // file structure
    // first two numbers: ROW# COL#
    // afterwards, the rest is the board
    void readFile( String fileName )
    {
        FileReader fr;
        Scanner inFile;
        try
        {
            fr = new FileReader( fileName );
            inFile = new Scanner( fr );
            
            row = inFile.nextInt();
            col = inFile.nextInt();
            
            // with row and col read, initialize arrays
            solution = new int[row][col];
            board = new int[row][col];
            
            buildBoards();
            
            if( row % 2 == 0 )
                rowHintMax = row / 2;
            else
                rowHintMax = ( row / 2 ) + 1;
            
            if( col % 2 == 0 )
                colHintMax = col / 2;
            else
                colHintMax = ( col / 2 ) + 1;
            
            rowHints = new int[row][rowHintMax];
            colHints = new int[col][colHintMax];
            buildHints();
            
            // begin reading board solution
            for( int i = 0; i < row; i++ )
            {
                for( int j = 0; j < col; j++ )
                {
                    solution[i][j] = inFile.nextInt();
                    if( solution[i][j] == 1 )
                        fill++;
                }
            }
            
            inFile.close();
        }catch( Exception e )
        {
            System.err.println( "Unable to read file" );
            System.exit( -1 );
        }
    }
    
    // initializes both boards that the user interacts with
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
    
    // initializes both hint lines
    void buildHints()
    {
        for( int i = 0; i < row; i++ )
        {
            for( int j = 0; j < rowHintMax; j++ )
            {
                rowHints[i][j] = 0;
            }
        }
        
        for( int i = 0; i < col; i++ )
        {
            for( int j = 0; j < colHintMax; j++ )
            {
                colHints[i][j] = 0;
            }
        }
    }
    
    // no matter what, change space to empty ( 0 )
    void remove( int i, int j )
    {
        if( board[i][j] == 1 )
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
        for( int i = 0; i < row; i++ )
        {
            for( int j = 0; j < col; j++ )
            {
                if( solution[i][j] != board[i][j] )
                    return false;
            }
        }
        
        return true;
    }
    
    void printBoard()
    {
        for( int i = 0; i < row; i++ )
        {
            for( int j = 0; j < col; j++ )
            {
                if( board[i][j] == 1 )
                    System.out.print( "O " );
                else if ( board[i][j] == 2 )
                    System.out.print( "X " );
                else
                    System.out.print( "  " );
            }
            System.out.println();
        }
    }
}
