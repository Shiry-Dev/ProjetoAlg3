/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.arquivo;

import Universidade.entidades.Funcionario;
import Universidade.persistencia.FuncionarioDAO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author garrien
 */
public class FuncionarioDAOImpl implements FuncionarioDAO{
    private final String filename = "funcionario.dat";
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	private void saveArquivo(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(funcionarios);
			oos.close();
		}catch(FileNotFoundException ex){
	Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex){
			Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadArquivo(){
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			try {
				funcionarios = (List<Funcionario>) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ois.close();
		}catch(FileNotFoundException ex){
	Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex){
			Logger.getLogger(FuncionarioDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void inserir (Funcionario funcionario){
		loadArquivo();
		boolean existe = false;
		for(Funcionario f : funcionarios){
			if(f.getId() == funcionario.getId()){
				existe = true;
				break;
			}
		}if(!existe){
			funcionarios.add(funcionario);
			saveArquivo();
			System.out.println("Funcionário cadastrado!");
		}else{
			System.out.println("Funcionário já cadastrado");
		}
	}
	
	public void editar(Funcionario funcionario){
		loadArquivo();
		for(int i = 0; i < funcionarios.size(); i++) {
			Funcionario f = funcionarios.get(i);
			if(f.getId() == funcionario.getId()) {
					funcionarios.set(i, funcionario);
					saveArquivo();
					break;
			}
		}
		return;
	}
	
	public boolean remover(int id){
		loadArquivo();
		for(int i = 0; i < funcionarios.size(); i++) {
			Funcionario f = funcionarios.get(i);
			if(f.getId() == id) {
				funcionarios.remove(i);
				saveArquivo();
				return true;
			}
		}return false;
	}
	
	public Funcionario getById(int id){
		loadArquivo();
		for(Funcionario f : funcionarios) {
			if(f.getId() == id) {
				return f;
			}
		}
		return null;
	}
	public List<Funcionario> listar(){
		loadArquivo();
		return funcionarios;
	}
}
