/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.postgresql;

import Universidade.entidades.Curso;
import Universidade.persistencia.CursoDAO;
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
public class CursoDAOPostgreSql implements CursoDAO {
    
    private Connection con;
    
    private void abreConexao(){
        try{
            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:62719:5432","db_algoritmos","senha");
        }catch(SQLException ex){
            Logger.getLogger(CursoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void fecharConexao(){
        try{
            con.close();
        }catch(SQLException ex){
            Logger.getLogger(CursoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void inserir(Curso curso) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{ 
            abreConexao();
            String sql ="INSERT INTO curso(nome, codigo) VALUES(";
            sql += "'" + curso.getNome() + "',";
            sql += "'" + curso.getCodigo() + "',";
            sql += ");";

            con.createStatement().execute(sql);
            System.out.println("Salvo com Sucesso!");
            System.out.println(sql);

            fecharConexao();
        }catch(SQLException ex){
            Logger.getLogger(CursoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editar(Curso curso) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            abreConexao();
            String sql = "UPDATE curso SET nome = ?,codigo = ? WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setString(1, curso.getNome());
            pstm.setString(2, curso.getCodigo());
            pstm.setInt(3, curso.getId());
            
            pstm.execute();
            
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean remover(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            abreConexao();
            String sql = "DELETE FROM curso WHERE id = " + id;
            con.createStatement().executeUpdate(sql);
            fecharConexao();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Curso getById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Curso c = null;
        try {
            abreConexao();
            String sql = "SELECT * FROM curso WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                c = new Curso();
                c.setNome(rs.getString("nome"));
                c.setCodigo(rs.getString("codigo"));
                c.setId(rs.getInt("id"));
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public List<Curso> listar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Curso> lista = new ArrayList<Curso>();
        try {
            abreConexao();
            String sql = "SELECT * FROM Curso;";
 
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                Curso c = new Curso();
                c.setNome(rs.getString("nome"));
                c.setCodigo(rs.getString("codigo"));
                c.setId(rs.getInt("id"));
                lista.add(c);
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
