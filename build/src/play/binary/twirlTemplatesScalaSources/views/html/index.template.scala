
package views.html

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /*
 * This template takes a single argument, a String containing a
 * message to display.
 */
  def apply/*5.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.19*/("""

"""),format.raw/*11.4*/("""
"""),_display_(/*12.2*/main("Welcome to Play")/*12.25*/ {_display_(Seq[Any](format.raw/*12.27*/("""

    """),format.raw/*17.8*/("""
    """),_display_(/*18.6*/welcome(message, style = "java")),format.raw/*18.38*/("""

""")))}),format.raw/*20.2*/("""
"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Nov 19 20:11:21 CET 2018
                  SOURCE: /home/archie/Programming/Java/acoes-admin/app/views/index.scala.html
                  HASH: 7f6af6d6f7b749fb8955e6f8b3fb99e81fc01e13
                  MATRIX: 1056->95|1168->112|1197->308|1225->310|1257->333|1297->335|1330->464|1362->470|1415->502|1448->505
                  LINES: 32->5|37->5|39->11|40->12|40->12|40->12|42->17|43->18|43->18|45->20
                  -- GENERATED --
              */
          