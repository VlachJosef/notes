package foo.bar

import org.scalatest.{ FlatSpec, Matchers }

class FooBar extends FlatSpec with Matchers {
  "FooBar" should "be FooBar in multi-play layout in core module. This need to be run when in core module!!!" in {
    succeed
  }
}
