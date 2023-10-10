package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlunoTest {

    @Test
    public void deveriaSetarMatricula() {
        Aluno aluno = new Aluno();
        String matricula = "12345678912";

        aluno.setMatricula(matricula);

        Assertions.assertEquals(matricula, aluno.getMatricula());
    }

    @Test
    public void naoDeveriaSetarMatriculaAbaixoDe11Caracteres() {
        Aluno aluno = new Aluno();
        String matriculaCom10Caracteres = "1234567891";

        Assertions.assertThrows(RuntimeException.class, () -> aluno.setMatricula(matriculaCom10Caracteres));
    }

    @Test
    public void naoDeveriaSetarMatriculaAcimaDe11Caracteres() {
        Aluno aluno = new Aluno();
        String matriculaCom12Caracteres = "123456789123";

        Assertions.assertThrows(RuntimeException.class, () -> aluno.setMatricula(matriculaCom12Caracteres));
    }

    @Test
    public void naoDeveriaSetarMatriculaComCaracteresEspeciais() {
        Aluno aluno = new Aluno();
        String matriculaComCaracteresEspeciais = "a234567891#";

        Assertions.assertThrows(RuntimeException.class, () -> aluno.setMatricula(matriculaComCaracteresEspeciais));
    }
}
