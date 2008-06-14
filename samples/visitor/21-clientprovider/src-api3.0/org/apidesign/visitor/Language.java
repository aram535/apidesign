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
    
    // BEGIN: visitor.clientprovider.v3
    /** @since 3.0 */
    public interface Real extends Expression {
        public double getValue();
    }
    public static abstract class Visitor {
        Visitor() {}
        
        
        /** @since 3.0 */
        public static Visitor create(Version3_0 v) {
            return create3_0(v);
        }

        /** @since 3.0 */
        public interface Version3_0 {
            public boolean visitUnknown(Expression e);
            public void visitPlus(Plus s);
            public void visitMinus(Minus s);
            public void visitReal(Real r);
        }

        /** @since 3.0 */
        public abstract void dispatchReal(Real r);
        
    // FINISH: visitor.clientprovider.v3
        /** @since 2.0 */
        public static Visitor create(Version2_0 v) {
            return create2_0(v);
        }

        /** @since 2.0 */
        public interface Version2_0 extends Version1_0 {
            public void visitMinus(Minus m);
        }


        /** @since 2.0 */
        public abstract void dispatchNumber(Number n);
        
        public static Visitor create(Version1_0 v) {
            return create1_0(v);
        }

        public interface Version1_0 {
            public boolean visitUnknown(Expression e);
            public void visitPlus(Plus s);
            public void visitNumber(Number n);
        }

        public abstract void dispatchPlus(Plus p);
        public abstract void dispatchMinus(Minus m);
    }
    
    // BEGIN: visitor.clientprovider.dispatch.v3.l1
    static Visitor create1_0(final Visitor.Version1_0 v) {
        return new Visitor() {
            @Override
            public void dispatchPlus(Plus p) {
                v.visitPlus(p);
            }

            @Override
            public void dispatchNumber(Number n) {
                v.visitNumber(n);
            }

            @Override
            public void dispatchMinus(Minus m) {
                if (v.visitUnknown(m)) {
                    m.getFirst().visit(this);
                    m.getSecond().visit(this);
                }
            }

            @Override
            public void dispatchReal(Real r) {
                v.visitUnknown(r);
            }
        };
    }
    // END: visitor.clientprovider.dispatch.v3.l1
    // BEGIN: visitor.clientprovider.dispatch.v3.l2
    static Visitor create2_0(final Visitor.Version2_0 v) {
        return new Visitor() {
            @Override
            public void dispatchPlus(Plus p) {
                v.visitPlus(p);
            }

            @Override
            public void dispatchNumber(Number n) {
                v.visitNumber(n);
            }

            @Override
            public void dispatchMinus(Minus m) {
                v.visitMinus(m);
            }

            @Override
            public void dispatchReal(Real r) {
                v.visitUnknown(r);
            }
        };
    }
    // END: visitor.clientprovider.dispatch.v3.l2
    // BEGIN: visitor.clientprovider.dispatch.v3.l3
    static Visitor create3_0(final Visitor.Version3_0 v) {
        return new Visitor() {
            @Override
            public void dispatchReal(Real r) {
                v.visitReal(r);
            }

            @Override
            public void dispatchNumber(final Number n) {
                class RealWrapper implements Real {
                    public double getValue() {
                        return n.getValue();
                    }
                    public void visit(Visitor v) {
                        n.visit(v);
                    }
                }
                v.visitReal(new RealWrapper());
            }

            @Override
            public void dispatchPlus(Plus p) {
                v.visitPlus(p);
            }

            @Override
            public void dispatchMinus(Minus m) {
                v.visitMinus(m);
            }
        };
    }
    // END: visitor.clientprovider.dispatch.v3.l3
}
