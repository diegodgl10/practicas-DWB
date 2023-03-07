import java.util.Scanner;

/* Clase para la Practica 01 */
public class Practica1 {

    /* Lista de categorias. */
    private static CategoryList categoryList;
    
    public static void main(String[] args) {
        categoryList = new CategoryList();
        while (true) {
            int opcion = menu();
            System.out.println();
            switch (opcion) {
                case 0:
                    return;
                case 1:
                    agregar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    eliminar();
            }
            System.out.println();
        }
    }

    /* Opcion para agregar una nueva categoria. */
    private static void agregar() {
        Scanner sc = new Scanner(System.in);
        Integer categoryId;
        String category;
        String acronym;
        String temp;
        int puntos = 0;
        boolean valido = false;
        do {
            System.out.print("ID de categoria (debe ser valor > 0): ");
            temp = sc.nextLine();
            categoryId = stringToInt(temp);
            
            System.out.print("Categoria (no puede ser vacia): ");
            category = sc.nextLine();

            System.out.print("Acronimo (no puede ser vacio): ");
            acronym = sc.nextLine();

            if (categoryId > 0) {
                puntos++;
            }
            if (category != null && !category.equals(" ")) {
                puntos++;
            }
            if (acronym != null && !acronym.equals(" ")) {
                puntos++;
            }
        } while (puntos != 3);
        valido = categoryList.createCategory(new Category(categoryId, category, acronym));
        if (valido) {
            System.out.println("Se agrego axitosamente !!");
        } else {
            System.out.println("Ya existe una categoria con este ID :(");
        }
    }

    /* Opcion para listar las categorias existentes. */
    private static void listar() {
        String lista = categoryList.getCategories();
        if (lista == null || lista.equals("")) {
            System.out.println("No existen categorías registradas");
        } else {
            System.out.println(lista);
        }
    }

    /* Opcion para buscar una categoria por ID */
    private static void buscar() {
        Scanner sc = new Scanner(System.in);
        Integer categoryId = -1;
        String in;
        do {
            System.out.print("ID de categoria (debe ser valor > 0): ");
            in = sc.nextLine();
            categoryId = stringToInt(in);
        } while (categoryId <= 0);
        Category categoria = categoryList.getCategory(categoryId);
        if (categoria == null) {
            System.out.println("No existe una categoría con el ID ingresado");
        } else {
            System.out.println(categoria.toString());
        }
    }

    /* Opcion para eliminar una categoria por ID */
    private static void eliminar() {
        Scanner sc = new Scanner(System.in);
        Integer categoryId = -1;
        String in;
        do {
            System.out.print("ID de categoria (debe ser valor > 0): ");
            in = sc.nextLine();
            categoryId = stringToInt(in);
        } while (categoryId <= 0);
        categoryList.deleteCategory(categoryId);
    }

    /* Menu principal. */
    private static int menu() {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        String menu;
        menu = "1  Agregar una categoria \n" +
               "2  Categorias registradas \n" +
               "3  Buscar categoria por ID \n" +
               "4  Eliminar categoria por ID \n" +
               "0  Salir";
        String in;
        do {
            System.out.println(menu + "\n");
            System.out.print("> ");
            in = sc.nextLine();
            opcion = stringToInt(in);
        } while (opcion < 0 || opcion > 4);
        return opcion;
    }

    /* Convierte un String a Integer */
    private static Integer stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }


}
