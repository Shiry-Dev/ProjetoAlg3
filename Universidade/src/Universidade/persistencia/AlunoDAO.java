/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia;

import Universidade.entidades.Aluno;
import java.util.List;

/**
 *
 * @author garrien
 */
public interface AlunoDAO {
        public void inserir(Aluno aluno);
	public void editar(Aluno aluno);
	public boolean remover(int id);
	public Aluno getById(int id);
	public List<Aluno> listar();
}
