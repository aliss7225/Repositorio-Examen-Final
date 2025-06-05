/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.seguridad;

import Controlador.seguridad.Clientes; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import Modelo.Conexion;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author visitante
 */
public class ClientesDAO {

    private static final String SQL_SELECT = "SELECT codigo_clientes, nombre_clientes, nit_clientes,tipo_bodega,direccion FROM clientes";
    private static final String SQL_INSERT = "INSERT INTO clientes(codigo_clientes, nombre_clientes, nit_clientes,tipo_bodega,direccion) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE clientes SET nombre_clientes=?, nit_clientes=?, nombre_clientes=?, nit_clientes=? WHERE codigo_clientes = ?";
    private static final String SQL_DELETE = "DELETE FROM clientes WHERE codigo_clientes=?";
    private static final String SQL_QUERY = "SELECT codigo_clientes, nombre_clientes, nit_clientes,tipo_bodega,direccion FROM clientes WHERE codigo_clientes = ?";

    public List<Clientes> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes cliente = null;
        List<Clientes> clientes = new ArrayList<Clientes>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String codigoCliente = rs.getString("codigo_clientes");
                String nombreClientes = rs.getString("nombre_clientes");
                String nitClientes = rs.getString("nit_clientes");
                String tipo_bodega = rs.getString("tipo_bodega");
                String direccion = rs.getString("direccion");
                
                cliente = new Clientes();
                cliente.setCodigo_clientes(codigoCliente);
                cliente.setNombre_clientes(nombreClientes);
                cliente.setNit_clientes(nitClientes);
                cliente.setTipo_bodega(tipo_bodega);
                cliente.setDireccion(direccion);
                
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return clientes;
    }

    public int insert(Clientes cliente) { 
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getCodigo_clientes());
            stmt.setString(2, cliente.getNombre_clientes());
            stmt.setString(3, cliente.getNit_clientes());
            stmt.setString(4, cliente.getTipo_bodega());
            stmt.setString(5, cliente.getDireccion());

            System.out.println("ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Clientes cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, cliente.getNombre_clientes());
            stmt.setString(2, cliente.getNit_clientes());
            stmt.setString(3, cliente.getCodigo_clientes());
            stmt.setString(4, cliente.getTipo_bodega());
            stmt.setString(5, cliente.getDireccion());

            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado: " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Clientes cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, cliente.getCodigo_clientes());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public Clientes query(Clientes cliente) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Clientes> clientes = new ArrayList<Clientes>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, cliente.getCodigo_clientes());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String codigoCliente = rs.getString("codigo_clientes");
                String nombreClientes = rs.getString("nombre_clientes");
                String nitClientes = rs.getString("nit_clientes");
                String tipo_bodega = rs.getString("tipo_bodega");
                String direccion = rs.getString("direccion");
                
                cliente = new Clientes();
                cliente.setCodigo_clientes(codigoCliente);
                cliente.setNombre_clientes(nombreClientes);
                cliente.setNit_clientes(nitClientes);
                cliente.setTipo_bodega(tipo_bodega);
                cliente.setDireccion(direccion);


            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cliente; 
    }
         public void imprimirReporte() {
        Connection conn = null;
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            conn = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/reportes/"+ "Reporte.jrxml");
            print = JasperFillManager.fillReport(report, p, conn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Clientes");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
}
