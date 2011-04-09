/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package librarysystem;

import java.io.Serializable;
import java.util.Calendar;


/**
 *
 * @author udesh
 */
public class Material implements Serializable {
    public String title,materialType;
    public String indexNo;
    public boolean availability=true;
    int noOfCopy;
    Calendar borrowDate,returnDate;

    public Material(String title,String indexNo){
        this.title=title;
        this.indexNo=indexNo;
    }

    public void setAvailability(boolean availability){
        this.availability=availability;
    }

    public String getIndex(){
        return indexNo;
    }
    public String getTitle(){
        return title;
    }
    public void setBorrowingDate(Calendar borrowDate){
        this.borrowDate=borrowDate;
    }
    public void setReturnDate(Calendar returnDate){
        this.returnDate=returnDate;
    }

}
