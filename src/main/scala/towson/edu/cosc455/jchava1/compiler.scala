package towson.edu.cosc455.jchava1
/**
  * Created by Joel on 10/11/2016.
  */
import scala.io.Source._
import java.awt.Desktop
import java.io.{File, IOException}

object compiler {
  var file : String = ""
  var currentToken : String = ""
  var isText : Boolean = false
  val scanner = new myLexicalAnalyzer
  val parser = new mySyntaxAnalyzer
  val semAna = new mySemanticAnalyzer

  def main(args: Array[String]) =
  {
    // check correct usage
    checkFile(args)
    readFile(args(0))

    scanner.getNextToken()
    parser.gittex()
    //semAna.
    openHTMLFileInBrowser(file)
  }

  def readFile(fileContents : String) =
  {
    val source = scala.io.Source.fromFile(file)
    file = try source.mkString finally source.close()
  }

  def checkFile(args : Array[String]) = {
    if (args.length != 1) {
      println("USAGE ERROR: wrong number of args. Program will now exit.")
      System.exit(1)
    }
    else if (! args(0).endsWith(".mkd")) {
      println("USAGE ERROR: wrong extension used. Program will now exit.")
      System.exit(1)
    }
  }

  def openHTMLFileInBrowser(htmlFileStr : String) = {
    val file : File = new File(htmlFileStr.trim)
    println(file.getAbsolutePath)
    if (!file.exists())
      sys.error("File " + htmlFileStr + " does not exist.")

    try {
      Desktop.getDesktop.browse(file.toURI)
    }
    catch {
      case ioe: IOException => sys.error("Failed to open file:  " + htmlFileStr)
      case e: Exception => sys.error("He's dead, Jim!")
    }
  }

}
