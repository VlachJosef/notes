package code.selector

import org.scalatest.{ FlatSpec, Matchers }

class SelectorSpec extends FlatSpec with Matchers {
  "Selector" should "select" in {
    import Selector._
    println("Selector.render " + f(in))

    val res = render(<div id="foo"/>)

    println("res " + res)

    val res2 = render2(<div id="foo"/>)
    println("res2 " + res2)

    def res3 = helloWorld(<div></div>)

    println("res3 " + res3)


  }
}
