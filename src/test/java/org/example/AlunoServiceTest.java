package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoDAO alunoDAO;

    private Aluno aluno;

    @BeforeEach
    public void beforeEach() {
        aluno = new Aluno();
        aluno.setId(1);
        aluno.setNome("Darath Hamake");
        aluno.setMatricula("12345678912");
        aluno.setCpf("12345678912");
        aluno.setIdade(25);
    }

    @Test
    public void deveriaInserirAluno() {
        doReturn(false).when(alunoDAO).existeMatriculaAtiva(anyString());
        doReturn(false).when(alunoDAO).existeAlunoComCPF(anyString());

        alunoService.inserirAluno(aluno);

        Mockito.verify(alunoDAO).inserirAluno(aluno);
    }

    @Test
    public void naoDeveriaInserirAlunoComMatriculaJaExistente() {
        doReturn(true).when(alunoDAO).existeMatriculaAtiva(anyString());

        Assertions.assertThrows(IllegalArgumentException.class, () -> alunoService.inserirAluno(aluno));
    }

    @Test
    public void naoDeveriaInserirAlunoComCpfJaExistente() {
        doReturn(false).when(alunoDAO).existeMatriculaAtiva(anyString());
        doReturn(true).when(alunoDAO).existeAlunoComCPF(anyString());

        Assertions.assertThrows(IllegalArgumentException.class, () -> alunoService.inserirAluno(aluno));
    }

    @Test
    public void naoDeveriaInserirAlunoMenorDeIdade() {
        aluno.setIdade(15);

        doReturn(false).when(alunoDAO).existeMatriculaAtiva(anyString());
        doReturn(false).when(alunoDAO).existeAlunoComCPF(anyString());

        Assertions.assertThrows(IllegalArgumentException.class, () -> alunoService.inserirAluno(aluno));
    }

    @Test
    public void deveriaRemoverAluno() {

        alunoService.removerAluno(aluno.getId());

        Mockito.verify(alunoDAO).removerAluno(aluno.getId());
    }

    @Test
    public void deveriaAtualizarAluno() {

        alunoService.atualizarAluno(aluno);

        Mockito.verify(alunoDAO).atualizarAluno(aluno);
    }

    @Test
    public void deveriaRecuperarAlunoPorId() {

        alunoService.recuperarAlunoPorId(aluno.getId());

        Mockito.verify(alunoDAO).recuperarAlunoPorId(aluno.getId());
    }

    @Test
    public void deveriaListarTodosOsAlunos() {

        alunoService.listarTodosAlunos();

        Mockito.verify(alunoDAO).listarTodosAlunos();
    }
}
