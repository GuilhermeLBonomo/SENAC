package br.senac.rj.empresa.modelo;

public class Material {
    private int codMaterial;
    private String descMaterial;
    private int qtdeEstoque = 0;

    public Material(final int codMat, final String descMat, final int qtEst) {
        this.setCodMaterial(codMat);
        this.setDescMaterial(descMat);
        this.entrarMaterial(qtEst);
    }

    public Material() {
    }

    public void entrarMaterial(int qtMaterial) {
        this.qtdeEstoque += Math.abs(qtMaterial);
    }

    public void sairMaterial(int qtMaterial) {
        if (qtMaterial > this.qtdeEstoque) {
            System.err.printf("Não há estoque suficiente!\nFaltam %d materiais\n", (qtMaterial - this.qtdeEstoque));
        } else {
            this.qtdeEstoque -= qtMaterial;
            System.out.printf("Saída do material realizada com sucesso!\n Adicionado %d materiais", qtMaterial);
        }
    }

    public int getCodMaterial() {
        return this.codMaterial;
    }

    public void setCodMaterial(final int codMaterial) {
        this.codMaterial = codMaterial;
    }

    public String getDescMaterial() {
        return this.descMaterial;
    }

    public void setDescMaterial(final String descMaterial) {
        this.descMaterial = descMaterial;
    }

    public int getQtEstoque() {
        return this.qtdeEstoque;
    }
}
