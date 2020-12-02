/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.persistencia;

import Universidade.entidades.Funcionario;
import java.util.List;

/**
 *
 * @author garrien
 */
public interface FuncionarioDAO {
        public void inserir(Funcionario funcionario);
	public void editar (Funcionario funcionario);
	public boolean remover(int id);
	public Funcionario getById(int id);
	public List<Funcionario>listar();
}
