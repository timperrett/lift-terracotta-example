package net.liftweb.sample.snippet

import scala.xml.{NodeSeq,Text}
import net.liftweb.util.Helpers._
import net.liftweb.http.{DispatchSnippet,SHtml,ContainerVar}

class Words extends DispatchSnippet {
  object WordHolder extends ContainerVar[String]("n/a")
  def dispatch = {
    case "update" => update _
    case "show" => show _
  }
  def update(xhtml: NodeSeq): NodeSeq = bind("f",xhtml,
    "word" -> SHtml.text(WordHolder.is, WordHolder(_)),
    "submit" -> SHtml.submit("Update >>", () => println("Submitted!"))
  )
  def show(xhtml: NodeSeq): NodeSeq = Text(WordHolder.is)
}


