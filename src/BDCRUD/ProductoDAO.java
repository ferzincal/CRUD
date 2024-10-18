/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDCRUD;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProductoDAO {
    
    public void ingresarProducto() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        
        System.out.print("Ingrese la cantidad del producto: ");
        int cantidad = scanner.nextInt();
        
        String sql = "INSERT INTO productos (nombre, precio, cantidad) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setInt(3, cantidad);
            stmt.executeUpdate();
            
            System.out.println("Producto ingresado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   public void mostrarProductos() {
        String sql = "SELECT * FROM productos";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Precio: " + rs.getDouble("precio"));
                System.out.println("Cantidad: " + rs.getInt("cantidad"));
                System.out.println("----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public void buscarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre o ID del producto a buscar: ");
        String input = scanner.nextLine();
        
        String sql = "SELECT * FROM productos WHERE nombre LIKE ? OR id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + input + "%");
            stmt.setString(2, input);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Precio: " + rs.getDouble("precio"));
                System.out.println("Cantidad: " + rs.getInt("cantidad"));
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
   public void modificarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        
        System.out.print("Ingrese el nuevo nombre del producto: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese el nuevo precio del producto: ");
        double precio = scanner.nextDouble();
        
        System.out.print("Ingrese la nueva cantidad del producto: ");
        int cantidad = scanner.nextInt();
        
        String sql = "UPDATE productos SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setInt(3, cantidad);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            
            System.out.println("Producto actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
  public void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        
        String sql = "DELETE FROM productos WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            System.out.println("Producto eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  public void salir() {
        System.out.println("Saliendo del menú principal...");
        System.exit(0);
    }
}// fin del programa

