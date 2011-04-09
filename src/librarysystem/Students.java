/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package librarysystem;

import java.io.Serializable;

/**
 *
 * @author udesh
 */
public class Students extends Users implements Serializable{

    Students(String name,String indexNo,int selection){
        super(name,indexNo,selection);
    }

}
