package Entidades;

import org.jetbrains.annotations.NotNull;

public class Disciplina {
    private String nome;
    private String codigo;
    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(@NotNull final Professor professor) {
        this.professor = professor;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(@NotNull final String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NotNull final String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        if (professor != null) {
            return "Disciplina{" +
                    "nome='" + nome + '\'' +
                    ", codigo='" + codigo + '\'' +
                    ", professor=" + professor.getNome() +
                    '}';
        } else {
            return "Disciplina{" +
                    "nome='" + nome + '\'' +
                    ", codigo='" + codigo + '\'' +
                    ", professor=" +
                    '}';
        }
    }

    public Disciplina(@NotNull final String nome, @NotNull final String codigo, @NotNull final Professor professor) {
        this.setProfessor(professor);
        this.setNome(nome);
        this.setCodigo(codigo);
    }

    public Disciplina(@NotNull final String nome, @NotNull final String codigo) {
        this.setNome(nome);
        this.setCodigo(codigo);
    }

    public Disciplina() {}
}
