import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ranges.DocumentRange;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sergey on 23.07.2019.
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

public class MyThree extends JTree {

    final String fileName = "HomeBuh.xml";
    Node root = null;
    DefaultTreeModel defaultTreeModel;

    public MyThree() {
        if(!Files.exists(Paths.get(fileName))) {
            // Создаём файл с некоторыми пустыми категориями
            try {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document document = builder.newDocument();

                Element root = document.createElement("Категории");
                document.appendChild(root);

                // Авто, подкатегории Запчасти и Ремонт
                Element avto = document.createElement("Авто");
                avto.appendChild(document.createElement("Запчасти"));
                avto.appendChild(document.createElement("Ремонт"));
                root.appendChild(avto);
                // Продукты питания
                Element product = document.createElement("Продукты");
                root.appendChild(product);
                // Медицина, подкатегории Лекарства и Обследования
                Element medical = document.createElement("Медицина");
                medical.appendChild(document.createElement("Лекарства"));
                medical.appendChild(document.createElement("Обследования"));
                root.appendChild(medical);
                // Развлечения
                Element holiday = document.createElement("Развлечения");
                root.appendChild(holiday);
                // Подарки
                Element present = document.createElement("Подарки");
                root.appendChild(present);
                // Кварплата
                Element kommunalka = document.createElement("Коммуналка");
                root.appendChild(kommunalka);
                // Одежда, вещи
                Element clothes = document.createElement("Одежда");
                root.appendChild(clothes);
                // Расходы на отпуск
                Element vacation = document.createElement("Отпуск");
                root.appendChild(vacation);
                // Налоги
                Element taxes = document.createElement("Налоги");
                root.appendChild(taxes);

                // Сохраняем в файл
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                transformer.transform(new DOMSource(document),
                        new StreamResult(new FileOutputStream(fileName)));

                System.out.println("Документ сохранён");

            } catch (IOException | TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Can't create file",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        // Читаем файл в DefaultTreeModel
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(fileName);
            root = (Node) document.getDocumentElement();

        }catch(IOException | ParserConfigurationException | SAXException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Can't parse file",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        };

        if(root!=null){
            defaultTreeModel= new DefaultTreeModel(builtDefaultTreeModel(root));
            this.setModel(defaultTreeModel);

            System.out.println("Документ прочитан");
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
