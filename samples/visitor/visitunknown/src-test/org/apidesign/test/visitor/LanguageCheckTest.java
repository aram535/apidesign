package org.apidesign.test.visitor;

import static junit.framework.Assert.*;
import org.apidesign.visitor.Language.Expression;
import org.apidesign.visitor.Language.Number;
import org.apidesign.visitor.Language.Plus;
import org.apidesign.visitor.Language.Visitor;
import org.junit.Test;

public class LanguageCheckTest {

    // BEGIN: visitor.language.check.exception
    private static class Valid1_0Language extends Visitor/*version1.0*/ {
        public void visitPlus(Plus s) {
            s.getFirst().visit(this);
            s.getSecond().visit(this);
        }
        public void visitNumber(Number n) { 
        }
    }

    public static boolean isValid1_0Language(Expression expression) {
        Valid1_0Language valid = new Valid1_0Language();
        try {
            expression.visit(valid);
            return true; // yes, no unknown elements
        } catch (IllegalStateException ex) {
            return false; // no, probably from visitMinus of Visitor/*2.0*/
        }
    }
    // END: visitor.language.check.exception
    
    @Test public void printOnePlusOne() {
        Number one = new Number(1);
        Expression expression = new Plus(one, one);

        assertTrue("Valid language", isValid1_0Language(expression));
    }

    @Test public void printOnePlusTwoPlusThree() {
        Number one = new Number(1);
        Number two = new Number(2);
        Number three = new Number(3);
        Expression plus = new Plus(one, new Plus(two, three));
        
        assertTrue("Valid language", isValid1_0Language(plus));
    }
}
