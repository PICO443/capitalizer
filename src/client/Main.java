package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String userInput = "";

    public static void main(String[] args) {
        printToConsole(getLogoAsciiArt());

        while(userInput != "#close"){
            printToConsole(getFormattedMessage("Enter you string", ""));
            userInput = getInputFromUser();
            String capitalizedUserInput = capitalize(userInput);
            printToConsole(getFormattedMessage("Your Capitalized String is:", capitalizedUserInput));
            }

    }


    static String capitalize(String string) {
        String capitalizedString = "";
        try (Socket capitalizerSocket = new Socket("localhost", 14441)) {
            PrintWriter capitalizerSocketWriter = new PrintWriter(capitalizerSocket.getOutputStream(), true);
            capitalizerSocketWriter.println(string);

            BufferedReader capitalizerSocketReader = new BufferedReader(new InputStreamReader(capitalizerSocket.getInputStream()));
            capitalizedString = capitalizerSocketReader.readLine();


        } catch (UnknownHostException e) {
            printToConsole(getFormattedMessage("Unknown Host Error", e.getMessage()));
        } catch (IOException e) {
            printToConsole(getFormattedMessage("An I/O Error Occurred", e.getMessage()));
        }

        return capitalizedString;
    }

    static void printToConsole(String string){
        System.out.println(string);
    }

    static String getInputFromUser(){
        return scanner.nextLine();
    }

    static String getLogoAsciiArt(){
        return  "  ______     ___      .______    __  .___________.    ___       __       __   ________   _______ .______      \n"
                + " /      |   /   \\     |   _  \\  |  | |           |   /   \\     |  |     |  | |       /  |   ____||   _  \\     \n"
                + "|  ,----'  /  ^  \\    |  |_)  | |  | `---|  |----`  /  ^  \\    |  |     |  | `---/  /   |  |__   |  |_)  |    \n"
                + "|  |      /  /_\\  \\   |   ___/  |  |     |  |      /  /_\\  \\   |  |     |  |    /  /    |   __|  |      /     \n"
                + "|  `----./  _____  \\  |  |      |  |     |  |     /  _____  \\  |  `----.|  |   /  /----.|  |____ |  |\\  \\----.\n"
                + " \\______/__/     \\__\\ | _|      |__|     |__|    /__/     \\__\\ |_______||__|  /________||_______|| _| `._____|\n"
                + "                                                                                                                     \n"
                + " :::(Client) Application:::                                                                                                           \n"
                + "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n"
                + "║                                         Made by: Abubakr Elsadig                                          ║\n"
                + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n"
                + "║          This application converts lowercase letters to uppercase letters. It's a simple yet handy        ║\n"
                + "║               tool for ensuring your text is always properly capitalized. Enjoy using it!                 ║\n"
                + "║                               <==Type #close , to close the application==>                                ║\n"
                + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════╝";
    }

    static String getFormattedMessage(String message, String secondaryMessage){
        return "━━━━━━━━━━━━━━━ ✦ " +message+" ✦ ━━━━━━━━━━━━━━━ \n"
                +"▶ " + secondaryMessage;
    }

}
