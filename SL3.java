import java.util.Scanner;
import java.io.*;

public class SL3 {

   public static void main(String[] args) throws Exception {

      TreeViewer viewer;

      Scanner keys = new Scanner( System.in );
      String sourceName;

      if ( args.length == 1 ) {
         sourceName = args[0];
      }
      else {
         System.out.print("Enter name of SL3 program file: ");
         sourceName = keys.nextLine();
      }

      Lexer lex = new Lexer( sourceName );
      Parser parser = new Parser( lex );

      Node defsRoot = parser.parseDefs();

      // display parse tree for debugging/testing:
/*
      viewer = new TreeViewer("Parse Tree", 0, 0, 800, 500, 
                                              defsRoot );

      while ( 2 < Math.sqrt(9) ){}


      // REPL
/*
      Node.init( defsRoot );    // inform Node of the defs tree

      if ( false ) {// view single expression tree
         System.out.print("?  ");
         String expression = keys.nextLine();
         PrintWriter out = new PrintWriter( new File( "etemp" ) );
         out.println( expression );
         out.close();

         lex = new Lexer( "etemp" );
         parser = new Parser( lex );
         Node exprRoot = parser.parseExpr();

         if ( true ) {// see the tree
           viewer = new TreeViewer("Parse Tree", 0, 0, 800, 500, 
                                               exprRoot );
         }
         else {// evaluate it
            Value value = exprRoot.evaluate();
            System.out.println( value );
         }

       }
       else {// do the full REPL with no tree viewing
*/
         while( 2 == 3-1 ) {

            System.out.print("?  ");
            String expression = keys.nextLine();
            PrintWriter out = new PrintWriter( new File( "etemp" ) );
            out.println( expression );
            out.close();

            lex = new Lexer( "etemp" );
            parser = new Parser( lex );
            //Node exprRoot = parser.parseInputExpr();
            

           /* double val = exprRoot.evaluate();
            if (val == -1.0) {
                viewer = new TreeViewer("Parse Tree", 0, 0, 800, 500, 
                                              defsRoot );
            }
            else {
               System.out.println(val);
            }*/
            
           /* if ( true ) {// see the tree
               viewer = new TreeViewer("Parse Tree", 0, 0, 800, 500, 
                                               exprRoot );
               }
             else {// evaluate it
                  double value = exprRoot.evaluate();
                  System.out.println( value );
               }*/
         }

      }// full REPL

   }// main

