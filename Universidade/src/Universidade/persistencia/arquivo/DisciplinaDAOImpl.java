/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.arquivo;

import Universidade.entidades.Disciplina;
import Universidade.persistencia.DisciplinaDAO;
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
public class DisciplinaDAOImpl implements DisciplinaDAO {
    private final String filename = "disciplina.dat";
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	private void saveArquivo(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(disciplinas);
			oos.close();
		}catch(FileNotFoundException ex){
	Logger.getLogger(DisciplinaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex){
			Logger.getLogger(DisciplinaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadArquivo(){
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			try {
				disciplinas = (List<Disciplina>) ois.readObject();
			} catch (ClassNotFoundException e) {
			}
			ois.close();
		}catch(FileNotFoundException ex){
	Logger.getLogger(DisciplinaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex){
			Logger.getLogger(DisciplinaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
        @Override
	public void inserir (Disciplina disciplina){
		loadArquivo();
		boolean existe = false;
		for(Disciplina d2 : disciplinas){
			if(d2.getId() == disciplina.getId()){
				existe = true;
				break;
			}
		}if(!existe){
			disciplinas.add(disciplina);
			saveArquivo();
			System.out.println("Disciplina cadastrado!");
		}else{
			System.out.println("Disciplina já cadastrado");
		}
	}
	
	public void editar(Disciplina disciplina){
		loadArquivo();
		for(int i = 0; i < disciplinas.size(); i++) {
			Disciplina d2 = disciplinas.get(i);
			if(d2.getId() == disciplina.getId()) {
					disciplinas.set(i, disciplina);
					saveArquivo();
					break;
			}
		}
		return;
	}
	
	public boolean remover(int id){
		loadArquivo();
		for(int i = 0; i < disciplinas.size(); i++) {
			Disciplina d2 = disciplinas.get(i);
			if(d2.getId() == id) {
				disciplinas.remove(i);
				saveArquivo();
				return true;
			}
		}return false;
	}
	
	public Disciplina getById(int id){
		loadArquivo();
		for(Disciplina d2 : disciplinas) {
			if(d2.getId() == id) {
				return d2;
			}
		}
		return null;
	}
	public List<Disciplina> listar(){
		loadArquivo();
		return disciplinas;
	}
}
