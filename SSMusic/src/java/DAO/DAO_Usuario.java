/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.DBUtil;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scardonas
 */
public class DAO_Usuario implements IUsuario_DAO {

    @Override
    public boolean setUser(Usuario user) {
        boolean registrar = false;

        Connection con = null;
        String sql = "INSERET INTO USUARIO"
                + "("
                + "ID_PERFIL, "
                + "TIPO_PERFIL, "
                + "USERNAME, "
                + "PASS, "
                + "NOMBRES, "
                + "APELLIDO1, "
                + "APELLIDO2, "
                + "FECHA_REGISTRO"
                + ") "
                + "VALUES(?,?,?,?,?,?,?,?)";

        try {
            con = DBUtil.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setNull(1, 0);
            ps.setString(2, user.getTipo_perfil());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPass());
            ps.setString(5, user.getNombres());
            ps.setString(6, user.getApellido1());
            ps.setString(7, user.getApellido2());
            ps.setString(8, "TO_DATE('" + user.getFecha_registro() + "', 'dd-MM-yyyy')");
            ps.executeQuery();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DAO_Usuario, método registrar");
            e.printStackTrace();
        }
        registrar = true;
        return registrar;
    }

    @Override
    public List<Usuario> getUser() {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM USUARIO ORDER BY TIPO_PERFIL";

        List<Usuario> listaUsuario = new ArrayList<Usuario>();

        try {
            co = DBUtil.getConexion();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("ID_PERFIL"));
                u.setTipo_perfil(rs.getString("TIPO_PERFIL"));
                u.setUsername(rs.getString("USERNAME"));
                u.setPass(rs.getString("PASS"));
                u.setNombres(rs.getString("NOMBRES"));
                u.setApellido1(rs.getString("APELLIDO1"));
                u.setApellido2(rs.getString("APELLIDO2"));
                u.setDocumento(rs.getString("DOCUMENTO"));
                u.setCorreo(rs.getString("CORREO"));
                u.setTelefono(rs.getString("TELEFONO"));
                u.setDireccion(rs.getString("DIRECCION"));
                u.setFecha_registro(rs.getString("FECHA_REGISTRO"));
                listaUsuario.add(u);
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DAO_USUARIO, método obtener");
            e.printStackTrace();
        }

        return listaUsuario;
    }

    @Override
    public boolean updateUser(Usuario user) {
        boolean actualizar = false;
        Connection con = null;

        String sql = "UPDATE USUARIO SET TIPO_PERFIL = ?, "
                + "PASS = ?, "
                + "NOMBRES = ?, "
                + "APELLIDO1 = ?, "
                + "APELLIDO2 = ?, "
                + "CORREO = ?, "
                + "TELEFONO = ?, "
                + "DIRECCION = ? "
                + "WHERE DOCUMENTO = ?";
        try {
            con = DBUtil.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getTipo_perfil());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getNombres());
            ps.setString(4, user.getApellido1());
            ps.setString(5, user.getApellido2());
            ps.setString(6, user.getCorreo());
            ps.setString(7, user.getTelefono());
            ps.setString(8, user.getDireccion());
            ps.setString(9, user.getDocumento());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase DAO_USUARIO, método actualizar");
            e.printStackTrace();
        }
        actualizar = true;
        return actualizar;
    }

    @Override
    public boolean deleteUser(Usuario user) {
        boolean eliminar = false;
        Connection con = null;

        String sql = "DELETE FROM USUARIO WHERE DOCUMENTO = ?";
        
        try {
            con = DBUtil.getConexion();;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getDocumento());
            ps.executeQuery();
            ps.close();
            eliminar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase ClienteDaoImple, método eliminar");
            e.printStackTrace();
        }
        return eliminar;

    }

}
