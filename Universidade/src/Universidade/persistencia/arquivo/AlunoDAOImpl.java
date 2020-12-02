/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia.arquivo;

import Universidade.entidades.Aluno;
import Universidade.persistencia.AlunoDAO;
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
public class AlunoDAOImpl implements AlunoDAO{
    private final String filename = "aluno.dat";
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	private void saveArquivo(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(alunos);
			oos.close();
		}catch(FileNotFoundException ex){
	Logger.getLogger(AlunoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}catch(IOException ex){
			Logger.getLogger(AlunoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadArquivo(){
		try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            alunos = (List<Aluno>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            Logger.getLogger(AlunoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
        @Override
	public void inserir (Aluno aluno){
		loadArquivo();
		boolean existe = false;
		for(Aluno a : alunos){
			if(a.getId() == aluno.getId()){
				existe = true;
				break;
			}
		}if(!existe){
			alunos.add(aluno);
			saveArquivo();
			System.out.println("Aluno cadastrado!");
		}else{
			System.out.println("Aluno já cadastrado");
		}
	}
	
	public void editar(Aluno aluno){
		loadArquivo();
		for(int i = 0; i < alunos.size(); i++) {
			Aluno a = alunos.get(i);
			if(a.getId() == aluno.getId()) {
					alunos.set(i, aluno);
					saveArquivo();
					break;
			}
		}
		return;
	}
	
	public boolean remover(int id){
		loadArquivo();
		for(int i = 0; i < alunos.size(); i++) {
			Aluno a = alunos.get(i);
			if(a.getId() == id) {
				alunos.remove(i);
				saveArquivo();
				return true;
			}
		}
                return false;
	}
	
	public Aluno getById(int id){
		loadArquivo();
		for(Aluno a : alunos) {
			if(a.getId() == id) {
				return a;
			}
		}
		return null;
	}
    @Override
	public List<Aluno> listar(){
		loadArquivo();
		return alunos;
	}
    
    
}
