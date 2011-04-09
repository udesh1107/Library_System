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
public class DVD extends Material implements Serializable{
    public String section;
    public int volume;
    public DVD(String title,String indexNo,String section){
        super(title,indexNo);
        this.section=section;

    }

}
