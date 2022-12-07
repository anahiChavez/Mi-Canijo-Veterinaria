package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProveedorDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedor(rfc, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getRfc());
            ps.setString(2, pr.getNombre());
            ps.setLong(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.execute();
            return true;
        } catch(SQLException e){
            System.out.println(e.toString());
            return false;
        } finally{
            try{
                con.close();
            } catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarProveedor() {
        List<Proveedor> ListaPro = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor pro = new Proveedor();
                pro.setId(rs.getInt("IdProveedor"));
                pro.setRfc(rs.getString("rfc"));
                pro.setNombre(rs.getString("nombre"));
                pro.setTelefono(rs.getLong("telefono"));
                pro.setDireccion(rs.getString("direccion"));
                pro.setRazon(rs.getString("razon"));
                ListaPro.add(pro);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPro;
    }
    
    public boolean EliminarProveedor(int id) {
        String sql = "DELETE FROM proveedor WHERE IdProveedor = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

    }

    public boolean ModificarProveedor(Proveedor pr ) {
        String sql = "UPDATE proveedor SET rfc=?, nombre=?, telefono=?, direccion=?, razon=? WHERE IdProveedor =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getRfc());
            ps.setString(2, pr.getNombre());
            ps.setLong(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.setInt(6, pr.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }
}

