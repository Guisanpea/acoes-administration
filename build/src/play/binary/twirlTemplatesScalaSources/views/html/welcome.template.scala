
package views.html

import models._
import controllers._
import play.api.i18n._
import play.api.templates.PlayMagic._
import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.libs.F
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._
import views.html._

object welcome extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String, style: String = "java"):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.43*/("""

    """),_display_(/*3.6*/defining(play.core.PlayVersion.current)/*3.45*/ { version : String  =>_display_(Seq[Any](format.raw/*3.68*/("""

        """),format.raw/*5.9*/("""<section id="top">
            <div class="wrapper">
                <h1><a href="https://playframework.com/documentation/"""),_display_(/*7.71*/version),format.raw/*7.78*/("""/Home">"""),_display_(/*7.86*/message),format.raw/*7.93*/("""</a></h1>
            </div>
        </section>

        <div id="content" class="wrapper doc">
            <article>

                <h1>Welcome to Play</h1>

                <p>
                    Congratulations, you’ve just created a new Play application. This page will help you with the next few steps.
                </p>

                <blockquote>
                    <p>
                        You’re using Play """),_display_(/*22.44*/version),format.raw/*22.51*/("""
                    """),format.raw/*23.21*/("""</p>
                </blockquote>

                <h2>Why do you see this page?</h2>

                <p>
                    The <code>conf/routes</code> file defines a route that tells Play to invoke the <code>
                    HomeController.index</code> action
                    whenever a browser requests the <code>/</code> URI using the GET method:
                </p>

                <pre><code># Home page
                    GET     /               controllers.HomeController.index</code></pre>


                <p>
                    Play has invoked the <code>controllers.HomeController.index</code> method:
                </p>

                <pre><code>public Result index() """),format.raw/*42.50*/("""{"""),format.raw/*42.51*/("""
                    """),format.raw/*43.21*/("""return ok(index.render("Your new application is ready."));
                    """),format.raw/*44.21*/("""}"""),format.raw/*44.22*/("""</code></pre>

                <p>
                    An action method handles the incoming HTTP request, and returns the HTTP result to send back to the web client.
                    Here we send a <code>200 OK</code> response, using a template to fill its content.
                </p>

                <p>
                    The template is defined in the <code>app/views/index.scala.html</code>
                    file and compiled as a standard Java class.
                </p>

                <pre><code>@(message: String)

                    @main("Welcome to Play") """),format.raw/*58.47*/("""{"""),format.raw/*58.48*/("""

                    """),format.raw/*60.21*/("""@play20.welcome(message, style = "Java")

                    """),format.raw/*62.21*/("""}"""),format.raw/*62.22*/("""</code></pre>

                <p>
                    The first line of the template defines the function signature. Here it just takes a single <code>
                    String</code> parameter.
                    Then this template calls another function defined in <code>app/views/main.scala.html</code>
                    which displays the HTML layout, and another
                    function that displays this welcome message. You can freely add any HTML fragment mixed with Scala code in this file.
                </p>

                <blockquote>
                    <p>
                        <strong>Note</strong>
                        that Scala is fully compatible with Java, so if you don’t know Scala don’t panic, a Scala statement is very similar to a Java one.
                    </p>
                </blockquote>

                <p>You can read more about <a href="https://www.playframework.com/documentation/"""),_display_(/*79.98*/version),format.raw/*79.105*/("""/ScalaTemplates">
                    Twirl</a>
                    , the template language used by Play, and how Play handles <a href="https://www.playframework.com/documentation/"""),_display_(/*81.134*/version),format.raw/*81.141*/("""/JavaActions">
                        actions</a>.</p>

                <h2>Async Controller</h2>

                Now that you've seen how Play renders a page, take a look at <code>AsyncController.java</code>
                , which shows how to do asynchronous programming when handling a request.  The code is almost exactly the same as <code>
                HomeController.java</code>, but instead of returning <code>Result</code>, the action returns <code>
                CompletionStage&lt;Result&gt;</code>
                to Play.  When the execution completes, Play can use a thread to render the result without blocking the thread in the mean time.

                <p>
                    You can read more about <a href="https://www.playframework.com/documentation/"""),_display_(/*93.99*/version),format.raw/*93.106*/("""/JavaAsync">
                    asynchronous actions</a> in the documentation.
                </p>

                <h2>Count Controller</h2>

                <p>
                    Both the HomeController and AsyncController are very simple, and typically controllers present the results of the interaction of several services.  As an example, see the <code>
                    CountController</code>
                    , which shows how to inject a component into a controller and use the component when handling requests.  The count controller increments every time you click on it, so keep clicking to see the numbers go up.
                </p>


                <p>
                    You can read more about <a href="https://www.playframework.com/documentation/"""),_display_(/*107.99*/version),format.raw/*107.106*/("""/JavaDependencyInjection">
                    dependency injection</a> in the documentation.
                </p>

                <h2>Need more info on the console?</h2>

                <p>
                    For more information on the various commands you can run on Play, i.e. running tests and packaging applications for production, see <a href="https://playframework.com/documentation/"""),_display_(/*114.203*/version),format.raw/*114.210*/("""/PlayConsole">
                    Using the Play console</a>.
                </p>

                <h2>Need to set up an IDE?</h2>

                <p>
                    You can start hacking your application right now using any text editor. Any changes will be automatically reloaded at each page refresh,
                    including modifications made to Scala source files.
                </p>

                <p>
                    If you want to set-up your application in <strong>IntelliJ IDEA</strong>
                    or any other Java IDE, check the
                    <a href="https://www.playframework.com/documentation/"""),_display_(/*128.75*/version),format.raw/*128.82*/("""/IDE">Setting up your preferred IDE</a>
                    page.
                </p>

                <h2>Need more documentation?</h2>

                <p>
                    Play documentation is available at <a href="https://www.playframework.com/documentation/"""),_display_(/*135.110*/version),format.raw/*135.117*/("""">
                    https://www.playframework.com/documentation</a>.
                </p>

                <p>
                    Play comes with lots of example templates showcasing various bits of Play functionality at <a href="https://www.playframework.com/download#examples">
                    https://www.playframework.com/download#examples</a>.
                </p>

                <h2>Need more help?</h2>

                <p>
                    Play questions are asked and answered on Stackoverflow using the "playframework" tag: <a href="https://stackoverflow.com/questions/tagged/playframework">
                    https://stackoverflow.com/questions/tagged/playframework</a>
                </p>

                <p>
                    The <a href="https://discuss.playframework.com">Discuss Play Forum</a>
                    is where Play users come to seek help,
                    announce projects, and discuss issues and new features.
                </p>

                <p>
                    Gitter is a real time chat channel, like IRC. The <a href="https://gitter.im/playframework/playframework">
                    playframework/playframework</a>
                    channel is used by Play users to discuss the ins and outs of writing great Play applications.
                </p>

            </article>

            <aside>
                <h3>Browse</h3>
                <ul>
                    <li><a href="https://playframework.com/documentation/"""),_display_(/*168.75*/version),format.raw/*168.82*/("""">Documentation</a></li>
                    <li><a href="https://playframework.com/documentation/"""),_display_(/*169.75*/version),format.raw/*169.82*/("""/api/"""),_display_(/*169.88*/style),format.raw/*169.93*/("""/index.html">Browse the """),_display_(/*169.118*/{
                        style.capitalize
                    }),format.raw/*171.22*/(""" """),format.raw/*171.23*/("""API</a></li>
                </ul>
                <h3>Start here</h3>
                <ul>
                    <li><a href="https://playframework.com/documentation/"""),_display_(/*175.75*/version),format.raw/*175.82*/("""/PlayConsole">
                        Using the Play console</a></li>
                    <li><a href="https://playframework.com/documentation/"""),_display_(/*177.75*/version),format.raw/*177.82*/("""/IDE">
                        Setting up your preferred IDE</a></li>
                    <li><a href="https://playframework.com/download#examples">Example Projects</a>
                </ul>
                <h3>Help here</h3>
                <ul>
                    <li><a href="https://stackoverflow.com/questions/tagged/playframework">Stack Overflow</a></li>
                    <li><a href="https://discuss.playframework.com">Discuss Play Forum</a> </li>
                    <li><a href="https://gitter.im/playframework/playframework">Gitter Channel</a></li>
                </ul>

            </aside>

        </div>
    """)))}),format.raw/*191.6*/("""
"""))
      }
    }
  }

  def render(message:String,style:String): play.twirl.api.HtmlFormat.Appendable = apply(message,style)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (message,style) => apply(message,style)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Nov 19 20:11:21 CET 2018
                  SOURCE: /home/archie/Programming/Java/acoes-admin/app/views/welcome.scala.html
                  HASH: cdb326c9b17661a4dd35c297f033b0558f0625bf
                  MATRIX: 976->1|1112->42|1144->49|1191->88|1251->111|1287->121|1436->244|1463->251|1497->259|1524->266|1980->695|2008->702|2057->723|2787->1425|2816->1426|2865->1447|2972->1526|3001->1527|3610->2110|3639->2111|3689->2133|3779->2196|3808->2197|4776->3138|4805->3145|5014->3326|5043->3333|5851->4114|5880->4121|6683->4896|6713->4903|7137->5298|7167->5305|7840->5950|7869->5957|8166->6225|8196->6232|9716->7724|9745->7731|9872->7830|9901->7837|9935->7843|9962->7848|10016->7873|10102->7937|10132->7938|10326->8104|10355->8111|10528->8256|10557->8263|11216->8891
                  LINES: 29->1|34->1|36->3|36->3|36->3|38->5|40->7|40->7|40->7|40->7|55->22|55->22|56->23|75->42|75->42|76->43|77->44|77->44|91->58|91->58|93->60|95->62|95->62|112->79|112->79|114->81|114->81|126->93|126->93|140->107|140->107|147->114|147->114|161->128|161->128|168->135|168->135|201->168|201->168|202->169|202->169|202->169|202->169|202->169|204->171|204->171|208->175|208->175|210->177|210->177|224->191
                  -- GENERATED --
              */
          