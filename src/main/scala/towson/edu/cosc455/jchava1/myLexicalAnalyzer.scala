package towson.edu.cosc455.jchava1

/**
  * Created by Joel on 10/11/2016.
  */
class myLexicalAnalyzer extends LexicalAnalyzer{

  var isSpace = false
  var nextChar: Char = ' '
  var lexeme = List()
  var lexLength: Int = 0
  var position: Int = 0
  var lexems = List(CONSTANTS.DOCB, CONSTANTS.DOCE,CONSTANTS.TITLEB,
    CONSTANTS.BRACKETE,CONSTANTS.HEADING,CONSTANTS.PARAB,CONSTANTS.PARAE,
    CONSTANTS.BOLD,CONSTANTS.ITALICS,CONSTANTS.LISTITEM,CONSTANTS.NEWLINE,
    CONSTANTS.LINKB,CONSTANTS.ADDRESSB,CONSTANTS.ADDRESSE,CONSTANTS.IMAGEB,
    CONSTANTS.DEFB,CONSTANTS.EQSIGN,CONSTANTS.USEB)


  override def getChar(): Char =
  {
    if(position < compiler.file.length())
        nextChar = compiler.file.charAt(position +1)
        return nextChar

  }
  override def addChar(): Unit =
  {

  }
  override def getNextToken(): Unit =
  {
    var nextToken: String = " "
    nonBlank()

    if (nextChar =='#' || nextChar=='+' || nextChar=='[' || nextChar== ']'
      || nextChar == '*' || nextChar=='!' || nextChar=='\\')
    {
      compiler.isText = false
      addChar()

      while(!isPace(nextChar) || nextChar != '\n')
        {
          getChar()
          addChar()
        }

      nextToken = lexeme.mkString

      if(lookup(nextToken))
        {
          compiler.currentToken = nextToken
        }

      while(position < compiler.file.length)
      {
        getNextToken()
      }
    }
    else if(nextChar == '.' || nextChar == ',' || nextChar == ':'
    || nextChar == "[a-zA-z0-9]")
      {
        compiler.isText = true
        while(!isPace(nextChar) || nextChar != '\n')
        {
          getChar()
          addChar()
        }
        compiler.currentToken = nextToken

        while(position < compiler.file.length)
        {
          getNextToken()
        }
      }
    else
      {
        println("Lexical error detected, '" + nextChar + "' is invalid lexem" +
          "program will now exit")
        System.exit(1)
      }

  }

  def isPace(c: Char): Boolean =
  {
    return c == ' '
  }

  def nonBlank(): Unit =
  {
    while(isPace(nextChar))
      {
        getChar()
      }
  }

  def lookup(token: String): Boolean =
  {
    if(!lexems.contains(token))
      {
        println("Lexical error detected, '" +token+ "' is not recognized" +
        "program will now exit")
        return false
      }
    return true
  }

}
