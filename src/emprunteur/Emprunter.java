/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emprunteur;

/**
 *
 * @author Info
 */
public interface Emprunter {
    
    boolean emprunter(String isbn);
    boolean rendre(String isbn);
    
}
