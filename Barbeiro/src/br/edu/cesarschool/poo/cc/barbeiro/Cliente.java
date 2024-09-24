package br.edu.cesarschool.poo.cc.barbeiro;
import java.util.Random;

public class Cliente extends Pessoa implements Runnable {
    private Barbearia barbearia;

    public Cliente(int id, Barbearia barbearia) {
        super(id);
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        while (true) {
            if (barbearia.cortaCabelo(this)) {
                try {
                    Thread.sleep(new Random().nextInt(2000) + 3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                System.out.println("Cliente " + getID() + " tentou entrar na barbearia, mas está lotada... indo dar uma voltinha");
            }
            try {
                Thread.sleep(new Random().nextInt(2000) + 3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
