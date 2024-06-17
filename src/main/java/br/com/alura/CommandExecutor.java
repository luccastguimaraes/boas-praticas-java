package br.com.alura;

import java.util.concurrent.Executor;

public class CommandExecutor {

    public void executeCommand(Command command){
        command.execute();
    }
}
