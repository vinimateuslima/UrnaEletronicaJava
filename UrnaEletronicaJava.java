import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UrnaEletronicaJava {

    private List<Candidato> _candidatos = new ArrayList<>();
    private int _votosNulos = 0;
    private String candidatoVencedor;
    private int contadorDeEmpate = 0;

    private List<String> _votos = new ArrayList<>();

    public UrnaEletronicaJava(List<Candidato> candidatos) {
        this._candidatos = candidatos;
    }

    public void receberVoto(String numero) {

        int numeroConvertido = Integer.parseInt(numero);

        if (numeroConvertido >= 1 && numeroConvertido <= _candidatos.size()) {
            this._votos.add(numero);
        } else {
            this.setVotosNulos();
        }

    }

    public void apurarResultados() {

        for (String voto : _votos) {
            for (Candidato candidato : _candidatos) {
                if (voto.equals(candidato.getNumero())) {
                    candidato.incrementarVotos();
                    break;
                }
            }
        }

        int maiorVoto = 0;
        for (Candidato candidato : _candidatos) {
            if (candidato.getVotos() > maiorVoto) {
                maiorVoto = candidato.getVotos();
                candidatoVencedor = candidato.getNome();
                contadorDeEmpate = 1;
            } else if (candidato.getVotos() == maiorVoto) {
                contadorDeEmpate++;
            }
        }

    }

    public String exibirResultados() {

        StringBuilder sb = new StringBuilder();

        sb.append("\nResultado da Votação: \n");
        sb.append("\n");

        double porcentagem = 0.0;

        for (Candidato candidato : _candidatos) {

            porcentagem = ((double) candidato.getVotos() / this._votos.size()) * 100;

            sb.append(candidato.getNome() + ": " + candidato.getVotos() + " votos " + "(" + porcentagem + "%)");
            sb.append("\n");

        }

        sb.append("Votos nulos: " + this._votosNulos);
        sb.append("\n");
        if (contadorDeEmpate > 1) {
            sb.append("Empate");
        } else {
            sb.append("Vencedor: " + candidatoVencedor);
        }

        return sb.toString();
    }

    public void getCandidatos() {
        System.out.println();
        for (Candidato candidato : _candidatos) {
            System.out.println("Nome: " + candidato.getNome() + " Votos: " + candidato.getVotos());

        }

        System.out.println("Votos Nulos: " + this.getVotosNulos());

    }

    public List<String> getvotos() {
        return this._votos;
    }

    public void setVotosNulos() {
        this._votosNulos++;
    }

    public int getVotosNulos() {
        return this._votosNulos;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=-=-=-=-=-= Bem Vindo à Urna Eletronica =-=-=-=-=-=-=");
        System.out.println("=-=-=-=-=-= Vote no novo diretor de Hogwarts =-=-=-=-=-=-= \n");

        List<Candidato> candidatos = new ArrayList<>();

        candidatos.add(new Candidato("Albus Dumbledore", "01"));
        candidatos.add(new Candidato("Godric Grifinória", "02"));
        candidatos.add(new Candidato("Helga Lufa-Lufa", "03"));
        candidatos.add(new Candidato("Rowena Ravenclaw", "04"));
        candidatos.add(new Candidato("Salazar Sonserina", "05"));

        UrnaEletronicaJava urna = new UrnaEletronicaJava(candidatos);

        StringBuilder sb = new StringBuilder();
        sb.append("Candidatos: \n \n");
        for (Candidato candidato : candidatos) {
            sb.append(candidato.getNumero() + " - " + candidato.getNome() + "\n");
        }

        System.out.println(sb.toString());

        String numero;

        int votosMaximos = 10;

        for (int i = 0; i < votosMaximos; i++) {

            while (true) {
                System.out.print("Digite o número do seu candidato: ");
                numero = sc.next();

                if (numero.length() == 2 && numero.matches("\\d+") && numero.charAt(0) == '0') {
                    urna.receberVoto(numero);
                    break;
                } else {
                    System.out.println("Voto inválido! Digite o número do candidato válido Ex: 01, 02...");

                }
            }

        }

        urna.apurarResultados();
        System.out.println(urna.exibirResultados());

        sc.close();

    }

}
