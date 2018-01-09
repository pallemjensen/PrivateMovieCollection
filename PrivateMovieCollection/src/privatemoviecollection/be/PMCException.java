/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.be;

/**
 *
 * @author Sven
 */
public class PMCException extends Exception {

    public PMCException() {
    }

    public PMCException(String string) {
        super(string);
    }

    public PMCException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public PMCException(Throwable thrwbl) {
        super(thrwbl);
    }
    
}
