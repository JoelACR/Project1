package towson.edu.cosc455.jchava1

/**
  * Created by Joel on 10/11/2016.
  */

trait LexicalAnalyzer {
  //var char : String = ""u
  def addChar() : Unit
  def getChar() : Char
  def getNextToken() : Unit
}
