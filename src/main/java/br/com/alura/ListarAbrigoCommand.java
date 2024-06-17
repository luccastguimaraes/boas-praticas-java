package br.com.alura;

import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class ListarAbrigoCommand implements Command{

    @Override
    public void execute() {
        try {
            AbrigoService abrigoService = new AbrigoService();
            abrigoService.listarAbrigo();
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
