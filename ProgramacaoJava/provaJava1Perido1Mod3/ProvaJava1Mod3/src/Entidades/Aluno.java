package Entidades;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public abstract class Aluno {
    protected Float duracaoDoCurso; // Anos
    protected HashSet<Disciplina> disciplinasInscrito = new HashSet<Disciplina>();

    private String nome;
    private String cpf;
    private String matricula;
    private String telefone;
    private String email;
    private String endereco;

    public Float getDuracaoDoCurso() {
        return duracaoDoCurso;
    }

    public HashSet<Disciplina> getDisciplinasInscrito() {
        return disciplinasInscrito;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "duracaoDoCurso=" + duracaoDoCurso +
                ", disciplinasInscrito=" + disciplinasInscrito +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", matricula='" + matricula + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    public void inscreverEmDisciplinas(@NotNull final HashSet<Disciplina> disciplinas) {
        for (Disciplina disciplina : disciplinas) {
            // Ocorreria uma validação aqui
            inscreverEmDisciplina(disciplina);
            System.out.printf("O aluno %s se inscreveu na disciplina: %s\n", this.getNome(), disciplina.getNome());
        }
    }

    public void inscreverEmDisciplina(@NotNull final Disciplina disciplina) {
        disciplinasInscrito.add(disciplina);
        System.out.printf("O aluno %s se inscreveu na disciplina: %s\n", this.getNome(), disciplina.getNome());
    }

    public void solicitarBoleto() {
        System.out.printf("A mensalidade é 900R$");
    }

    public void solicitarHistorico() {
        for (Disciplina disciplina : disciplinasInscrito) {
            System.out.printf("Nota na disciplina %s: 10\n", disciplina.getNome()); // Não tá implementado o sistema de notas...
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(@NotNull final String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull final String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(@NotNull final String matricula) {
        this.matricula = matricula;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotNull final String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull final String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotNull final String endereco) {
        this.endereco = endereco;
    }

    public Aluno(@NotNull final String nome, @NotNull final String cpf, @NotNull final String matricula, @NotNull final String telefone, @NotNull final String email, @NotNull final String endereco) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setMatricula(matricula);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setEndereco(endereco);
    }

    public Aluno() {}
}
