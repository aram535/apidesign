package org.apidesign.test.visitor;

import org.apidesign.test.visitor.PrintTest.PrintVisitor;
import static junit.framework.Assert.*;
import org.apidesign.visitor.Language.Expression;
import org.apidesign.visitor.Language.Minus;
import org.apidesign.visitor.Language.Number;
import org.apidesign.visitor.Language.Plus;
import org.apidesign.visitor.Language.Visitor2_0;
import org.junit.Test;

public class PrintOfMinusStructureTest {
    @Test public void printOneMinusTwo() {
        Number one = new Number(1);
        Number two = new Number(2);
        Expression plus = new Minus(one, two);
        
        PrintVisitor print = new PrintVisitor();
        plus.visit(print);
        
        assertEquals("1 - 2", print.sb.toString());
    }
    
    @Test public void visitorReadyForVersion2_0() {
        // BEGIN: visitor.cleanversion.print2
        class PrintVisitor2_0 implements Visitor2_0 {
            StringBuffer sb = new StringBuffer();

            public void visitUnknown(Expression exp) {
                sb.append("unknown");
            }

            public void visitPlus(Plus s) {
                s.getFirst().visit(this);
                sb.append(" + ");
                s.getSecond().visit(this);
            }

            public void visitNumber(Number n) {
                sb.append (n.getValue());
            }
            public void visitMinus(Minus m) {
                m.getFirst().visit(this);
                sb.append(" - ");
                m.getSecond().visit(this);
            }
        }
        
        Number one = new Number(1);
        Number two = new Number(2);
        Expression plus = new Minus(one, two);
        
        PrintVisitor2_0 print = new PrintVisitor2_0();
        plus.visit(print);
        
        assertEquals("1 - 2", print.sb.toString());
        // END: visitor.cleanversion.print2
        
    }
}