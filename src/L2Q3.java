/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LSA
 */
public class L2Q3 {

    public static void main(String[] args) {
        Arquivo arq = new Arquivo("src/L2Q3.in", "src/L2Q3.out");

        while (!arq.isEndOfFile()) {
            String linha = arq.readLine();
            String[] valores = linha.split(" ");
            Arvore arvore = new Arvore();
            String saida = "";
            int numEle = 0;
            for (int e = 0; e < valores.length; e++) {
                if (valores[e].equals("a")) {
                    e++;
                    numEle++;
                    arvore.inserirNo(arvore, new No(Integer.parseInt(valores[e])));

                } else if (valores[e].equals("r")) {
                    e++;
                    
                    No nb = arvore.buscarNo(arvore.raiz, Integer.parseInt(valores[e]));
                    if ( nb == null) {
                         numEle++;
                         arvore.inserirNo(arvore, new No(Integer.parseInt(valores[e])));
                    }else{
                        numEle--;
                        arvore.deletarNo(arvore, nb);
                    }
                }
            }

            String arvoreOrdenada = arvore.readInOrdem(arvore.raiz);
            No nn [] = new No[numEle];
            No u[] = arvore.caminharOrdemArvore2(arvore.raiz,nn,numEle);
            valores = arvoreOrdenada.split(" ");
            for (No e : u) {
                //No rs = arvore.buscarNo(arvore.raiz, Integer.parseInt(e));
                No rs2 = arvore.buscarNo2(arvore.raiz, e);
               
                int alt = arvore.calcularAlt(rs2);
                saida += e.chave + " (" + alt + ") ";
            }
            saida = saida.substring(0, saida.length() - 1);
            if (arq.isEndOfFile()) {
                arq.print(saida);
            } else {
                arq.println(saida);
            }
        }

        arq.close();
    }
}
