package foldright

import org.scalatest._
import play.api.libs.json.{ Json, JsValue, Writes }

class AdtSpec extends FlatSpec with Matchers {

  val circle = Circle(Point(1, 2), 3)
  val polygon = Polygon(List(Point(1, 2), Point(2, 3), Point(3, 4)))

  "Adt" should "parse Circle" in {
    val res: JsValue = implicitly[Writes[Shape]].writes(circle)
    res should be(
      Json.obj(
        "origin" -> Json.obj("x" -> 1, "y" -> 2),
        "radius" -> 3
      )
    )
  }

  it should "parse Polygon" in {
    val res: JsValue = implicitly[Writes[Polygon]].writes(polygon)
    res should be(
      Json.obj(
        "points" -> Json.arr(
          Json.obj("x" -> 1, "y" -> 2),
          Json.obj("x" -> 2, "y" -> 3),
          Json.obj("x" -> 3, "y" -> 4)
        )
      )
    )
  }
}
