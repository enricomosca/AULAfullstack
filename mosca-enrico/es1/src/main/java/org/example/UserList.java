package org.example;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class UserList {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<User> listaUtenti = new ArrayList<User>();

    public static void main(String[] args) throws IOException, InterruptedException {

        User user = new User("enrico", 1996);
        listaUtenti.add(user);

        boolean continua = true;


        do {
            Thread.sleep(500);
            System.out.println("\nCosa vuoi fare?");
            System.out.println("1: crea un utente");
            System.out.println("2: mostra la lista utenti");
            System.out.println("3: update utente");
            System.out.println("4: rimuovi un utente");
            System.out.println("5: salva su file");
            System.out.println("6: termina");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> createUser();
                case "2" -> showList();
                case "3" -> updateUser();
                case "4" -> removeUser();
                case "5" -> saveList();
                case "6" -> continua = false;
                default -> System.out.println("Inserimento non valido");
            }
        } while (continua);


    }

    private static void saveList() throws IOException {
        System.out.println("Quale estensione vuoi usere?");
        System.out.println("1: .txt");
        System.out.println("2: .json");
        String name = scanner.nextLine();

        if (name.equals("1")) {
            FileWriter writer;
            try {
                writer = new FileWriter("listaUtenti.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (User user : listaUtenti) {
                writer.write(user + System.lineSeparator());
            }
            System.out.println("File listaUtenti.txt scritto correttamente");
            writer.close();
        } else {
            JSONArray jsonUserList = new JSONArray();
            JSONObject jsonUser = new JSONObject();

            for (User user : listaUtenti) {
                jsonUser.put("name", user.getName());
                jsonUser.put("annoNascita", user.getAnnoNascita());

                // CAPIRE WARNING Unchecked call to 'add(E)' as a member of raw type 'java.util.ArrayList'
                jsonUserList.add(jsonUser);
            }

            FileWriter file = null;
            try {
                file = new FileWriter("listaUtenti.json");
                file.write(jsonUserList.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(file).flush();
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void removeUser() {
        System.out.println("Come si chiama l'utente da eliminare?");
        String name = scanner.nextLine();
        User user = findUser(name);
        if (user == null) {
            System.out.println("!!!! Utente non trovato !!!\n");
        } else {
            listaUtenti.remove(user);
            System.out.println("Vuoi vedere la lista aggiornata? (S/N)");
            String option = scanner.nextLine().toLowerCase();
            if (option.equals("s")) {
                showList();
            }
        }
    }

    private static User findUser(String name) {
        for (User user : listaUtenti) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    private static void updateUser() {
        System.out.println("Come si chiama l'utente da aggiornare?");
        String name = scanner.nextLine();
        User user = findUser(name);
        if (user == null) {
            System.out.println("!!! Utente non trovato !!!\n");
        } else {
            System.out.println("Cosa vuoi modificare?");
            System.out.println("1: nome");
            System.out.println("2: anno di nascita");
            String opt = scanner.nextLine();
            if (opt.equals("1")) {
                System.out.println("Nuovo nome?");
                String newName = scanner.nextLine();
                Objects.requireNonNull(user).setName(newName);
            } else {
                System.out.println("Qual è l'hanno di nascita?");
                String anno = scanner.nextLine();
                Objects.requireNonNull(user).setAnnoNascita(parseInt(anno));
            }
        }
    }

    private static void createUser() {
        System.out.println("Come si chiama?");
        String name = scanner.nextLine();
        System.out.println("Qual è l'hanno di nascita?");
        String data = scanner.nextLine();
        User user = new User(name, parseInt(data));
        listaUtenti.add(user);

        System.out.println("--- Utente aggiunto correttamente ---\n Nome: " + name + "Anno di Nascita: " + data + "\n");
        System.out.println("Vuoi vedere la lista aggiornata? (S/N)");
        String option = scanner.nextLine().toLowerCase();
        if (option.equals("s")) {
            showList();
        }
    }

    static void showList() {
        if (listaUtenti.size() == 0) {
            System.out.println("La lista è vuota");
        } else {
            System.out.println(Arrays.toString(listaUtenti.toArray()));
        }

    }


}