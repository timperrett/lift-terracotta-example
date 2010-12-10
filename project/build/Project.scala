import sbt._

class LiftHazzleCastSampleProject(info: ProjectInfo) extends DefaultWebProject(info) {
  // deployment
  override def managedStyle = ManagedStyle.Maven
  override def jettyWebappPath = webappPath 
  override def scanDirectories = Nil 
  
  // setup jetty port from sys properties
  // so we can run two seperate servers to simulate
  // the "failure" of a node
  
  lazy val actualJettyPort = {
    //println(System.getProperty("jetty.port"))
    System.getProperty("jetty.port") match {
      case v if(v != null) => v.toInt
      case _ => 8080
    }
  }
  
  override def jettyPort = actualJettyPort
  // no scala 2.8, so cant do:
  //Option(System.getProperty("jetty.port")).map(_.toInt) getOrElse 8080
  
  // dependencies
  val webkit = "net.liftweb" %% "lift-webkit" % "2.3-SNAPSHOT" % "compile->default"
  
  // environment
  val jetty6 = "org.mortbay.jetty" % "jetty" % "6.1.21" % "test"
  val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided"
  
  val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"
  val scalatoolsSnapshot = "Scala Tools Snapshot" at "http://scala-tools.org/repo-snapshots/"
}
