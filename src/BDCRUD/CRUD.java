
package BDCRUD;



    import java.util.Scanner;

public class CRUD {

    public static void main(String[] args) {
        ProductoDAO productoDAO = new ProductoDAO();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("***** Menú principal *****");
            System.out.println("1. Ingresar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Modificar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Salir del menú principal");
            System.out.print("Seleccione una opción del menú: ");
            
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    productoDAO.ingresarProducto();
                    break;
                case 2:
                    productoDAO.mostrarProductos();
                    break;
                case 3:
                    productoDAO.buscarProducto();
                    break;
                case 4:
                    productoDAO.modificarProducto();
                    break;
                case 5:
                    productoDAO.eliminarProducto();
                    break;
                case 6:
                    productoDAO.salir();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }
}


