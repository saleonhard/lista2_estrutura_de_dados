/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LSA
 */
public class L2Q1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arquivo arq = new Arquivo("src/L2Q1.in", "src/L2Q1.out");
        
        
        while (!arq.isEndOfFile()) {
            String linha = arq.readLine();
            String[] valores = linha.split(" ");
            No[] nos = new No[valores.length];
            Arvore arvore = new Arvore();
            String saida = "";
            
            for (int e = 0; e < valores.length; e++) {
                No no = new No(Integer.parseInt(valores[e]));
                arvore.inserirNo(arvore,no);
                nos[e] = no;
            }
          
            for (int e = 0; e < nos.length; e++) {
               No noRes = arvore.buscarNo2(arvore.raiz, nos[e]);
               int altDir = (noRes.dir == null? 0: noRes.dir.alt);
               int altEsq = (noRes.esq == null? 0: noRes.esq.alt);
               saida+=Integer.toString(altEsq - altDir) + " ";
            }
           No min = arvore.min(arvore.raiz);
           saida += "min "+ min.chave + " alt " + min.alt + " suc " + (arvore.suc(min)== null? "NaN":arvore.suc(min).chave);
            if (arq.isEndOfFile()) {
                arq.print(saida);
            } else {
                arq.println(saida);
            }
        }
        
      arq.close();
    }
    
    
}
