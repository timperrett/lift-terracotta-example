package bootstrap.liftweb

import net.liftweb.http.{LiftRules, NotFoundAsTemplate, ParsePath}
import net.liftweb.sitemap.{SiteMap, Menu, Loc}

class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("eu.getintheloop")
    
    // build sitemap
    val entries = List(Menu("Home") / "index") ::: Nil
    
    LiftRules.setSiteMap(SiteMap(entries:_*))
  }
}