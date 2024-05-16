package SistemaAcademico;

import org.jetbrains.annotations.NotNull;
final public class AlunoGraduacao extends Aluno {
    public AlunoGraduacao(@NotNull final String nome, @NotNull final String cpf, @NotNull final String matricula, @NotNull final String telefone, @NotNull final String email, @NotNull final String endereco) {
        super(nome, cpf, matricula, telefone, email, endereco);
        this.duracaoDoCurso = 2.5F;
        Disciplina pi = new Disciplina("PI", "123");
        this.inscreverEmDisciplina(pi);
    }

    public AlunoGraduacao() {
        super();
        this.duracaoDoCurso = 2.5F;
        Disciplina pi = new Disciplina("PI", "123");
        this.inscreverEmDisciplina(pi);
    }

    @Override
    public void inscreverEmDisciplina(@NotNull final Disciplina disciplina) {
        if ("TCC".equalsIgnoreCase(disciplina.getNome())) {
            System.err.printf("O aluno %s não pode se inscrever na disciplina %s\n", this.getNome(), disciplina.getNome());
        } else {
            super.inscreverEmDisciplina(disciplina);
        }
    }

}
