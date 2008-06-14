/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.impl.security.friendapi;

/**
 *
 * @author Jaroslav Tulach <jaroslav.tulach@apidesign.org>
 */
public interface DigestProvider {
    public abstract DigestImplementation create(String algorithm);
}
