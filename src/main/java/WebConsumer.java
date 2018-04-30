import com.marta.sandbox.webservice.ProductEditor;
import com.marta.sandbox.webservice.ProductEditorService;
import com.marta.sandbox.webservice.ProductRecord;

import java.util.Scanner;

public class WebConsumer {

    public static void main(String[] args) {

        ProductEditorService productEditorService = new ProductEditorService();
        ProductEditor editor = productEditorService.getProductEditorPort();

        int choice = -1;
        Scanner scan = new Scanner (System.in);

        while(choice!=0) {
            printMenu();
            choice = scan.nextInt();
            scan.nextLine();
            try {
                switch (choice) {
                    case 0: break;
                    case 1:
                        System.out.println("Введите id товара");
                        printInfo(editor.getProductById(scan.nextLine()));
                        break;
                    case 2:
                        System.out.println("Введите наименование товара");
                        editor.getProductsByName(scan.nextLine()).getItem().forEach(WebConsumer::printInfo);
                        break;
                    case 3:
                        editor.getProducts().getItem().forEach(WebConsumer::printInfo);
                        break;
                    case 4:
                        System.out.println("Введите наименование категории");
                        editor.getProductsByCategory(scan.nextLine()).getItem().forEach(WebConsumer::printInfo);
                        break;
                    case 5:
                        //too long...
                        editor.addNewProduct("test product",
                                "HOME",
                                "4cbeb411-972c-4ecf-a12b-781b0f1b6e54",
                                99.90f,
                                "-",
                                "some long description",
                                true);
                        System.out.println("Товар добавлен");
                        break;
                    case 6:
                        System.out.println("Введите id товара");
                        editor.removeProductById(scan.nextLine());
                        break;
                    case 7:
                        System.out.println("Введите название нового бренда:");
                        editor.addNewBrand(scan.nextLine());
                        break;
                    default:
                        System.out.println("Неверный ввод - введите цифру");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printMenu () {
        System.out.println("\nВыберите из списка:");
        System.out.println("1 - получить информацию о товаре по id");
        System.out.println("2 - получить информацию о товаре по наименованию");
        System.out.println("3 - получить список всех товаров");
        System.out.println("4 - получить список всех товаров по категории");
        System.out.println("5 - добавить товар в базу данных");
        System.out.println("6 - удалить товар из базы данных");
        System.out.println("7 - добавить новый бренд");
        System.out.println("0 - ВЫЙТИ");
    }

    private static void printInfo (ProductRecord product) {
        System.out.println("ProductRecord{" +
                "id='" + product.getId() + '\'' +
                ", name='" + product.getName() + '\'' +
                ", category='" + product.getCategory() + '\'' +
                ", brandId='" + product.getBrandId() + '\'' +
                ", price=" + product.getPrice() +
                ", image='" + product.getImage() + '\'' +
                ", description='" + product.getDescription() + '\'' +
                ", inStock=" + product.isInStock() +
                '}');
    }
}
