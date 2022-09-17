/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge
 */
public class cliente extends persona{
    private String nit;
        Conexion cn;
    public cliente(){ }
    
    public cliente(String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super( nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    public DefaultTableModel leer (){
        DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn= new Conexion ();
        cn.abrir_conexion();
        System.out.println("ENTRO " );
        String query;
        query = "select id_clientes as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento from clientes;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        
        String encabezado[] = {"id","Nit","Nombres","Apellidos","Direccion","telefono","fecha_nacimiento"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[]=new String[7];
        
        while(consulta.next()){
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("Nit");
            datos[2] = consulta.getString("Nombres");
            datos[3] = consulta.getString("apellidos");
            datos[4] = consulta.getString("direccion");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("fecha_nacimiento");
            tabla.addRow(datos);
            }
        cn.cerrar_conexion();
        
    } catch(SQLException ex){
        cn.cerrar_conexion();
        System.out.println("Error2: " + ex.getMessage() );
        
    }
    return tabla;
    }
    @Override
    public void agregar(){
      try{
         PreparedStatement parametro;
         String query ="INSERT INTO clientes(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) VALUES(?,?,?,?,?,?);";
    cn = new Conexion();
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
    parametro.setString(1, getNit());
    parametro.setString(2, getNombres());
    parametro.setString(3, getApellidos());
    parametro.setString(4, getDireccion());
    parametro.setString(5, getTelefono());
    int executar = parametro.executeUpdate();
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null,Integer.toString(executar)+ " Registro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);

            }catch(HeadlessException | SQLException Ex){
          System.out.println("Error..."+ Ex.getMessage());
      }  
    }
    
    
    public void Agregar(){
    System.out.println("Nit: " + getNit());
    System.out.println("Nombres: " + getNombres());
    System.out.println("Apellidos: " + getApellidos());
    System.out.println("Direccion: " + getDireccion());
    System.out.println("Telefono: " + getTelefono());
    System.out.println("Fecha_nacimiento: " + getFecha_nacimiento());
    System.out.println ("______________________________");
    }
}
