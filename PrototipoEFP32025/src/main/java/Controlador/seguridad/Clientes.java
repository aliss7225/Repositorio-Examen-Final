/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.seguridad;

/**
 *
 * @author cdavi
 */
public class Clientes {

    private String codigo_clientes;
    private String nombre_clientes;
    private String nit_clientes;
    private String tipo_bodega;
    private String direccion;

    public Clientes(String codigo_clientes, String nombre_clientes, String nit_clientes,String tipo_bodega, String direccion) {
        this.codigo_clientes = codigo_clientes;
        this.nombre_clientes = nombre_clientes;
        this.nit_clientes = nit_clientes;
        this.tipo_bodega = tipo_bodega;
        this.direccion = direccion;
    }

 

    @Override
    public String toString() {
        return "Clientes{" + "codigo_clientes=" + codigo_clientes + ", nombre_clientes=" + nombre_clientes + ", nit_clientes=" + nit_clientes + ", tipo_bodega=" + tipo_bodega + ", direccion=" + direccion +'}';
    }
       public String getTipo_bodega() {
        return tipo_bodega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setTipo_bodega(String tipo_bodega) {
        this.tipo_bodega = tipo_bodega;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigo_clientes() {
        return codigo_clientes;
    }

    public void setCodigo_clientes(String codigo_clientes) {
        this.codigo_clientes = codigo_clientes;
    }

    public String getNombre_clientes() {
        return nombre_clientes;
    }

    public void setNombre_clientes(String nombre_clientes) {
        this.nombre_clientes = nombre_clientes;
    }

    public String getNit_clientes() {
        return nit_clientes;
    }

    public void setNit_clientes(String nit_clientes) {
        this.nit_clientes = nit_clientes;
    }

    public Clientes() {
    }
}
