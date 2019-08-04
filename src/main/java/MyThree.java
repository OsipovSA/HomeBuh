
import javax.swing.*;

/**
 * Created by Sergey on 23.07.2019.
 */

public class MyThree extends JTree {

    public MyThree() {

        MyDefaultTreeModel myDefaultTreeModel = new MyDefaultTreeModel();
        this.setModel(myDefaultTreeModel.getDefaultTreeModel());
    }
}