package br.edu.cesarschool.poo.cc.barbeiro;

import java.util.Random;

public class Barbeiro extends Pessoa implements Runnable {
    private Barbearia barbearia;

    public Barbeiro(int id, Barbearia barbearia) {
        super(id);
        this.barbearia = barbearia;
    }
    @Override
    public void run() {
        while (true) {
            Cliente cliente = barbearia.proximoCliente();
            if (cliente != null) {
                System.out.println("Cliente " + cliente.getID() + " cortando cabelo com Barbeiro " + getID());
                try {
                    Thread.sleep(new Random().nextInt(2000) + 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                barbearia.corteTerminado(cliente);
            } else {
                System.out.println("Barbeiro " + getID() + " indo dormir um pouco... não há clientes na barbearia...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
