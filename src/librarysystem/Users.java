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
public class Users implements Serializable {
    String name;
    String indexNo;
    String password="uom";
    String userType;
    int materialLimit,demeritLimit,demeritPoints=0;
    int noOfMaterialBorrowed=0;
    boolean suspendedState=false;
    int timesSuspended=0,maxTimeSuspended=5;
    boolean permenent=false;
    int selection;

    Users(String name,String indexNo,int selection){
        this.name=name;
        this.indexNo=indexNo;
        if(selection==1){
            userType = "Student";
            materialLimit=5;
            demeritLimit=30;
        }else{
            userType = "Academic";
            materialLimit=25;
            demeritLimit=60;
        }
    }

    Users() {

    }

    public String getIndex(){
        return indexNo;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }
    void setPassword(String pass){
        this.password=pass;
    }


}
