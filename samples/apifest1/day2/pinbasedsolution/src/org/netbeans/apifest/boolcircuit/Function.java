/*
 * Function.java
 *
 * Created on 13. červenec 2006, 14:40
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.apifest.boolcircuit;

/**
 * An abstract transfer function of a gate.
 * @author nenik
 */
public abstract class Function {
    
    public abstract double evaluate(double input1, double input2);
}
