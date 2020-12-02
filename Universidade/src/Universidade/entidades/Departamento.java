/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.entidades;

import java.io.Serializable;

/**
 *
 * @author garrien
 */
public class Departamento extends Base  implements Serializable{
    private String nome;
    private String codigo;
    //private Funcionario funcionario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCodigo(){
    	return codigo;
    }
    
    public void setCodigo(String codigo){
    	this.codigo = codigo;
    }
    
    /*public Funcionario getFuncionario(){
    	return funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario){
    	this.funcionario = funcionario;
    } 

    public void setDepartamento(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
