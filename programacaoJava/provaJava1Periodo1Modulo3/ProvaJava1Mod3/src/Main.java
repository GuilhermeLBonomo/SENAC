import SistemaAcademico.Professor;
import SistemaAcademico.Disciplina;
import SistemaAcademico.AlunoGraduacao;
import SistemaAcademico.AlunoPosGraduacao;

public class Main {
    public static void main(String[] args) {
        Disciplina disciplinaJava = new Disciplina("Java", "01");
        Professor Clayton = new Professor("Clayton", "12345678901", "MAT123", "987654321", "clayton@example.com", "Endereço do Clayton", disciplinaJava);
        AlunoGraduacao Guilherme = new AlunoGraduacao("Guilherme", "12345678901", "MAT001", "987654321", "guilherme@example.com", "Barra");
        AlunoPosGraduacao Maria = new AlunoPosGraduacao("Maria", "98765432109", "MAT002", "123456789", "maria@example.com", "Endereço da Maria");
        Professor Jadson = new Professor();

        Jadson.setNome("Jadson");
        Jadson.setCpf("98765432109");
        Jadson.setMatricula("MAT456");
        Jadson.setTelefone("123456789");
        Jadson.setEmail("jad@hotmail.com");
        Jadson.setEndereco("Senac");

        Guilherme.inscreverEmDisciplina(disciplinaJava);
        Disciplina ProgCpp = new Disciplina("C++", "56");
        Jadson.addDisciplinaMinistrada(ProgCpp);
        Clayton.aplicarAvaliacao(disciplinaJava, "Módulo 3");


        Clayton.lancarNota(Guilherme, disciplinaJava, 10f);
        Clayton.lancarFrequencia(Guilherme, 95, disciplinaJava);

        System.out.println("\n\n\n");

        System.out.println(disciplinaJava);
        System.out.println(Clayton);
        System.out.println(Jadson);
        System.out.println(Guilherme);
        System.out.println(Maria);
    }
}