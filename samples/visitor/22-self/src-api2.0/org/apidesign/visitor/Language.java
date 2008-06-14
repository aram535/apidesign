package org.apidesign.visitor;

public final class Language {
    private Language() { }
    
    public interface Expression {
        public abstract void visit(Visitor v);
    }
    public interface Plus extends Expression {
        public Expression getFirst();
        public Expression getSecond();
    }
    public interface Number extends Expression {
        public int getValue();
    }
    /** @since 2.0 */
    public interface Minus extends Expression {
        public Expression getFirst();
        public Expression getSecond();
    }
    
    public static abstract class Visitor {
        Visitor() {}
        /** @since 2.0 */
        public static Visitor create(Version2_0 v) {
            return create2_0(v);
        }

        /** @since 2.0 */
        public interface Version2_0 extends Version1_0 {
            public void visitMinus(Minus m, Visitor self);
        }


        /** @since 2.0 */
        public abstract void dispatchNumber(Number n);
        
        public static Visitor create(Version1_0 v) {
            return create1_0(v);
        }

        public interface Version1_0 {
            public boolean visitUnknown(Expression e, Visitor self);
            public void visitPlus(Plus s, Visitor self);
            public void visitNumber(Number n, Visitor self);
        }

        public abstract void dispatchPlus(Plus p);
        public abstract void dispatchMinus(Minus m);
    }
    
    static Visitor create1_0(final Visitor.Version1_0 v) {
        return new Visitor() {
            @Override
            public void dispatchPlus(Plus p) {
                v.visitPlus(p, this);
            }

            @Override
            public void dispatchNumber(Number n) {
                v.visitNumber(n, this);
            }

            @Override
            public void dispatchMinus(Minus m) {
                if (v.visitUnknown(m, this)) {
                    m.getFirst().visit(this);
                    m.getSecond().visit(this);
                }
            }
        };
    }
    static Visitor create2_0(final Visitor.Version2_0 v) {
        return new Visitor() {
            @Override
            public void dispatchPlus(Plus p) {
                v.visitPlus(p, this);
            }

            @Override
            public void dispatchNumber(Number n) {
                v.visitNumber(n, this);
            }

            @Override
            public void dispatchMinus(Minus m) {
                v.visitMinus(m, this);
            }
        };
    }
}
