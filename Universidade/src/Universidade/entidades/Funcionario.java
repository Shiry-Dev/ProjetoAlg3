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
public class Funcionario extends Base  implements Serializable{
    	private String nome;
	private String codigo;
        private String cargo;//professor,  prof.substituto, coordenardo, zelador, tia da limpeza, técnicos, guardinha e etc.
        //private Departamento departamento;

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
	     
	public String getCargo(){
	    return cargo;
	}
	    
	public void setCargo(String cargo){
	    	this.cargo = cargo;
	}
	    
	/*public Departamento getDepartamento(){
	    	return departamento;
	}
	    
	public void setDepartamento(Departamento departamento){
	    this.departamento = departamento;
	}

        public void setFuncionario(String text) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }*/
}
