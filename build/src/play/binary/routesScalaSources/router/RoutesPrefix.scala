// @GENERATOR:play-routes-compiler
// @SOURCE:/home/archie/Programming/Java/acoes-admin/conf/routes
// @DATE:Mon Nov 19 20:11:17 CET 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
