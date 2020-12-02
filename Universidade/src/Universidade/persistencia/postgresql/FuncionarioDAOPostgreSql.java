/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.postgresql;

import Universidade.entidades.Funcionario;
import Universidade.persistencia.FuncionarioDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author garrien
 */
public class FuncionarioDAOPostgreSql  implements FuncionarioDAO{

    private Connection con;
    
    private void abreConexao(){
        try{
            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:62719:5432","db_algoritmos","senha");
        }catch(SQLException ex){
            Logger.getLogger(FuncionarioDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void fecharConexao(){
        try{
            con.close();
        }catch(SQLException ex){
            Logger.getLogger(FuncionarioDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(Funcionario funcionario) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{ 
            abreConexao();
            String sql ="INSERT INTO funcionario(nome, codigo, cargo) VALUES(";
            sql += "'" + funcionario.getNome() + "',";
            sql += "'" + funcionario.getCodigo() + "',";
            sql += "'" + funcionario.getCargo() + "',";
            sql += ");";

            con.createStatement().execute(sql);
            System.out.println("Salvo com Sucesso!");
            System.out.println(sql);

            fecharConexao();
        }catch(SQLException ex){
            Logger.getLogger(FuncionarioDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editar(Funcionario funcionario) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            abreConexao();
            String sql = "UPDATE funcionario SET nome = ?, codigo = ?, cargo = ? WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setString(1, funcionario.getNome());
            pstm.setString(2, funcionario.getCodigo());
            pstm.setString(3, funcionario.getCargo());
            pstm.setInt(4, funcionario.getId());
            
            pstm.execute();
            
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean remover(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            abreConexao();
            String sql = "DELETE FROM funcionario WHERE id = " + id;
            con.createStatement().executeUpdate(sql);
            fecharConexao();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Funcionario getById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Funcionario f = null;
        try {
            abreConexao();
            String sql = "SELECT * FROM funcionario WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("codigo"));
                f.setCodigo(rs.getString("cargo"));
                f.setId(rs.getInt("id"));
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    @Override
    public List<Funcionario> listar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Funcionario> lista = new ArrayList<Funcionario>();
        try {
            abreConexao();
            String sql = "SELECT * FROM Funcionario;";
 
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setCodigo(rs.getString("codigo"));
                f.setCargo(rs.getString("cargo"));
                f.setId(rs.getInt("id"));
                lista.add(f);
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    
    }
    
   
    
    
}
