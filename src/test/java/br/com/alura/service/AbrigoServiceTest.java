package br.com.alura.service;

import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AbrigoServiceTest {

    @InjectMocks
    @Spy
    private AbrigoService abrigoService;

    @Mock
    private HttpResponse<String> mockResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveVerificarQuandoHaAbrigo() throws IOException, InterruptedException {
        Abrigo abrigo = new Abrigo("Teste", "61981880392", "abrigo_alura@gmail.com");
        abrigo.setId(0L);

        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedId = "0";
        String expectedNome = "Teste";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        // Configurar o mock da resposta HTTP
        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(List.of(abrigo));
        when(mockResponse.body()).thenReturn(responseBody);

        // Mockar o método requisicaoGet para retornar a resposta mockada
        doReturn(mockResponse).when(abrigoService).requisicaoGet(anyString());

        // Executar o método que lista abrigos
        abrigoService.listarAbrigo();

        // Capturar a saída no console e verificar o resultado
        List<String> lines = List.of(baos.toString().split(System.lineSeparator()));
        String actualAbrigosCadastrados = lines.get(0);
        String actualId = lines.get(1);
        String actualNome = lines.get(2);

        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectedId, actualId);
        Assertions.assertEquals(expectedNome, actualNome);
    }


    @Test
    public void deveVerificarSeNaoHaAbrigo() throws InterruptedException, IOException {
        String expected = "Não há abrigos cadastrados";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(mockResponse.body()).thenReturn("[]");
        doReturn(mockResponse).when(abrigoService).requisicaoGet(anyString());

        abrigoService.listarAbrigo();

        List<String> lines = List.of(baos.toString().split(System.lineSeparator()));
        String expectedActual = lines.get(0);

        Assertions.assertEquals(expected, expectedActual);
    }

}
