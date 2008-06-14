/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.apifest.boolcircuit;

// BEGIN: apifest.day2.elementbasedsolution.Circuit
public final class Circuit {
    private Circuit() {
    }
    
    public static Element and(final Element e1, final Element e2) {
        return new Element() {
            public boolean result() {
                return e1.result() && e2.result();
            }
            public double doubleResult() {
                return e1.doubleResult() * e2.doubleResult();
            }
        };
    }
    public static Element or(final Element e1, final Element e2) {
        return new Element() {
            public boolean result() {
                return e1.result() || e2.result();
            }
            public double doubleResult() {
                return 1.0 - (1.0 - e1.doubleResult()) * (1.0 - e2.doubleResult());
            }
        };
    }

    public static Element not(final Element e1) {
        return new Element() {
            public boolean result() {
                return !e1.result();
            }
            public double doubleResult() {
                return 1 - e1.doubleResult();
            }
        };
    }
    
    public static Element operation(final Operation op, final Element... elements) {
        return new Element() {
            public boolean result() {
                return doubleResult() >= 1.0;
            }
            public double doubleResult() {
                double[] arr = new double[elements.length];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = elements[i].doubleResult();
                }
                return op.computeResult(arr);
            }
        };
        
    }
    
    public static Variable var() {
        return new Variable();
    }
    
    public static abstract class Element {
        private Element() {
        }
        
        public abstract boolean result();
        public abstract double doubleResult();
    }
    
    public static final class Variable extends Element {
        private Boolean booleanValue;
        private Double doubleValue;
        
        public void assignValue(boolean b) {
            booleanValue = b;
        }
        public void assignValue(double d) {
            if (d < 0 || d > 1) {
                throw new IllegalArgumentException();
            }
            doubleValue = d;
        }

        public boolean result() {
            return booleanValue != null ? booleanValue : doubleValue >= 1.0;
        }

        public double doubleResult() {
            return doubleValue != null ? doubleValue : (booleanValue ? 1.0 : 0.0);
        }

    }
    
    public static interface Operation {
        public double computeResult(double... values);
    }
}
// END: apifest.day2.elementbasedsolution.Circuit
