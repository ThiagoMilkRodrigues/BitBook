package ativ.pkg1.thiago.milk.bitbook.pkg1.pkg0.pkg0;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.filechooser.FileSystemView;

//@author Thiago Milk
//programa criado com o propósito de armazenar registros de compra e venda de bitcoin em um arquivo .txt na área de trabalho.
//@version 1.0.0

public class Ativ1ThiagoMilkBitbook100 { 
    
    public float qntComprou(Float rs, Float cota){ // função que descobre o quanto da moeda o user comprou
        
        float result;
        
        result = rs/cota;
        
        return result; // retorna valor calculado
    }
    
    public static String txtCompra(String moeda, String dataEHora, Float rs, Float cota ){ //funçao que gera o texto de compra
        
        String txt;
        
        txt = "------------------------------\n"+
              "Registro de Compra:\n"+
              "Moeda: " +moeda+"\n"+
              "Quantidade investida: R$" +rs +"\n"+
              "Hora da compra: " +dataEHora + "\n"+
              "Cotação na hora da compra: R$" +cota+ "\n"+
              "Quantidade de " +moeda+ "(s) adquirido(dos): " +rs/cota+ "\n"+
              "------------------------------";
        
        return txt; // retorna texto
    }
    
    public static String txtVenda(String moeda, String qnd, Float rs, Float cota, Float cotaVenda, String qndVendeu){ // função que gera o texto de venda
        
        String txt;
        
        txt = "------------------------------\n"+
              "Registro de venda:\n"+
              "Moeda: " +moeda+"\n"+
              "Quantidade investida: R$" +rs +"\n"+
              "Hora da compra: " +qnd + "\n"+
              "Cotação na hora da compra: R$" +cota+ "\n"+  
              "Quantidade de " +moeda+ "(s) adquirido(dos): " +rs/cota+ "\n"+
              "Cotação na hora da venda: R$" +cotaVenda+ "\n"+
              "Valor total na venda: R$" +(rs/cota) * cotaVenda+ "\n"+
              "Hora da venda " +qndVendeu+ "\n"+
              "------------------------------";
        
        return txt; // retorna texto
    }

    public static void main(String[] args) {
        
        String user;
        String senhaD;
        int opc;
        String oq, qnd, qndVendeu, msg;
        float cota,cotaVenda, rs;
        
        Scanner scan = new Scanner(System.in);

        Users User1 = new Users(); //instancia um novo usuário da classe users
        
        User1.nome = "teste";// aribiu valores de teste para nome e senha
        User1.senha = "teste";
 
    do{
        
        System.out.println("Bem-vindo ao Bitbook, Insira seu nome de usuário:\n");
        user = scan.nextLine();
        
        System.out.println("Agora digite sua senha:\n");
        senhaD = scan.nextLine();
        
        if((User1.nome.equals(user)) && (User1.senha.equals(senhaD))){// testa se user e senha estão certos
            
            System.out.println("Bem-vindo " + user + ", o que deseja registrar hoje?\n1 - compra\n2 - venda");
            opc = scan.nextInt(); // escaneia a opção
            
            scan.nextLine();// limpa buffer de teclado
            
            switch(opc){ // switch usado para caso seja registro de compra ou venda
                
                case 1: // solicita valores
                    System.out.println("O que você comprou " +user+" ?\n");
                    oq = scan.nextLine();
                    
                    System.out.println("Qual o valor da cotação de " +oq+ " quando comprou?\n");
                    cota = scan.nextFloat();
                    
                    System.out.println("Quantos R$ investiu?\n");
                    rs = scan.nextFloat();
                    scan.nextLine();
                    
                    System.out.println("Quando comprou?\n");
                    qnd = scan.nextLine();
                    
                    msg = txtCompra(oq,qnd,rs,cota); // chama a função que gera o texto de compra
                    
                    System.out.println("O seguinte registro será disponibilizado em .txt na sua área de trabalho:\n");
                    System.out.println(msg); // mostra o texto a ser salvo
                    
                    try {// aqui o texto é enviado para um arquivo txt na área de trabalho
                        
                        String dataHoraCorrigida = qnd.replaceAll("[^a-zA-Z0-9]", "_");// substitui as / da data por _ para por no endereço do arquivo
                        String desktopPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath(); //pega o endereço da área de trabalho
                        String fileName = "/Registros_de_compra_" +oq+ "_" +dataHoraCorrigida+ ".txt";

                        FileWriter Writer = new FileWriter(desktopPath + fileName);
                        Writer.write(msg);// armazena a mensagem no .txt
                        Writer.close();

                        System.out.println("Arquivo salvo com sucesso!");//avisa se deu certo

                    } catch (IOException e) {// caso de errado mostra o erro
                        System.out.println("Erro ao salvar arquivo: "+e.getMessage());
                    }
                    break;
                    
                case 2:// requisita os valores para caso seja uma venda
                    
                    System.out.println("O que você vendeu " +user+" ?\n");
                    oq = scan.nextLine();
                    
                    System.out.println("Qual o valor da cotação de " +oq+ " quando comprou?\n");
                    cota = scan.nextFloat();
                    
                    System.out.println("Quantos R$ investiu?\n");
                    rs = scan.nextFloat();
                    scan.nextLine();
                    
                    System.out.println("Quando comprou?\n");
                    qnd = scan.nextLine();
                    
                    System.out.println("Qual o valor da cotação de " +oq+ " quando vendeu?\n");
                    cotaVenda = scan.nextFloat();
                    scan.nextLine();
                    
                    System.out.println("Quando vendeu?\n");
                    qndVendeu = scan.nextLine();
                    
                    msg = txtVenda(oq, qnd, rs, cota, cotaVenda, qndVendeu);// chama a função que gera o texto de venda
                    
                    System.out.println("O seguinte registro será disponibilizado em .txt na sua área de trabalho:\n");
                    System.out.println(msg);
                    
                    try {
                        
                        String dataHoraCorrigida = qnd.replaceAll("[^a-zA-Z0-9]", "_");
                        String desktopPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
                        String fileName = "/Registros_de_venda_" +oq+ "_" +dataHoraCorrigida+ ".txt";

                        FileWriter Writer = new FileWriter(desktopPath + fileName);
                        Writer.write(msg);
                        Writer.close();

                        System.out.println("Arquivo salvo com sucesso!");

                    } catch (IOException e) {
                        System.out.println("Erro ao salvar arquivo: "+e.getMessage());
                    }
                    
            }
            
        } else {
            System.out.println("Usuário ou senha incorretos. Tente novamente.");
        }
        
    }while(!User1.nome.equals(user) || !User1.senha.equals(senhaD));// fica em loop ate a senha e user ser correta
    }
}

