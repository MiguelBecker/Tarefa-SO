package br.edu.cesarschool.poo.cc.barbeiro;

import java.util.LinkedList;
import java.util.Queue;

public class Barbearia {
    private final int numBarbeiros;
    private final int numCadeiras;
    private final Queue<Cliente> filaClientes;
    private int clientesAtendendo;
    
    public Barbearia(int numBarbeiros, int numCadeiras) {
        this.numBarbeiros = numBarbeiros;
        this.numCadeiras = numCadeiras;
        this.filaClientes = new LinkedList<>();
        this.clientesAtendendo = 0;
    }

    public synchronized boolean cortaCabelo(Cliente cliente) {
        if (clientesAtendendo < numBarbeiros && filaClientes.size() < numCadeiras) {
            filaClientes.add(cliente);
            System.out.println("Cliente " + cliente.getID() + " esperando corte...");
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized Cliente proximoCliente() {
        while (filaClientes.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        clientesAtendendo++;
        return filaClientes.poll();
    }

    public synchronized void corteTerminado(Cliente cliente) {
        clientesAtendendo--;
        System.out.println("Cliente " + cliente.getID() + " terminou o corte... saindo da barbearia!");
        notifyAll();
    }
}
