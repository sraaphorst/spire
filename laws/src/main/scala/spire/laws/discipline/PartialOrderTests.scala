package spire.laws
package discipline

import spire.algebra.{Eq, PartialOrder}
import spire.std.boolean._

import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll

trait PartialOrderTests[A] extends EqTests[A] {

  def laws: PartialOrderLaws[A]

  def partialOrder(implicit arbA: Arbitrary[A], eqA: Eq[A]): RuleSet =
    new DefaultRuleSet(
      "partialOrder",
      Some(eqv),
      "transitivity" -> forAll(laws.transitivity _),
      "reflexitivity lt" -> forAll(laws.reflexitivityLt _),
      "reflexitivity gt" -> forAll(laws.reflexitivityGt _),
      "antisymmetry" -> forAll(laws.antisymmetry _),
      "gt" -> forAll(laws.gt _),
      "gteqv" -> forAll(laws.gteqv _),
      "lt" -> forAll(laws.lt _),
      "partialCompare" -> forAll(laws.partialCompare _),
      "pmax" -> forAll(laws.pmax _),
      "pmin" -> forAll(laws.pmin _)
    )

}

object PartialOrderTests {
  def apply[A: PartialOrder]: PartialOrderTests[A] =
    new PartialOrderTests[A] { def laws: PartialOrderLaws[A] = PartialOrderLaws[A] }
}