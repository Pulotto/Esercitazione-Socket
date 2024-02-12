/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author raino
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    public Socket attendi() {
        try{
            System.out.println("1 SERVER partito in esecuzione...");
            
            server = new ServerSocket(6789);
            
            client = server.accept();
            
            server.close();
            
            inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
        return client;
    }
    
    public void comunica(){
        try{
            //rimango in attesa della riga trasmessa dal client
            System.out.println("3 benvenuto client, srivi una frase e la trasformo in maiuscolo. Attendo...");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("6 ricevuta la stringa dal client: " + stringaRicevuta);
            
            
            //la modifico e la rispedisco al client
            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("7 invio la stringa modificata al client...");
            outVersoClient.writeBytes(stringaModificata + '\n');
            
            //termina elaborazione sul server: chiudo la connessione del client
            System.out.println("9 SERVER: fine elaborazione ... buona notte");
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerStr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String args[]){
        ServerStr servente = new ServerStr();
        servente.attendi();
        servente.comunica();
    }
}






