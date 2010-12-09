package eu.getintheloop.snippet

import net.liftweb.common.{Full,Empty}
import scala.xml.{NodeSeq,Text}
import net.liftweb.http.{DispatchSnippet,ContainerVar,ContainerSerializer,SHtml,S}
import net.liftweb.util.Helpers._

class Words extends DispatchSnippet {
  // object WordHolder extends ContainerVar[String]("n/a")
  
  def dispatch = {
    case "update" => update _
    case "show" => show _
  }
  def update(xhtml: NodeSeq): NodeSeq = bind("f",xhtml,
    "word" -> SHtml.text(get, set(_)),
    "submit" -> SHtml.submit("Update >>", () => println("Submitted!"))
  )
  
  def show(xhtml: NodeSeq): NodeSeq = Text(get)
  
  private def set(what: String){
    S.containerSession.map(_.setAttribute("word", ContainerSerializer.stringSerializer.serialize(what)))
  }
  
  private def get: String = {
    S.containerSession.map { s => 
      s.attribute("word").asInstanceOf[Array[Byte]] match {
        case a: Array[Byte] if a.length > 0 => 
          new String(ContainerSerializer.stringSerializer.deserialize(a))
        case _ => "empty"
      }
    } openOr "dummy"
  }
  
  // private def set(what: String){
  //   S.containerSession.map(_.setAttribute("word", what.getBytes("UTF-8")))
  // }
  // 
  // private def get: String = 
  //   new String().openOr(Array[Byte]()))
}
