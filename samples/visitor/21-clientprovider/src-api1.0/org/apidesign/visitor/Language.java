package org.apidesign.visitor;

public final class Language {
    private Language() { }
    
    // BEGIN: visitor.clientprovider.v1
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

    public static abstract class Visitor {
        Visitor() {}

        public Visitor create(Version1_0 v) {
            return create1_0(v);
        }

        public interface Version1_0 {
            public boolean visitUnknown(Expression e);
            public void visitPlus(Plus s);
            public void visitNumber(Number n);
        }

        public abstract void dispatchPlus(Plus p);
        public abstract void dispatchNumber(Number n);
    }
    // END: visitor.clientprovider.v1
    
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
        };
    }
}
