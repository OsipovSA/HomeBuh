import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.swing.tree.*;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sergey on 04.08.2019.
 */

// Переопределенный класс узла с сортировкой.
// Нашел на просторах интернета, работает
class MyNode extends DefaultMutableTreeNode {

    public MyNode(Object userObject) {
        super(userObject);
    }

    @Override
    public void add(MutableTreeNode newChild) {
        super.add(newChild);
        sort();//add to tree and sort immediately use in case the model is small if large comment it and and call node.sort once you've added all the children
    }

    public void sort() {
        Collections.sort(children, compare());
    }

    private Comparator compare() {
        return new Comparator<DefaultMutableTreeNode>() {
            @Override
            public int compare(DefaultMutableTreeNode o1, DefaultMutableTreeNode o2) {
                return o1.getUserObject().toString().compareTo(o2.getUserObject().toString());
            }

        };
    }
}

public class MyDefaultTreeModel {

    Node root = null;
    DefaultTreeModel defaultTreeModel;

    public MyDefaultTreeModel() {
        this.defaultTreeModel = getDefaultTreeModel();
    }

    public DefaultTreeModel getDefaultTreeModel() {

        root = FileData.getInstance().getRoot();
        if(root!=null) {
            return new DefaultTreeModel(builtDefaultTreeModel(root));
        }else{
            return null;
        }
    }

    /*
    * необходимо дописать условие в считывание узлов, что если
    * данный узел имеет атрибут "record", тогда это конечный узел в иерархии
    * и нужно создавать экземпляр класса BuhRecord и заполнять его.
    * Он и будет конечным узлом дерева
    * */
    private TreeNode builtDefaultTreeModel(Node root) {
        MyNode dmtNode;

        dmtNode = new MyNode(root.getNodeName());
        NodeList nodeList = root.getChildNodes();
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                if (tempNode.hasChildNodes()) {
                    // loop again if has child nodes
                    dmtNode.add((MyNode) builtDefaultTreeModel(tempNode));
                }else{
                    dmtNode.add(new MyNode(tempNode.getNodeName()));
                }
            }
        }
        return dmtNode;
    }
}
