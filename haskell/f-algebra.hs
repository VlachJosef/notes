-- https://www.schoolofhaskell.com/user/bartosz/understanding-algebras
data ExprF a
    = Const Int
    | Add a
          a
    | Mul a
          a

newtype Fix f =
    Fx (f (Fix f))

type Expr = Fix ExprF

val :: Fix ExprF
val = Fx (Const 12)

testExpr = Fx $ (Fx $ (Fx $ Const 2) `Add` (Fx $ Const 3)) `Mul` (Fx $ Const 4)

instance Functor ExprF where
    fmap eval (Const i) = Const i
    fmap eval (left `Add` right) = (eval left) `Add` (eval right)
    fmap eval (left `Mul` right) = (eval left) `Mul` (eval right)

-- alg :: ExprF Int -> Int
-- alg (Const i) = i
-- alg (x `Add` y) = x + y
-- alg (x `Mul` y) = x * y
type Algebra f a = f a -> a

-- My simple algebra
type SimpleA = Algebra ExprF Int

alg :: SimpleA
alg (Const i) = i
alg (x `Add` y) = x + y
alg (x `Mul` y) = x * y

type ExprInitAlg = Algebra ExprF (Fix ExprF)

ex_init_alg :: ExprF (Fix ExprF) -> Fix ExprF
ex_init_alg = Fx
