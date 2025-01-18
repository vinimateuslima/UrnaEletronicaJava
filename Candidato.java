public class Candidato {
    private String _nome;
    private String _numero;
    private int _votos;

    public Candidato(String nome, String numero) {
        this._nome = nome;
        this._numero = numero;
    }

    public String getNome() {
        return this._nome;
    }

    public String getNumero() {
        return this._numero;
    }

    public int getVotos() {
        return this._votos;
    }

    public void incrementarVotos() {
        this._votos++;
    }

    @Override
	public String toString() {
            return this.getNome();
		
	}

}
