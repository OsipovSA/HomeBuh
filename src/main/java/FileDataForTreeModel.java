import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sergey on 04.08.2019.
 */
public class FileDataForTreeModel implements IDataForTreeModel {

    final String fileName = "HomeBuh.xml";
    Node root = null;

    public FileDataForTreeModel() {
        this.root = getDateForTreeModel();
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public Node getDateForTreeModel() {
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
                transformer.transform(new DOMSource(document),
                        new StreamResult(new FileOutputStream(fileName)));

                System.out.println("Документ сохранён");

            } catch (IOException | TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Can't create file",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        // Читаем файл в DefaultTreeModel
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(fileName);
            root = document.getDocumentElement();

        }catch(IOException | ParserConfigurationException | SAXException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Can't parse file",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return root;
    }
}
