package br.com.alura;

import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class CadastrarAbrigoCommand implements Command{

    @Override
    public void execute() {
        try {
            AbrigoService abrigoService = new AbrigoService();
            abrigoService.cadastrarAbrigo();
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
