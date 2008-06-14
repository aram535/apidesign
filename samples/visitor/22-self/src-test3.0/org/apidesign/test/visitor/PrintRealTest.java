package org.apidesign.test.visitor;

import org.apidesign.test.visitor.PrintTest.PrintVisitor;
import static junit.framework.Assert.*;
import org.apidesign.visitor.Language.Expression;
import org.apidesign.visitor.Language.Minus;
import org.apidesign.visitor.Language.Number;
import org.apidesign.visitor.Language.Plus;
import org.apidesign.visitor.Language.Real;
import org.apidesign.visitor.Language.Visitor;
import org.junit.Test;

public class PrintRealTest {
    @Test public void printOneMinusTwo() {
        Real one = newReal(1);
        Real two = newReal(2);
        Expression plus = PrintOfMinusStructureTest.newMinus(one, two);
        
        PrintVisitor print = new PrintVisitor();
        plus.visit(Visitor.create(print));
        
        if (Boolean.getBoolean("no.failures")) {
            assertEquals("unknownunknownunknown", print.sb.toString());
            return;
        }
        assertEquals("1.0 - 2.0", print.sb.toString());
    }
    
    @Test public void visitorReadyForVersion3_0() {
        class PrintVisitor3_0 implements Visitor.Version3_0 {
            StringBuffer sb = new StringBuffer();

            public boolean visitUnknown(Expression exp, Visitor self) {
                sb.append("unknown");
                return true;
            }

            public void visitPlus(Plus s, Visitor self) {
                s.getFirst().visit(self);
                sb.append(" + ");
                s.getSecond().visit(self);
            }

            public void visitMinus(Minus m, Visitor self) {
                m.getFirst().visit(self);
                sb.append(" - ");
                m.getSecond().visit(self);
            }

            public void visitReal(Real r, Visitor self) {
                sb.append(r.getValue());
            }
        }
        
        Number one = PrintTest.newNumber(1);
        Number two = PrintTest.newNumber(2);
        Expression plus = PrintOfMinusStructureTest.newMinus(one, two);
        
        PrintVisitor3_0 print = new PrintVisitor3_0();
        plus.visit(Visitor.create(print));
        
        assertEquals("1.0 - 2.0", print.sb.toString());

        Real five = newReal(5);
        Real three = newReal(3);
        Expression realPlus = PrintOfMinusStructureTest.newMinus(five, three);
        
        PrintVisitor3_0 printReal = new PrintVisitor3_0();
        realPlus.visit(Visitor.create(printReal));
        
        assertEquals("5.0 - 3.0", printReal.sb.toString());
        
    }
    
    public static Real newReal(final double value) {
        return new Real() {
            public double getValue() {
                return value;
            }

            public void visit(Visitor v) {
                v.dispatchReal(this);
            }
        };
    }
}
