package towson.edu.cosc455.jchava1

/**
  * Created by Joel on 10/11/2016.
  */
class mySyntaxAnalyzer extends SyntaxAnalyzer{

  var tree = List()

  override def gittex(): Unit =
  {
    if (compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)){
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
      variableDefine()
      title()
      body()
      if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE))
        {
          compiler.currentToken :: tree
        }
      else {
        println("Syntax ERROR - DOCE character was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
    }
    else {
      println("Syntax ERROR - DOCB character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def title(): Unit =
  {
    val title: String = CONSTANTS.TITLEB + CONSTANTS.BRACKETE
    if(compiler.currentToken.equalsIgnoreCase(title))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
    }
    else
      {
        println("Syntax ERROR - TITLE character was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
  }

  override def body(): Unit =
  {
    innerText()
    paragraph()
    newline()
  }

  override def paragraph(): Unit =
  {
    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAB))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
      variableDefine()
      innerText()
      if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAE))
      {
        compiler.currentToken :: tree
        compiler.scanner.getNextToken()
      }
      else
      {
        println("Syntax ERROR - PARAE character was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
    }
    else
    {
      println("Syntax ERROR - PARAB character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def innerText(): Unit =
  {
    variableUse()
    heading()
    bold()
    italics()
    listItem()
    image()
    link()
    if(compiler.isText == true)
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
    }
    else
    {
      println("Syntax ERROR - TEXT was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def heading(): Unit =
  {
    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.HEADING))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
      if(compiler.isText == true) {
        compiler.currentToken :: tree
        compiler.scanner.getNextToken()
      }
      else
      {
        println("Syntax ERROR - TEXT was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
    }
    else
    {
      println("Syntax ERROR - HEADING character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def variableDefine(): Unit =
  {
    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.DEFB))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
      if(compiler.isText == true)
      {
        compiler.currentToken :: tree
        compiler.scanner.getNextToken()
        if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.EQSIGN)) {
          compiler.currentToken :: tree
          compiler.scanner.getNextToken()
          if (compiler.isText == true) {
            compiler.currentToken :: tree
            compiler.scanner.getNextToken()
            if (compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE)) {
              compiler.currentToken :: tree
              compiler.scanner.getNextToken()
              variableDefine()
            }
            else {
              println("Syntax ERROR - BRACKETE character was expected" +
                "when '" + compiler.currentToken + "' was found." +
                "Program will now exit")
              System.exit(1)
            }
          }
          else {
            println("Syntax ERROR - TEXT was expected" +
              "when '" + compiler.currentToken + "' was found." +
              "Program will now exit")
            System.exit(1)
          }
        }
        else
        {
          println("Syntax ERROR - EQSING character was expected" +
            "when '" + compiler.currentToken + "' was found." +
            "Program will now exit")
          System.exit(1)
        }
      }
      else
      {
        println("Syntax ERROR - TEXT was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
    }
    else
    {
      println("Syntax ERROR - DEFB character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def variableUse(): Unit =
  {
    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
      if(compiler.isText == true) {
        compiler.currentToken :: tree
        compiler.scanner.getNextToken()
        if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE))
        {
          compiler.currentToken :: tree
          compiler.scanner.getNextToken()
        }
        else
        {
          println("Syntax ERROR - BRACKETE character was expected" +
            "when '" + compiler.currentToken + "' was found." +
            "Program will now exit")
          System.exit(1)
        }
      }
      else
      {
        println("Syntax ERROR - TEXT was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
    }
    else
    {
      println("Syntax ERROR - USEB character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def bold(): Unit =
  {
    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
      if(compiler.isText == true)
      {
        compiler.currentToken :: tree
        compiler.scanner.getNextToken
        if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
          compiler.currentToken :: tree
        }
        else
        {
          println("Syntax ERROR - BOLD character was expected" +
            "when '" + compiler.currentToken + "' was found." +
            "Program will now exit")
          System.exit(1)
        }
      }
      else
      {
        println("Syntax ERROR - TEXT was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
    }
    else
    {
      println("Syntax ERROR - BOLD character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def italics(): Unit =
  {
    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()

      if(compiler.isText == true)
      {
        compiler.currentToken :: tree
        compiler.scanner.getNextToken
        if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
          compiler.currentToken :: tree
        }
        else
        {
          println("Syntax ERROR - ITALICS character was expected" +
            "when '" + compiler.currentToken + "' was found." +
            "Program will now exit")
          System.exit(1)
        }
      }
      else
      {
        println("Syntax ERROR - TEXT was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
    }
    else
    {
      println("Syntax ERROR - ITALICS character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def listItem(): Unit =
  {

    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
      innerItem()
      listItem()
    }
    else
    {
      println("Syntax ERROR - LISTITEM character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def innerItem(): Unit = ???

  override def link(): Unit =
  {
    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
      if(compiler.isText == true)
      {
        compiler.currentToken :: tree
        compiler.scanner.getNextToken()
        if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE))
        {
          compiler.currentToken :: tree
          compiler.scanner.getNextToken()
          if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSB))
          {
            compiler.currentToken :: tree
            compiler.scanner.getNextToken()
            if(compiler.isText == true)
            {
              compiler.currentToken :: tree
              compiler.scanner.getNextToken()
              if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSE))
              {
                compiler.currentToken :: tree
                compiler.scanner.getNextToken()
              }
              else
              {
                println("Syntax ERROR - ADDRESSE character was expected" +
                  "when '" + compiler.currentToken + "' was found." +
                  "Program will now exit")
                System.exit(1)
              }
            }
            else
            {
              println("Syntax ERROR - TEXT was expected" +
                "when '" + compiler.currentToken + "' was found." +
                "Program will now exit")
              System.exit(1)
            }
          }
          else
          {
            println("Syntax ERROR - ADDRESSEB character was expected" +
              "when '" + compiler.currentToken + "' was found." +
              "Program will now exit")
            System.exit(1)
          }
        }
        else
        {
          println("Syntax ERROR - BRACKETE character was expected" +
            "when '" + compiler.currentToken + "' was found." +
            "Program will now exit")
          System.exit(1)
        }
      }
      else
      {
        println("Syntax ERROR - TEXT was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
    }
    else
    {
      println("Syntax ERROR - LINKB character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def image(): Unit =
  {
    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGEB))
    {
      compiler.currentToken :: tree
      compiler.scanner.getNextToken()
      if(compiler.isText == true)
      {
        compiler.currentToken :: tree
        compiler.scanner.getNextToken()
        if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.BRACKETE))
        {
          compiler.currentToken :: tree
          compiler.scanner.getNextToken()
          if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSB))
          {
            compiler.currentToken :: tree
            compiler.scanner.getNextToken()
            if(compiler.isText == true)
            {
              compiler.currentToken :: tree
              compiler.scanner.getNextToken()
              if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSE))
              {
                compiler.currentToken :: tree
                compiler.scanner.getNextToken()
              }
              else
              {
                println("Syntax ERROR - ADDRESSE character was expected" +
                  "when '" + compiler.currentToken + "' was found." +
                  "Program will now exit")
                System.exit(1)
              }
            }
            else
            {
              println("Syntax ERROR - TEXT was expected" +
                "when '" + compiler.currentToken + "' was found." +
                "Program will now exit")
              System.exit(1)
            }
          }
          else
          {
            println("Syntax ERROR - ADDRESSEB character was expected" +
              "when '" + compiler.currentToken + "' was found." +
              "Program will now exit")
            System.exit(1)
          }
        }
        else
        {
          println("Syntax ERROR - BRACKETE character was expected" +
            "when '" + compiler.currentToken + "' was found." +
            "Program will now exit")
          System.exit(1)
        }
      }
      else
      {
        println("Syntax ERROR - TEXT was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
    }
    else
    {
      println("Syntax ERROR - IMAGEB character was expected" +
        "when '" + compiler.currentToken + "' was found." +
        "Program will now exit")
      System.exit(1)
    }
  }

  override def newline(): Unit =
  {
    if(compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE))
      {
        compiler.currentToken :: tree
        compiler.scanner.getNextToken()
      }
    else
      {
        println("Syntax ERROR - NEWLINE character was expected" +
          "when '" + compiler.currentToken + "' was found." +
          "Program will now exit")
        System.exit(1)
      }
  }

}
