package code.selector.snippet

import net.liftweb.util.Helpers._

/* object Selector {
 *   val f = "a [href]" #> "http://example.org"
 *   val in = <a>click me</a>
 *   def render = "#foo" #> <div id="bar"/> andThen "#bar *" #> "bar content"
 *   def render2 = "#foo" #> <div id="bar2"/> & "#foo *" #> <div id="bar"/>
 *   def helloWorld = "*" #> <strong>hello world!</strong>
 *
 *   //render(<div id="foo"/>)
 * } */

class Selector {
  val f = "a [href]" #> "http://example.org"
  val in = <a>click me</a>
  def render = "#foo" #> <div id="bar"/> andThen "#bar *" #> "bar content"
  def render2 = "#foo" #> <div id="bar2"/> & "#foo *" #> <div id="bar"/>
  def helloWorld = "*" #> <strong>hello world!</strong>

  //render(<div id="foo"/>)
}
