/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LSA
 */
public class Arvore {

    No raiz;

    public Arvore(No raiz) {
        this.raiz = raiz;
    }

    public Arvore() {
    }

    public int calcularAlt(No no) {
        No aux = raiz;
        int alt = 0;
        while (aux != null && !aux.equals(no)) {
            if (no.chave < aux.chave) {
                aux = aux.esq;
            } else {
                aux = aux.dir;
            }
            alt++;
        }
        return alt;
    }


    public String caminharOrdemArvore(No x, String arvoreOrdenada) {

        if (x != null) {
            caminharOrdemArvore(x.esq, arvoreOrdenada);
            // System.out.print(" " + x.chave + " ");
            arvoreOrdenada = arvoreOrdenada + x.chave + " ";
            
            caminharOrdemArvore(x.dir, arvoreOrdenada);
        }
        return arvoreOrdenada;
    }
    public No[] caminharOrdemArvore2(No x, No[] arvoreOrdenada,int e) {
        
        if (x != null) {
            
            caminharOrdemArvore2(x.esq, arvoreOrdenada,e);
            // System.out.print(" " + x.chave + " ");
            for(int i = 0; i < e ;i++){
              if(arvoreOrdenada[i] == null){ arvoreOrdenada[i] = x; break;}
               
            }
            caminharOrdemArvore2(x.dir, arvoreOrdenada, e);
            //e++;
        }
        return arvoreOrdenada;
    }

    public void caminharPreOrdemArvore(No x) {
        if (x != null) {
            System.out.print(" " + x.chave + " ");
            caminharPreOrdemArvore(x.esq);
            caminharPreOrdemArvore(x.dir);
        }

    }

    public String readInOrdem(No no) {
        if (no == null) {
            return "";
        }
        String rt = "";
        if (no.esq != null) {
            rt += (rt.isEmpty() ? "" : ",") + readInOrdem(no.esq);
        }
        rt += (rt.isEmpty() ? "" : " ") + no.chave;
        if (no.dir != null) {
            rt += (rt.isEmpty() ? "" : " ") + readInOrdem(no.dir);
        }
        return rt;
    }
    public No[] readInOrdem2(No no, int numNos,int e, No rt[]) {
        if (no == null) {
            return null;
        }
       No[] rte  = rt;
        
       
            //++e; 
            readInOrdem2(no.esq,numNos,e, rte);
            //rt[e] = no;
           
        
        rt[e++] = no;
        
       
            //++e; 
            readInOrdem2(no.dir,numNos,e,rte);
           // rt[e] = no;
            //e++;
      
        return rte;
    }

    public int diferencaDirEsq(No no) {

        int somaDir = 0;
        int somaEsq = 0;
        int resul = 0;
        boolean sub = false;
        if (no == null) {
            return 0;
        }
        String rt = "";
        if (no.dir != null) {
            rt += (rt.isEmpty() ? "" : " ") + readInOrdem(no.dir);
        }
        rt += (rt.isEmpty() ? "0 " : " ") + (no.chave == no.chave ? "-" : ("+" + no.chave));
        if (no.esq != null) {
            rt += (rt.isEmpty() ? "" : " ") + readInOrdem(no.esq);
        } else {
            rt += " 0";
        }
        String[] valores = rt.split(" ");
        for (String e : valores) {
            if (e.equals("-")) {
                sub = true;
            } else {
                resul = (sub ? resul - Integer.parseInt(e) : resul + Integer.parseInt(e));
            }
        }

        return resul;
    }

    public No min(No no) {

        while (no.esq != null) {
            no = no.esq;
        }
        return no;
    }

    public No suc(No no) {
        if (no.dir != null) {
            return min(no.dir);
        }
        No x = no.pai;
        while (x != null && no == x.dir) {
            no = x;
            x = x.pai;
        }
        return x;
    }

    public No buscarNo(No no, int chave) {
        if (no == null || no.chave == chave) {
            return no;
        } else {
            if (chave < no.chave) {
                return buscarNo(no.esq, chave);
            } else {
                return buscarNo(no.dir, chave);
            }
        }
    }
    public No buscarNo3(No no, int chave, int chavePai) {
        if (no == null || (no.chave == chave && no.pai.chave == chavePai)) {
            return no;
        } else {
            if (chave < no.chave) {
                return buscarNo(no.esq, chave);
            } else {
                return buscarNo(no.dir, chave);
            }
        }
    }
    public No buscarNo2(No no, No noBus) {
        if (no == null || no.equals(noBus)) {
            return no;
        } else {
            if (noBus.chave < no.chave) {
                return buscarNo2(no.esq, noBus);
            } else {
                return buscarNo2(no.dir, noBus);
            }
        }
    }


    public void inserirNo(Arvore arvore, No no) {

        No y = null;
        No x = arvore.raiz;
        while (x != null) {
            y = x;
            if (no.chave < x.chave) {
                x = x.esq;
            } else {
                x = x.dir;
            }
        }
        no.pai = y;
        if (y == null) {
            no.alt = 0;
            arvore.raiz = no;
        } else {
            if (no.chave < y.chave) {
                no.alt = no.pai.alt + 1;
                y.esq = no;
            } else {
                no.alt = no.pai.alt + 1;
                y.dir = no;
            }
        }

    }

    public void deletarNo(Arvore arvore, No no) {
        No x;
        No y;
        int alt = 0;

        if (no.esq == null || no.dir == null) {
            y = no;
        } else {
            y = suc(no);
            alt = y.alt;
            y.alt = no.alt;
        }
        if (y.esq != null) {
            x = y.esq;
            // x.alt = alt;
        } else {
            x = y.dir;
            //x.alt = alt;
        }
        if (x != null) {
            x.pai = y.pai;
            x.alt = y.alt;

        }
        if (y.pai == null) {
            arvore.raiz = x;
        } else {
            if (y.pai == null) {
                y.pai.esq = x;
                //y.pai.esq.alt = x.alt;
            } else {
                y.pai.dir = x;
                //y.pai.dir.alt = x.alt + 1 ;
            }

        }
        if (y != no) {
            no.chave = y.chave;
            //no.alt = alt;
        }
    }
    
    public static void main(String[] args){
      Arvore a = new Arvore();
     for (int i = 0; i<4;i++){
      No nn = new No(i);
      a.inserirNo(a, nn);
    }
       No r = a.buscarNo(a.raiz, 0);
       No r2 = a.buscarNo(a.raiz, 3);
       No r3 = a.buscarNo(a.raiz, 4);
      
       
      
    }

}
