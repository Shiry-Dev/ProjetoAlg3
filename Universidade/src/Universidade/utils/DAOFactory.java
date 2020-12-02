/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Universidade.utils;

import Universidade.persistencia.AlunoDAO;
import Universidade.persistencia.CursoDAO;
import Universidade.persistencia.DepartamentoDAO;
import Universidade.persistencia.DisciplinaDAO;
import Universidade.persistencia.FuncionarioDAO;
import Universidade.persistencia.arquivo.AlunoDAOImpl;
import Universidade.persistencia.arquivo.CursoDAOImpl;
import Universidade.persistencia.arquivo.DepartamentoDAOImpl;
import Universidade.persistencia.arquivo.DisciplinaDAOImpl;
import Universidade.persistencia.arquivo.FuncionarioDAOImpl;
import Universidade.persistencia.postgresql.AlunoDAOPostgreSql;
import Universidade.persistencia.postgresql.CursoDAOPostgreSql;
import Universidade.persistencia.postgresql.DepartamentoDAOPostgreSql;
import Universidade.persistencia.postgresql.DisciplinaDAOPostgreSql;
import Universidade.persistencia.postgresql.FuncionarioDAOPostgreSql;

/**
 *
 * @author garrien
 */
public class DAOFactory {
    public static AlunoDAO createAlunoDAO(){
        return new AlunoDAOPostgreSql();
    }
    
    public static CursoDAO createCursoDAO(){
        return new CursoDAOPostgreSql();
    }
    
    public static DepartamentoDAO createDepartamentoDAO(){
        return new DepartamentoDAOPostgreSql();
    }
    
    public static DisciplinaDAO createDisciplinaDAO(){
        return new DisciplinaDAOPostgreSql();
    }
    
    public static FuncionarioDAO createFuncionarioDAO(){
        return new FuncionarioDAOPostgreSql();
    }
}
