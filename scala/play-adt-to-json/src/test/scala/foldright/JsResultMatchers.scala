package foldright

import org.scalatest.matchers.{ BeMatcher, MatchResult, Matcher }
import play.api.libs.json._
import play.api.data.validation.ValidationError

trait JsResultMatchers {

  /**
   * Checks to see if `play.api.libs.json.JsResult` is a specific JsSuccess element.
   */
  def beJsSuccess[E](element: E): Matcher[JsResult[E]] = new BeJsSuccessMatcher[E](element)

  /**
   * Checks to see if `play.api.libs.json.JsResult` is a `JsSuccess`.
   */
  def jsSuccess[E]: BeMatcher[JsResult[E]] = new IsJsSuccessMatcher[E]

  /**
   * Checks to see if `play.api.libs.json.JsResult` is a specific JsError element.
   */
  def beJsError[E](errors: Seq[(JsPath, Seq[ValidationError])]): Matcher[JsResult[E]] =
    new BeJsErrorMatcher[E](errors)

  /**
   * Checks to see if `play.api.libs.json.JsResult` is a `JsError`.
   */
  def jsError[E]: BeMatcher[JsResult[E]] = new IsJsErrorMatcher[E]

  final private class BeJsSuccessMatcher[E](element: E) extends Matcher[JsResult[E]] {
    def apply(jsResult: JsResult[E]): MatchResult = {
      MatchResult(
        jsResult.fold(_ => false, _ == element),
        s"'$jsResult' did not contain an JsSuccess element matching '$element'.",
        s"'$jsResult' contained an JsSuccess element matching '$element', but should not have."
      )
    }
  }

  final private class BeJsErrorMatcher[E](element: Seq[(JsPath, Seq[ValidationError])]) extends Matcher[JsResult[E]] {
    def apply(jsResult: JsResult[E]): MatchResult = {
      MatchResult(
        jsResult.fold(_ == element, _ => false),
        s"'$jsResult' did not contain an JsError element matching '$element'.",
        s"'$jsResult' contained an JsError element matching '$element', but should not have."
      )
    }
  }

  final private class IsJsSuccessMatcher[E] extends BeMatcher[JsResult[E]] {
    def apply(jsResult: JsResult[E]): MatchResult = MatchResult(
      jsResult.isSuccess,
      s"'$jsResult' was not an JsSuccess, but should have been.",
      s"'$jsResult' was an JsSuccess, but should *NOT* have been."
    )
  }

  final private class IsJsErrorMatcher[E] extends BeMatcher[JsResult[E]] {
    def apply(jsResult: JsResult[E]): MatchResult = MatchResult(
      jsResult.isError,
      s"'$jsResult' was not an JsError, but should have been.",
      s"'$jsResult' was an JsError, but should *NOT* have been."
    )
  }
}

object JsResultMatchers extends JsResultMatchers
