/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.postgresql;

import Universidade.entidades.Departamento;
import Universidade.persistencia.DepartamentoDAO;
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
public class DepartamentoDAOPostgreSql implements DepartamentoDAO{

    private Connection con;
    
    private void abreConexao(){
        try{
            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:62719:5432","db_algoritmos","senha");
        }catch(SQLException ex){
            Logger.getLogger(DepartamentoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void fecharConexao(){
        try{
            con.close();
        }catch(SQLException ex){
            Logger.getLogger(DepartamentoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void inserir(Departamento departamento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{ 
            abreConexao();
            String sql ="INSERT INTO departamento(nome, codigo) VALUES(";
            sql += "'" + departamento.getNome() + "',";
            sql += "'" + departamento.getCodigo() + "',";
            sql += ");";

            con.createStatement().execute(sql);
            System.out.println("Salvo com Sucesso!");
            System.out.println(sql);

            fecharConexao();
        }catch(SQLException ex){
            Logger.getLogger(DepartamentoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editar(Departamento departamento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            abreConexao();
            String sql = "UPDATE departamento SET nome = ?,codigo = ? WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setString(1, departamento.getNome());
            pstm.setString(2, departamento.getCodigo());
            pstm.setInt(3, departamento.getId());
            
            pstm.execute();
            
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean remover(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try {
            abreConexao();
            String sql = "DELETE FROM departamento WHERE id = " + id;
            con.createStatement().executeUpdate(sql);
            fecharConexao();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }

    @Override
    public Departamento getById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Departamento d = null;
        try {
            abreConexao();
            String sql = "SELECT * FROM departamento WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                d = new Departamento();
                d.setNome(rs.getString("nome"));
                d.setCodigo(rs.getString("codigo"));
                d.setId(rs.getInt("id"));
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    @Override
    public List<Departamento> listar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Departamento> lista = new ArrayList<Departamento>();
        try {
            abreConexao();
            String sql = "SELECT * FROM Departamento;";
 
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                Departamento d = new Departamento();
                d.setNome(rs.getString("nome"));
                d.setCodigo(rs.getString("codigo"));
                d.setId(rs.getInt("id"));
                lista.add(d);
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
