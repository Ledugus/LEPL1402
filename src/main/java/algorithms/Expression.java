package algorithms;

/**
 * This class can be used to build simple arithmetic expression
 * with binary operator +,-,* and involving one variable 'x'.
 * <p>
 * The expression can be
 * 1) evaluated by replacing the variable x with a specific value
 * 2) derivated to obtain a new expression
 * <p>
 * You must modify this class to make it work
 * You can/should extend this class with inner classes the way you want.
 * You can also modify it but you are not allowed to modify the signature
 * of existing methods
 * <p>
 * As a reminder, the formulas for the derivations as are followed
 * - (f + g)' = f' + g'
 * - (f*g)' = f'g + fg'
 * - (x)' = 1
 * - (C)' = 0 with C a constant
 */
public abstract class Expression {

    /**
     * Creates the basic variable expression 'x'
     *
     * @return the expression 'x'
     */
    public static Expression x() {
        return X.INSTANCE;
    }

    /**
     * Creates the basic constant expression 'v'
     *
     * @return the expression 'v'
     */
    public static Expression value(double v) {
        return new Value(v);
    }

    /**
     * Creates the binary expression 'this + r'
     *
     * @param r the right operator
     * @return the binary expression 'this + r'
     */
    public Expression plus(Expression r) {
        return new BinaryExpression(this, '+', r);
    }

    /**
     * Creates the binary expression 'this - r'
     *
     * @param r the right operator
     * @return the binary expression 'this - r'
     */
    public Expression minus(Expression r) {
        return new BinaryExpression(this, '-', r);
    }

    /**
     * Creates the binary expression 'this * r'
     *
     * @param r the right operator
     * @return the binary expression 'this * r'
     */
    public Expression mul(Expression r) {
        return new BinaryExpression(this, '*', r);
    }

    /**
     * Evaluate the expression with fixed value for x
     *
     * @param xValue the value taken by x for the evaluation
     * @return the evaluation of the expression considering x=xValue
     */
    public abstract double evaluate(double xValue);

    /**
     * Derivate the expression wrt to 'x'
     *
     * @return the derivative of the expression with respect to 'x'
     */
    public abstract Expression derivate();

    private static class BinaryExpression extends Expression {
        private final Expression left;
        private final Expression right;
        private final char operator;

        private BinaryExpression(Expression left, char op, Expression right) {
            this.operator = op;
            this.left = left;
            this.right = right;
        }

        @Override
        public double evaluate(double xValue) {
            double leftres = left.evaluate(xValue);
            double rightres = right.evaluate(xValue);
            switch (operator) {
                case '+':
                    return leftres + rightres;
                case '-':
                    return leftres - rightres;
                case '*':
                    return leftres * rightres;
                default:
                    throw new IllegalArgumentException("Opérateur inconnu" + operator);
            }
        }

        @Override
        public Expression derivate() {
            Expression dleft = left.derivate();
            Expression dright = right.derivate();
            switch (operator) {
                case '+':
                    return dleft.plus(dright);
                case '-':
                    return dleft.minus(dright);
                case '*':
                    return dleft.mul(right).plus(left.mul(dright));
                default:
                    throw new IllegalArgumentException("Opérateur inconnu" + operator);
            }
        }
    }


    private static class Value extends Expression {
        private final double value;

        private Value(double v) {
            this.value = v;
        }
        public double evaluate(double d) {
            return this.value;
        }
        public Expression derivate() {
            return new Value(0);
        }


    }
    private static class X extends Expression {
        private static final X INSTANCE = new X();
        private X(){
        }
        @Override public double evaluate(double xValue) {
            return xValue;
        }
        @Override public Expression derivate() {
            return new Value(1);
        }
    }
}
