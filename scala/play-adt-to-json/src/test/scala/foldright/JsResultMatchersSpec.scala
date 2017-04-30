package foldright

import org.scalatest.{ WordSpec, Matchers }
import play.api.data.validation.ValidationError
import play.api.libs.json._
import foldright.JsResultMatchers._

class JsResultMatchersSpec extends WordSpec with Matchers {

  sealed trait Expr
  case class Add(expr1: Expr, expr2: Expr) extends Expr
  case class Constant(value: String) extends Expr

  val add: JsResult[Expr] = JsSuccess(Add(Constant("c1"), Constant("c2")))
  val addError: JsResult[Expr] = JsError(JsPath(List(KeyPathNode("key-path-node"))), "error-with-path")
  val jsSuccessValue: JsResult[String] = JsSuccess("js-success")

  "JsResultMatchers" should {
    "Match 'non specific' jsSuccess" in {
      jsSuccessValue should be(jsSuccess)
    }

    "Match 'valued' jsSuccess" in {
      jsSuccessValue should beJsSuccess("js-success")
    }

    "Match 'non specific' jsSuccess 2" in {
      add should be(jsSuccess)
    }

    "Match 'valued' jsSuccess 2" in {
      add should beJsSuccess[Expr](Add(Constant("c1"), Constant("c2")))
    }

    "Match 'valued' jsError" in {
      val pathNodes: List[PathNode] = List(KeyPathNode("key-path-node"))
      val path: JsPath = JsPath(pathNodes)
      val error = JsError(path, "error-with-path").errors
      addError should beJsError(error)
    }

  }
}
