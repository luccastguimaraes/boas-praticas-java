package br.com.alura;

import br.com.alura.service.PetService;

import java.io.IOException;

public class ImportarPetsDoAbrigoCommand implements Command{

    @Override
    public void execute() {
        try {
            PetService petService = new PetService();
            petService.importarPetsDoAbrigo();
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
