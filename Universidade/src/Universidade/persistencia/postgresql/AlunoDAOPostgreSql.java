/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.postgresql;

import Universidade.entidades.Aluno;
import Universidade.persistencia.AlunoDAO;
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
public class AlunoDAOPostgreSql implements AlunoDAO{

    private Connection con;
    
    private void abreConexao(){
        try{
            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:62719:5432","db_algoritmos","senha");
        }catch(SQLException ex){
            Logger.getLogger(AlunoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void fecharConexao(){
        try{
            con.close();
        }catch(SQLException ex){
            Logger.getLogger(AlunoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    @Override
    public void inserir(Aluno aluno) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{ 
            abreConexao();
            String sql ="INSERT INTO aluno(nome, email, cpf, telefone, idade, endereco) VALUES(";
            sql += "'" + aluno.getNome() + "',";
            sql += "'" + aluno.getEmail() + "',";
            sql += "'" + aluno.getCpf() + "',"; 
            sql += "'" + aluno.getTelefone() + "',";
            sql += "'" + aluno.getIdade() + "',";
            sql += "'" + aluno.getEndereco() + "'";
            sql += ");";

            con.createStatement().execute(sql);
            System.out.println("Salvo com Sucesso!");
            System.out.println(sql);

            fecharConexao();
        }catch(SQLException ex){
            Logger.getLogger(AlunoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editar(Aluno aluno) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            abreConexao();
            String sql = "UPDATE aluno SET nome = ?,email = ?, cpf = ?, telefone = ?, idade = ?, endereco = ? WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            
            pstm.setString(1, aluno.getNome());
            pstm.setString(2,aluno.getEmail());
            pstm.setString(3, aluno.getCpf());
            pstm.setString(4, aluno.getTelefone());
            pstm.setString(5, aluno.getIdade());
            pstm.setString(6,aluno.getEndereco());
            pstm.setInt(7, aluno.getId());
            
            pstm.execute();
            
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean remover(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            abreConexao();
            String sql = "DELETE FROM aluno WHERE id = " + id;
            con.createStatement().executeUpdate(sql);
            fecharConexao();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Aluno getById(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Aluno a = null;
        try {
            abreConexao();
            String sql = "SELECT * FROM aluno WHERE id = ?;";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                a = new Aluno();
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));
                a.setCpf(rs.getString("cpf"));
                a.setTelefone(rs.getString("telefone"));
                a.setIdade(rs.getString("idade"));
                a.setEndereco(rs.getString("endereço"));
                a.setId(rs.getInt("id"));
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public List<Aluno> listar() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Aluno> lista = new ArrayList<Aluno>();
        try {
            abreConexao();
            String sql = "SELECT * FROM Aluno;";
 
            ResultSet rs = con.createStatement().executeQuery(sql);
            
            while(rs.next()){
                Aluno a = new Aluno();
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));
                a.setCpf(rs.getString("cpf"));
                a.setTelefone(rs.getString("telefone"));
                a.setIdade(rs.getString("idade"));
                a.setEndereco(rs.getString("endereço"));
                a.setId(rs.getInt("id"));
                lista.add(a);
            }
            rs.close();
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAOPostgreSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    
    }
    
}
