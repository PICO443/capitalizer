package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printToConsole(getLogoAsciiArt());
        printToConsole(getFormattedMessage("Waiting For Clients", "...."));

        try(ServerSocket capitalizerServerSocket = new ServerSocket(14441)){
            while(true) {
                Socket capitalizerSocket = capitalizerServerSocket.accept();

                printToConsole(getFormattedMessage("Client Connected !", capitalizerSocket.getLocalSocketAddress().toString()));

                BufferedReader capitalizerSocketReader = new BufferedReader(new InputStreamReader(capitalizerSocket.getInputStream()));

                String clientInput = "";
                clientInput = capitalizerSocketReader.readLine();
                String capitalizedClientInput = capitalize(clientInput);
                PrintWriter capitalizerSocketWriter = new PrintWriter(capitalizerSocket.getOutputStream(), true);
                capitalizerSocketWriter.println(capitalizedClientInput);
                capitalizerSocket.close();
            }

        } catch (UnknownHostException e) {
            printToConsole(getFormattedMessage("Unknown Host Error", e.getMessage()));
        } catch (IOException e) {
            printToConsole(getFormattedMessage("An I/O Error Occurred", e.getMessage()));
        }
    }

    static String getLogoAsciiArt(){
        return  "  ______     ___      .______    __  .___________.    ___       __       __   ________   _______ .______      \n"
                + " /      |   /   \\     |   _  \\  |  | |           |   /   \\     |  |     |  | |       /  |   ____||   _  \\     \n"
                + "|  ,----'  /  ^  \\    |  |_)  | |  | `---|  |----`  /  ^  \\    |  |     |  | `---/  /   |  |__   |  |_)  |    \n"
                + "|  |      /  /_\\  \\   |   ___/  |  |     |  |      /  /_\\  \\   |  |     |  |    /  /    |   __|  |      /     \n"
                + "|  `----./  _____  \\  |  |      |  |     |  |     /  _____  \\  |  `----.|  |   /  /----.|  |____ |  |\\  \\----.\n"
                + " \\______/__/     \\__\\ | _|      |__|     |__|    /__/     \\__\\ |_______||__|  /________||_______|| _| `._____|\n"
                + "                                                                                                                     \n"
                + " :::Server Application:::                                                                                                           \n"
                + "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n"
                + "║                                         Made by: Abubakr Elsadig                                          ║\n"
                + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + "║          This application converts lowercase letters to uppercase letters. It's a simple yet handy        ║\n"
                + "║               tool for ensuring your text is always properly capitalized. Enjoy using it!                 ║\n"
                + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════╝";
    }
    static void printToConsole(String string){
        System.out.println(string);
    }

    static String getInputFromUser(){
        return scanner.nextLine();
    }
    static String getFormattedMessage(String message, String secondaryMessage){
        return "━━━━━━━━━━━━━━━ ✦ " +message+" ✦ ━━━━━━━━━━━━━━━ \n"
                +"▶ " + secondaryMessage;
    }

    static String capitalize(String string){
        return string.toUpperCase();
    }


}