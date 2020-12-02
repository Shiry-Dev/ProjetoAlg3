/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.postgresql;

import Universidade.entidades.Disciplina;
import Universidade.persistencia.DisciplinaDAO;
import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author garrien
 */
public class DisciplinaDAOPostgreSql implements DisciplinaDAO {
    
    private Connection con;
    
    private void abreConexao(){
        try{
            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:62719:5432","db_algoritmos","senha");
        }catch(SQLException ex){
            Logger.getLogger(DisciplinaDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void fecharConexao(){
        try{
            con.close();
        }catch(SQLException ex){
            Logger.getLogger(DisciplinaDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void inserir(Disciplina disciplina) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{ 
            abreConexao();
            String sql ="INSERT INTO disciplina(nome, status, tipo) VALUES(";
            sql += "'" + disciplina.getNome() + "',";
            sql += "'" + disciplina.getStatus() + "',";
            sql += "'" + disciplina.getTipo() + "',";
            sql += ");";

            con.createStatement().execute(sql);
            System.out.println("Salvo com Sucesso!");
            System.out.println(sql);

            fecharConexao();
        }catch(SQLException ex){
            Logger.getLogger(DisciplinaDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editar(Disciplina disciplina) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            abreConexao();
            String sql = "UPDATE disciplina SET nome = ?, status = ?, tipo = ? WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setString(1, disciplina.getNome());
            pstm.setString(2, disciplina.getStatus());
            pstm.setString(3, disciplina.getTipo());
            pstm.setInt(4, disciplina.getId());
            
            pstm.execute();
            
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean remover(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            abreConexao();
            String sql = "DELETE FROM disciplina WHERE id = " + id;
            con.createStatement().executeUpdate(sql);
            fecharConexao();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Disciplina getById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Disciplina d2 = null;
        try {
            abreConexao();
            String sql = "SELECT * FROM disciplina WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                d2 = new Disciplina();
                d2.setNome(rs.getString("nome"));
                d2.setStatus(rs.getString("status"));
                d2.setTipo(rs.getString("tipo"));
                d2.setId(rs.getInt("id"));
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d2;
    }

    @Override
    public List<Disciplina> listar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Disciplina> lista = new ArrayList<Disciplina>();
        try {
            abreConexao();
            String sql = "SELECT * FROM Disciplina;";
 
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                Disciplina d2 = new Disciplina();
                d2.setNome(rs.getString("nome"));
                d2.setStatus(rs.getString("status"));
                d2.setTipo(rs.getString("tipo"));
                d2.setId(rs.getInt("id"));
                lista.add(d2);
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    
    }
    
}
