/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.arquivo;

import Universidade.entidades.Departamento;
import Universidade.persistencia.DepartamentoDAO;
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
public class DepartamentoDAOImpl implements DepartamentoDAO {
    private final String filename = "departamento.dat";
	private List<Departamento> departamentos = new ArrayList<Departamento>();
	
	private void saveArquivo(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(departamentos);
			oos.close();
		}catch(FileNotFoundException ex){
	Logger.getLogger(DepartamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex){
			Logger.getLogger(DepartamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadArquivo(){
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			try {
				departamentos = (List<Departamento>) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ois.close();
		}catch(FileNotFoundException ex){
	Logger.getLogger(DepartamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex){
			Logger.getLogger(DepartamentoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void inserir (Departamento departamento){
		loadArquivo();
		boolean existe = false;
		for(Departamento d : departamentos){
			if(d.getId() == departamento.getId()){
				existe = true;
				break;
			}
		}if(!existe){
			departamentos.add(departamento);
			saveArquivo();
			System.out.println("Departamento cadastrado!");
		}else{
			System.out.println("Departamento já cadastrado");
		}
	}
	
	public void editar(Departamento departamento){
		loadArquivo();
		for(int i = 0; i < departamentos.size(); i++) {
			Departamento d = departamentos.get(i);
			if(d.getId() == departamento.getId()) {
					departamentos.set(i, departamento);
					saveArquivo();
					break;
			}
		}
		return;
	}
	
	public boolean remover(int id){
		loadArquivo();
		for(int i = 0; i < departamentos.size(); i++) {
			Departamento d = departamentos.get(i);
			if(d.getId() == id) {
				departamentos.remove(i);
				saveArquivo();
				return true;
			}
		}return false;
	}
	
	public Departamento getById(int id){
		loadArquivo();
		for(Departamento d : departamentos) {
			if(d.getId() == id) {
				return d;
			}
		}
		return null;
	}
	public List<Departamento> listar(){
		loadArquivo();
		return departamentos;
	}
}
