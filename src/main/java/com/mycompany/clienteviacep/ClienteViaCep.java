/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clienteviacep;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author ronaldo
 */
public class ClienteViaCep {

    /**
     * Para buscar o cep no viacep
     *
     * @param cep A String com a cep a ser bsucado
     * @return A stirng não formatada
     */
    public static String buscarCep(String cep) {
        String xmlNaoFormatado;
        try {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/xml");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder xmlSb = new StringBuilder();
            br.lines().forEach(l -> xmlSb.append(l.trim()));
            xmlNaoFormatado = xmlSb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return xmlNaoFormatado;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Digite seu cep");
        Scanner scanner = new Scanner(System.in);
        String CEPAPesquisar = scanner.nextLine();
        String xmlNaoFormatado = buscarCep(CEPAPesquisar);                  //Chama a função que bisca o CEP
        System.out.println(xmlNaoFormatado);                                //Saída não formatada
        System.out.println(new FormatadorXML().format(xmlNaoFormatado));    //Saída formatada
    }

}
