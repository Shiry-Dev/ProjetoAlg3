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
public class Disciplina extends Base  implements Serializable{
    private String nome;
    private String status;//conluida, matriculado, não matriculado, não disponível
    private String tipo;//obrigatoria, optativa
    /*private Aluno aluno;
    private Curso curso;*/
  
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}
	    
	public String getTipo(){
		return tipo;
	}

	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	/*public Aluno getAluno(){
		return aluno;
	}

	public void setAluno(Aluno aluno){
		this.aluno = aluno;
	}

   	public Curso getCurso(){
    		return curso;
    	}
    
    	public void setCurso(Curso curso){
    		this.curso = curso;
    	}

        public void setDisciplina(String text) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }*/
}
