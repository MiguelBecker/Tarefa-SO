package br.edu.cesarschool.poo.cc.barbeiro;

import java.util.Random;

public class Barbeiro extends Pessoa implements Runnable {
    private Barbearia barbearia;
    private boolean dormindo;

    public Barbeiro(int id, Barbearia barbearia) {
        super(id);
        this.barbearia = barbearia;
        this.dormindo = false;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (barbearia) {
                while (barbearia.filaVazia()) {
                    if (!dormindo) {
                        System.out.println("Barbeiro " + getID() + " indo dormir um pouco... não há clientes na barbearia...");
                        dormindo = true;
                    }
                    try {
                        barbearia.wait(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                if (dormindo) {
                    System.out.println("Barbeiro " + getID() + " acordou! Começando os trabalhos!");
                    dormindo = false;
                }

                Cliente cliente = barbearia.proximoCliente();
                if (cliente != null) {
                    System.out.println("Cliente " + cliente.getID() + " cortando cabelo com Barbeiro " + getID());
                    try {
                        Thread.sleep(new Random().nextInt(2000) + 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    barbearia.corteTerminado(cliente);
                }
            }
        }
    }
}
