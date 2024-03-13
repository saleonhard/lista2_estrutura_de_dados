/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LSA
 */
public class L2Q2 {

    public static void main(String[] args) {
        Arquivo arq = new Arquivo("src/L2Q2.in", "src/L2Q2.out");

        while (!arq.isEndOfFile()) {
            String linha = arq.readLine();
            String[] valores = linha.split(" ");
            Arvore arvore = new Arvore();
            String saida = "";

            for (int e = 0; e < valores.length; e++) {
                
                if(arvore.buscarNo(arvore.raiz, Integer.parseInt(valores[e])) == null) {
                    arvore.inserirNo(arvore, new No(Integer.parseInt(valores[e])));
                }
            }
            
            String arvoreOrdenada = arvore.readInOrdem(arvore.raiz);
            valores = arvoreOrdenada.split(" ");
            for(String e : valores){
             No rs = arvore.buscarNo(arvore.raiz, Integer.parseInt(e)); 
             saida += e + " ("+ arvore.diferencaDirEsq(arvore.buscarNo(arvore.raiz, rs.chave)) +") ";
            }
            saida = saida.substring(0, saida.length()-1);
            if (arq.isEndOfFile()) {
                arq.print(saida);
            } else {
                arq.println(saida);
            }
        }

        arq.close();
    }
}
