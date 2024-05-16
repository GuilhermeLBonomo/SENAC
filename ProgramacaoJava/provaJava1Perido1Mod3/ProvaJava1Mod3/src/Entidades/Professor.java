package Entidades;

import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
public final class Professor {

    private HashSet<Disciplina> disciplinasMinistradas = new HashSet<Disciplina>();
    private String nome;
    private String cpf;
    private String matricula;
    private String telefone;
    private String email;
    private String endereco;

    public void aplicarAvaliacao(@NotNull final Disciplina disciplina, @NotNull final String turma) {
        System.out.printf("O professor %s aplicou a prova de %s para a turma %s\n", this.getNome(), disciplina.getNome(), turma);
    }

    public void lancarNota(@NotNull final AlunoGraduacao aluno, @NotNull final Disciplina disciplina, @NotNull final Float nota) {
        System.out.printf("O professor %s lançou a nota %f para o aluno %s em %s\n", this.getNome(), nota, aluno.getNome(), disciplina.getNome());
    }

    public void lancarFrequencia(@NotNull final AlunoGraduacao aluno, final int frequencia, @NotNull final Disciplina disciplina) {
        System.out.printf("O professor %s lançou a frequência %d para o aluno %s em %s\n", this.getNome(), frequencia, aluno.getNome(), disciplina.getNome());
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

    public HashSet<Disciplina> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }

    public void addDisciplinasMinistradas(@NotNull final HashSet<Disciplina> disciplinasMinistradas) {
        for (Disciplina disciplina : disciplinasMinistradas) {
            // Aqui teria uma validacao de erro...
           this.addDisciplinaMinistrada(disciplina);
        }
    }

    public void addDisciplinaMinistrada(@NotNull final Disciplina disciplina) {
        if (disciplina.getProfessor() == null) {
            disciplina.setProfessor(this);
            this.disciplinasMinistradas.add(disciplina);
            System.out.printf("O professor %s ministrará a disciplina %s\n", this.getNome(), disciplina.getNome());
        } else {
            System.err.printf("Erro: essa disciplina %s já está associada ao professor %s.\n", disciplina.getNome(), disciplina.getProfessor().getNome());
        }
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", matricula='" + matricula + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", disciplinasMinistradas=" + disciplinasMinistradas +
                '}';
    }

    public Professor(@NotNull final String nome, @NotNull final String cpf, @NotNull final String matricula, @NotNull final String telefone, @NotNull final String email, @NotNull final String endereco, @NotNull final HashSet<Disciplina> disciplinasMinistradas) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setMatricula(matricula);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setEndereco(endereco);
        this.addDisciplinasMinistradas(disciplinasMinistradas);
    }

    public Professor(@NotNull final String nome, @NotNull final String cpf, @NotNull final String matricula, @NotNull final String telefone, @NotNull final String email, @NotNull final String endereco, @NotNull final Disciplina disciplinaMinistrada) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setMatricula(matricula);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setEndereco(endereco);
        this.addDisciplinaMinistrada(disciplinaMinistrada);
    }
    public Professor(@NotNull final String nome, @NotNull final String cpf, @NotNull final String matricula, @NotNull final String telefone, @NotNull final String email, @NotNull final String endereco) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setMatricula(matricula);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setEndereco(endereco);
    }

    public Professor() {}
}
