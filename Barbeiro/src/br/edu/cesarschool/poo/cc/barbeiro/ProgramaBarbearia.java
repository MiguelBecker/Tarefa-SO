package br.edu.cesarschool.poo.cc.barbeiro;

import java.util.Scanner;
import java.util.Random;

public class ProgramaBarbearia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número de barbeiros: ");
        int numBarbeiros = scanner.nextInt();
        System.out.print("Digite o número de cadeiras: ");
        int numCadeiras = scanner.nextInt();
        Barbearia barbearia = new Barbearia(numBarbeiros, numCadeiras);
        scanner.close();
        for (int i = 0; i < numBarbeiros; i++) {
            new Thread(new Barbeiro(i + 1, barbearia)).start();
        }
        int clienteId = 1;
        Random random = new Random();

        while (true) {
            new Thread(new Cliente(clienteId++, barbearia)).start();
            try {
                Thread.sleep(random.nextInt(2000) + 3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
