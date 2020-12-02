/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia;

import Universidade.entidades.Disciplina;
import java.util.List;

/**
 *
 * @author garrien
 */
public interface DisciplinaDAO {
        public void inserir(Disciplina disciplina);
	public void editar (Disciplina disciplina);
	public boolean remover(int id);
	public Disciplina getById(int id);
	public List<Disciplina>listar();
}
