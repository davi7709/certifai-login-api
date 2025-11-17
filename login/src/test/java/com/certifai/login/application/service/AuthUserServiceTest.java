package com.certifai.login.application.service;

import com.certifai.login.application.port.out.AuthUserGateway;
import com.certifai.login.application.service.AuthUserService;
import com.certifai.login.domain.UserLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthUserServiceTest {

    // Mock: Simula o comportamento do adaptador de persistência (AuthUserGateway)
    @Mock
    private AuthUserGateway authUserGateway;

    // InjectMocks: Instancia AuthUserService e injeta o mock acima
    @InjectMocks
    private AuthUserService authUserService;

    private final String TEST_EMAIL = "test@example.com";
    private final String CORRECT_HASH = "correctHash123";
    private final String WRONG_HASH = "wrongHash456";

    // Dados fornecidos pelo usuário no input
    private UserLogin userInputCorrectPassword;
    private UserLogin userInputWrongPassword;

    // Dados carregados do "banco" (storedUser)
    private UserLogin storedUser;

    @BeforeEach
    void setUp() {
        // 1. Input do usuário com senha correta
        userInputCorrectPassword = new UserLogin(TEST_EMAIL, CORRECT_HASH);
        // 2. Input do usuário com senha incorreta
        userInputWrongPassword = new UserLogin(TEST_EMAIL, WRONG_HASH);

        // 3. Usuário carregado do banco de dados
        storedUser = new UserLogin(TEST_EMAIL, CORRECT_HASH);
    }

    // --- Cenario 1: Sucesso na Autenticação ---
    @Test
    void login_ShouldReturnStoredUser_WhenCredentialsAreValid() {
        // ARRANGE
        // Configura o Mock: Quando loadByEmail é chamado, retorna o usuário armazenado
        when(authUserGateway.loadByEmail(TEST_EMAIL)).thenReturn(storedUser);

        // ACT
        final UserLogin result = authUserService.login(userInputCorrectPassword);

        // ASSERT
        // Verifica se o resultado não é nulo e se o objeto retornado é o mesmo que foi carregado
        assertNotNull(result, "O resultado não deve ser nulo");
        assertEquals(storedUser.email(), result.email(), "O email deve corresponder ao usuário carregado");

        // Verifica se o método do gateway foi chamado
        verify(authUserGateway, times(1)).loadByEmail(TEST_EMAIL);
    }

    // --- Cenario 2: Senha Incorreta (Credentials Inválidas) ---
    @Test
    void login_ShouldThrowException_WhenPasswordIsInvalid() {
        // ARRANGE
        // Configura o Mock: Simula o gateway retornando o usuário real do "banco"
        when(authUserGateway.loadByEmail(TEST_EMAIL)).thenReturn(storedUser);

        // ACT & ASSERT
        // Testa se a exceção é lançada quando o input tem a senha errada
        final RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> authUserService.login(userInputWrongPassword),
                "Deve lançar RuntimeException para senha incorreta"
        );

        assertEquals("Invalid Credentials", thrown.getMessage());

        // Verifica se a busca no banco ocorreu
        verify(authUserGateway, times(1)).loadByEmail(TEST_EMAIL);
    }

    // --- Cenario 3: Usuário Não Encontrado ---
    @Test
    void login_ShouldThrowException_WhenUserIsNotRegistered() {
        // ARRANGE
        // Configura o Mock: Quando loadByEmail é chamado, retorna null (usuário não encontrado)
        when(authUserGateway.loadByEmail(TEST_EMAIL)).thenReturn(null);

        // ACT & ASSERT
        final RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> authUserService.login(userInputCorrectPassword),
                "Deve lançar RuntimeException quando o usuário não existe"
        );

        assertEquals("Invalid Credentials", thrown.getMessage());

        // Verifica se a busca no banco ocorreu
        verify(authUserGateway, times(1)).loadByEmail(TEST_EMAIL);
    }

    // --- Cenario 4: Email Nulo (Validação Inicial) ---
    @Test
    void login_ShouldThrowException_WhenEmailIsNull() {
        // ARRANGE
        final UserLogin userWithNullEmail = new UserLogin(null, CORRECT_HASH);

        // ACT & ASSERT
        final RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> authUserService.login(userWithNullEmail),
                "Deve lançar RuntimeException quando o email é nulo"
        );

        assertEquals("Email must be provided", thrown.getMessage());

        // Verifica se o método do gateway NUNCA foi chamado, pois o teste falhou antes de chamá-lo
        verify(authUserGateway, never()).loadByEmail(anyString());
    }
}