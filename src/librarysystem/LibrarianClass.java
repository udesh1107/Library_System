
package librarysystem;

import javax.swing.JOptionPane;

/**
 *
 * @author udesh
 */
public class LibrarianClass {
    String indexNo ="007";
    String password ="max";
    Store str = new Store();

    LibrarianClass(Store st){
        this.str = st;
    }


    public void checkLibrarian(String indexNo,String pass){

        if(this.password.equals(pass) && this.indexNo.equals(indexNo)){

            Librarian lb = new Librarian(str);
            lb.setVisible(true);
         
        }else{
            JOptionPane.showMessageDialog(null, "Password is wrong");
            Login log = new Login();

            log.setVisible(true);

        }
    }

}
