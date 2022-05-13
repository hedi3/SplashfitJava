/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopp.Interface;


import java.util.ArrayList;

/**
 *
 * @author BabyViper
 * @param <T>
 */
public interface Iservice<T> {

    void ajouter(T t) ;
    
    ArrayList<T> getAll() ;

}
