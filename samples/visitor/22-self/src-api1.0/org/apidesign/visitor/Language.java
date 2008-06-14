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

    // BEGIN: visitor.clientprovider.self.v1
    public static abstract class Visitor {
        Visitor() {}

        public static Visitor create(Version1_0 v) {
            return create1_0(v);
        }

        public interface Version1_0 {
            public boolean visitUnknown(Expression e, Visitor self);
            public void visitPlus(Plus s, Visitor self);
            public void visitNumber(Number n, Visitor self);
        }

        public abstract void dispatchPlus(Plus p);
        public abstract void dispatchNumber(Number n);
    }
    // END: visitor.clientprovider.self.v1
    
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
        };
    }
}
