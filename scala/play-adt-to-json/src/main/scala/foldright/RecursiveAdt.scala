package foldright

import julienrf.json.derived
import play.api.libs.json.OFormat

sealed trait Expr

final case class Add(field1: Expr, field2: Expr) extends Expr
final case class Form(value: String) extends Expr
final case class Constant(value: String) extends Expr

object Expr {

  implicit val reads: OFormat[Expr] = derived.oformat

}
