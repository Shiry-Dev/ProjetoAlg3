/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.arquivo;

import Universidade.entidades.Curso;
import Universidade.persistencia.CursoDAO;
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
public class CursoDAOImpl implements CursoDAO {
    private final String filename = "curso.dat";
	private List<Curso> cursos = new ArrayList<Curso>();
	
	private void saveArquivo(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(cursos);
			oos.close();
		}catch(FileNotFoundException ex){
	Logger.getLogger(CursoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex){
			Logger.getLogger(CursoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadArquivo(){
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			try {
				cursos = (List<Curso>) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ois.close();
		}catch(FileNotFoundException ex){
	Logger.getLogger(CursoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex){
			Logger.getLogger(CursoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void inserir (Curso curso){
		loadArquivo();
		boolean existe = false;
		for(Curso c : cursos){
			if(c.getId() == curso.getId()){
				existe = true;
				break;
			}
		}if(!existe){
			cursos.add(curso);
			saveArquivo();
			System.out.println("Curso cadastrado!");
		}else{
			System.out.println("Curso já cadastrado");
		}
	}
	
	public void editar(Curso curso){
		loadArquivo();
		for(int i = 0; i < cursos.size(); i++) {
			Curso c = cursos.get(i);
			if(c.getId() == curso.getId()) {
					cursos.set(i, curso);
					saveArquivo();
					break;
			}
		}
		return;
	}
	
	public boolean remover(int id){
		loadArquivo();
		for(int i = 0; i < cursos.size(); i++) {
			Curso c = cursos.get(i);
			if(c.getId() == id) {
				cursos.remove(i);
				saveArquivo();
				return true;
			}
		}return false;
	}
	
	public Curso getById(int id){
		loadArquivo();
		for(Curso c : cursos) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	public List<Curso> listar(){
		loadArquivo();
		return cursos;
	}
}
