
package views.html

object hello extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](_display_(/*1.2*/main("Hello")/*1.15*/ {_display_(Seq[Any](format.raw/*1.17*/("""
    """),format.raw/*2.5*/("""<section id="top">
        <div class="wrapper">
            <h1>Hello World</h1>
        </div>
    </section>
""")))}))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Nov 19 20:11:22 CET 2018
                  SOURCE: /home/archie/Programming/Java/acoes-admin/app/views/hello.scala.html
                  HASH: 199ddf100dec5e2a5aa98d9c0a0f76e68530396e
                  MATRIX: 1049->1|1070->14|1109->16|1140->21
                  LINES: 34->1|34->1|34->1|35->2
                  -- GENERATED --
              */
          