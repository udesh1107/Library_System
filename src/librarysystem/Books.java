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
public class Books extends Material implements Serializable{
    public String section;
    public Books(String title,String indexNo,String section){
        super(title,indexNo);
        this.section = section;
        //this.author=author;

    }

}
