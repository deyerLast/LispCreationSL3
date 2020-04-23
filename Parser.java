
import java.util.*;
import java.io.*;

public class Parser{
   private Lexer lex;
   
   public Parser( Lexer lexer){
      lex = lexer;
   }
   
   public Node parseDefs(){
      System.out.println("---> parsing <defs>:");
      Node first = parseDef();
      Token token = lex.getNextToken();
      if( token.isKind("eof") ){
         return new Node( "defs",first,null,null);
      }
      else{
         lex.putBackToken( token );
         Node second = parseDefs();
         return new Node("defs",first,second,null);
      }
   }// parseDefs
   
   
   
   
   public Node parseDef(){
      System.out.println("---> parsing <def>:");
      
      Token token = lex.getNextToken();
      errorCheck(token, "lparen");
      
      token = lex.getNextToken();
      errorCheck(token, "define");
      
      token = lex.getNextToken();
      errorCheck(token,"lparen");
      
      Token name = lex.getNextToken();
      errorCheck(name,"name");                                     //???????????????
      
      token = lex.getNextToken();
      if( token.isKind("rparen") ){
         //lex.putBackToken( token ); //it's ")"
         Node first = parseExpr();
         token = lex.getNextToken();
         errorCheck(token, "rparen");
         return new Node("def",name.getDetails(),first,null,null);        ///what's wrong?
      }
      else{
         System.out.println("                                  R22222222222222222222222K");

         
         lex.putBackToken( token );
         Node first = parseParams();
         
         token = lex.getNextToken();
 System.out.println("                                  R22222222222222222222222K 2 " + token);        
         errorCheck(token,"rparen");
         
         Node second = parseExpr();
       
         token = lex.getNextToken();
System.out.println("                                  R22222222222222222222222K 3 " + token);            
         errorCheck(token,"rparen");
         
         return new Node("def",name.getDetails(),first,second,null);         ///issue what's wrong
      }      
   }// parseDef
   
   
   
   
   
  
   
   
   private Node parseParams(){
      Token name = lex.getNextToken();
      errorCheck(name,"name");//should be type "name"
      
      Token token = lex.getNextToken();
      if( !token.isKind( "rparen" ) ){
         lex.putBackToken( token );
         Node first = parseParams();
         return new Node("params",name.getDetails(),first,null,null);// NAME <params>
      }
      else{
         lex.putBackToken( token );
         return new Node("params", name.getDetails(),null,null,null);// NAME
      }
   }// parseParams()
   
   
 
   
   
   
   
   private Node parseExpr(){
      Token t1 = lex.getNextToken();
      if( t1.isKind("name") || t1.isKind("number") ){
         //lex.putBackToken( t1 );
         return new Node("expr",t1.getDetails(),null,null,null);
      }
      lex.putBackToken(t1);
      Node first = parseList();
      return new Node("expr",first,null,null);
      
   }// parseExpr()
   
   
     
   
    
   
   
   private Node parseList(){
      Token token = lex.getNextToken();
      errorCheck(token,"lparen");
      
      token = lex.getNextToken();
      if( token.isKind("rparen") ){
         errorCheck(token,"rparen");
         return new Node("list",null,null,null);        // ()                      // Is this correct?
      }
      lex.putBackToken(token);
      
      Node first = parseItems();
      
      token = lex.getNextToken();
      errorCheck(token,"rparen");
      
      return new Node("list",first,null,null);
   }// parseList()
   
   
   
   
   
   
   
   
   
   
   
   private Node parseItems(){
      Node first = parseExpr();
      
      Token token = lex.getNextToken();
      if( !token.isKind("rparen") ){
         lex.putBackToken(token);
         Node second = parseItems();
         return new Node("items",first,second,null);
      }
      lex.putBackToken(token);
      return new Node("items",first, null, null);
   }
   
   
   
   
   
   
   
   
   
   
   // check whether token is correct kind
  private void errorCheck( Token token, String kind ) {
    if( ! token.isKind( kind ) ) {
      System.out.println("Error:  expected " + token + 
                         " to be of kind " + kind );
      System.exit(1);
    }
  }

  // check whether token is correct kind and details
  private void errorCheck( Token token, String kind, String details ) {
    if( ! token.isKind( kind ) || 
        ! token.getDetails().equals( details ) ) {
      System.out.println("Error:  expected " + token + 
                          " to be kind= " + kind + 
                          " and details= " + details );
      System.exit(1);
    }
  }

   

}//End Class