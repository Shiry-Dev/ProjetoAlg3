package Universidade.entidades;

import java.io.Serializable;

/**
 *
 * @author garrien
 */
public class Aluno extends Base  implements Serializable{
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String idade;
    private long rga;
    private String endereco;
    /*private Curso curso;
    private Disciplina disciplina;*/
    
    public String getNome(){
    	return nome;
    }

	public void setNome(String nome){
	    this.nome = nome;
	}
	
	public String getEmail(){
	    return email;
	}
	
	public void setEmail(String email){
	    this.email = email;
	}
	
	public String getCpf(){
    	return cpf;
	}

	public void setCpf(String cpf){
    	this.cpf = cpf;
	}

	public String getTelefone(){
    	return telefone;
	}

	public void setTelefone(String telefone){
    	this.telefone = telefone;
	}

	public String getIdade(){
		return idade;
	}

	public void setIdade(String idade){
		this.idade = idade;
	}

	public long getRga(){
		return rga;
	}

	public void setRga(long rga){
		this.rga = rga;
	}

	public String getEndereco(){
		return endereco;
	}

	public void setEndereco(String endereco){
		this.endereco = endereco;
	}
	
	/*public Curso getCurso(){
		return curso;
	}

	public void setCurso(Curso curso){
		this.curso = curso;
	}
	
	public Disciplina getDisciplina(){
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina){
		this.disciplina = disciplina;
	}	

    public void setAluno(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
