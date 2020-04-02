
package br.grupolider.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;

/**
 *
 * @author rodriguesrafael
 */
public class Impressao {

    // variavel estatica porque sera utilizada por inumeras threads
    private static PrintService impressao;

    public Impressao(String nomeImpressora) {
        this.detectaImpressoras(nomeImpressora);
    }

    // O metodo verifica se existe impressora conectada e a
    // define como padrao.
    public void detectaImpressoras(String nomeImpressora) {
        DocFlavor df = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
        for (PrintService p : ps) {
            if (p.getName().contains(nomeImpressora)) {
                System.out.println("Impressora Selecionada: " + p.getName());
                impressao = p;
                break;
            }
        }
    }

    public synchronized boolean imprime(File arquivo) throws PrintException, FileNotFoundException {
        //cria um trabalho na impressora
        DocPrintJob job = impressao.createPrintJob();
        //pega o documento passado como parametro
        FileInputStream stream = new FileInputStream(arquivo);
        //seta os atributos da impressao que na realidade sao vazios null
        HashDocAttributeSet attributes = new HashDocAttributeSet();
        //fala que o documento que vai ser impresso e um PDF
        DocFlavor flavor = DocFlavor.INPUT_STREAM.GIF;
        //cria o documento que sera impresso
        Doc doc = new SimpleDoc(stream, flavor, attributes);
        //manda imprimir no trabalho criado
        job.print(doc, null);
        return true;
    }

}
