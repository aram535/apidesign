package org.apidesign.visitor;

public final class Language {

    private Language() {
    }

    public static abstract class Expression {

        Expression() {
        }

        public abstract void visit(Visitor v);
    }

    public static final class Plus extends Expression {

        private final Expression first;
        private final Expression second;

        public Plus(Expression first, Expression second) {
            this.first = first;
            this.second = second;
        }

        public Expression getFirst() {
            return first;
        }

        public Expression getSecond() {
            return second;
        }

        @Override
        public void visit(Visitor v) {
            if (v instanceof Visitor1_0) {
                ((Visitor1_0) v).visitPlus(this);
            } else if (v instanceof Visitor3_0) {
                ((Visitor3_0) v).visitPlus(this);
            } else {
                v.visitUnknown(this);
            }
        }
    }

    public static final class Number extends Expression {

        private final int value;

        public Number(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public void visit(Visitor v) {
            if (v instanceof Visitor1_0) {
                ((Visitor1_0) v).visitNumber(this);
            } else if (v instanceof Visitor3_0) {
                Real wrapper = new Real(getValue());
                ((Visitor3_0) v).visitReal(wrapper);
            } else {
                v.visitUnknown(this);
            }
        }
    }
    /** @since 2.0 */
    public static final class Minus extends Expression {
        private final Expression first;
        private final Expression second;
        
        public Minus(Expression first, Expression second) {
            this.first = first;
            this.second = second;
        }
        public Expression getFirst() { return first; }
        public Expression getSecond() { return second; }
        
        public void visit(Visitor v) { 
            if (v instanceof Visitor2_0) {
                ((Visitor2_0)v).visitMinus(this);
            } else if (v instanceof Visitor3_0) {
                ((Visitor3_0)v).visitMinus(this);
            } else {
                v.visitUnknown(this);
            }
        }
    }
    // BEGIN: visitor.nonmonotonic.real
    /** @since 3.0 */
    public static final class Real extends Expression {
        private final double value;
        public Real(double value) {
            this.value = value;
        }
        public double getValue() {
            return value;
        }
        public void visit(Visitor v)
        // FINISH: visitor.nonmonotonic.real
        {
        }
    }

    
    public interface Visitor {
        public void visitUnknown(Expression e);
    }
    public interface Visitor1_0 extends Visitor {
        public void visitPlus(Plus s);
        public void visitNumber(Number n);
    }
    /** @since 2.0 */
    public interface Visitor2_0 extends Visitor {
        public void visitMinus(Minus s);
    }
    // BEGIN: visitor.nonmonotonic.visitor
    /** @since 3.0 */
    public interface Visitor3_0 extends Visitor {
        public void visitPlus(Plus s);
        public void visitMinus(Minus s);
        public void visitReal(Real r);
    }
    // END: visitor.nonmonotonic.visitor
}
