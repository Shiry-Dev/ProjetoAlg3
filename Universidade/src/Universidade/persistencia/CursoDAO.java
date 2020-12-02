/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia;

import Universidade.entidades.Curso;
import java.util.List;

/**
 *
 * @author garrien
 */
public interface CursoDAO {
        public void inserir(Curso curso);
	public void editar(Curso curso);
	public boolean remover(int id);
	public Curso getById(int id);
	public List<Curso>listar();
}
