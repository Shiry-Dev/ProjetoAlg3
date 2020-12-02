/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia;

import Universidade.entidades.Departamento;
import java.util.List;

/**
 *
 * @author garrien
 */
public interface DepartamentoDAO {
        public void inserir(Departamento departamento);
	public void editar (Departamento departamento);
	public boolean remover(int id);
	public Departamento getById(int id);
	public List<Departamento>listar();
}
