package foldright

import org.scalatest.{ FlatSpec, Matchers }
import org.scalatest.matchers.{ MatchResult, Matcher }
import play.api.libs.json.{ Json, JsResult, JsValue, Reads, Writes }

class RecursiveAdtSpec extends FlatSpec with Matchers with JsResultMatchers {

  val form = Form("form")
  val formJson = Json.obj(
    "Form" -> Json.obj("value" -> "form")
  )

  "RecursiveAdt" should "write Form" in {
    val res: JsValue = implicitly[Writes[Expr]].writes(form)
    res should be(formJson)
  }

  it should "read Form" in {
    val res: JsResult[Expr] = implicitly[Reads[Expr]].reads(formJson)
    res should beJsSuccess[Expr](form)
  }

  val add = Add(Form("fieldA"), Constant("fieldB"))
  val addJson = Json.obj(
    "Add" -> Json.obj(
      "field1" -> Json.obj("Form" -> Json.obj("value" -> "fieldA")),
      "field2" -> Json.obj("Constant" -> Json.obj("value" -> "fieldB"))
    )
  )

  it should "write Add" in {
    val res: JsValue = implicitly[Writes[Expr]].writes(add)
    res should be(addJson)
  }

  it should "read Add" in {
    val res: JsResult[Expr] = implicitly[Reads[Expr]].reads(addJson)
    res should beJsSuccess[Expr](add)
  }

  val constant = Constant("constant")
  val constantJson = Json.obj(
    "Constant" -> Json.obj("value" -> "constant")
  )

  it should "parse Constant" in {
    val res: JsValue = implicitly[Writes[Expr]].writes(constant)
    res should be(constantJson)
  }

  it should "read Constant" in {
    val res: JsResult[Expr] = implicitly[Reads[Expr]].reads(constantJson)
    res should beJsSuccess[Expr](constant)
  }
}
