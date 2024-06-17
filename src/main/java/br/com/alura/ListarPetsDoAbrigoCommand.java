package br.com.alura;

import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ListarPetsDoAbrigoCommand implements Command{

    @Override
    public void execute() {
        try {
            PetService petService = new PetService();
            petService.listarPetsDoAbrigo();
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
