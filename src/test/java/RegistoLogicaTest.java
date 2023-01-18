import com.example.trabalho.Login;
import com.example.trabalho.RegistoLogica;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class RegistoLogicaTest {

    RegistoLogica teste = new RegistoLogica();

    @Test
    void checkRegister_Logica_CampoVazio_Username() {
        assertEquals(1, teste.checkRegister_Logica("","email@gmail.com","password","password"));
    }

    @Test
    void checkRegister_Logica_CampoVazio_Email() {
        assertEquals(1, teste.checkRegister_Logica("username","","password","password"));
    }

    @Test
    void checkRegister_Logica_CampoVazio_Password() {
        assertEquals(1, teste.checkRegister_Logica("username","email@gmail.com","","password"));
    }

    @Test
    void checkRegister_Logica_CampoVazio_PasswordRepetida() {
        assertEquals(1, teste.checkRegister_Logica("username","email@gmail.com","password",""));
    }
    @Test
    void checkRegister_Logica_PasswordsDiferentes_Password() {
        assertEquals( 2, teste.checkRegister_Logica("utilizador","email@gmail.com","password","teste1234"));
    }
    @Test
    void checkRegister_Logica_PasswordsDiferentes_ConfirmacaoPassword() {
        assertEquals( 2, teste.checkRegister_Logica("utilizador","email@gmail.com","teste1234","password"));
    }
    @Test
    void checkRegister_Logica_UsernameCurto() {
        assertEquals( 3, teste.checkRegister_Logica("user","email@gmail.com","pass","pass"));
    }

    @Test
    void checkRegister_Logica_UsernameLongo() {
        assertEquals( 4, teste.checkRegister_Logica("utilizadorutilizadorutilizador","email@gmail.com","013245678901324567891","013245678901324567891"));
    }
    @Test
    void checkRegister_Logica_PasswordCurta() {
        assertEquals( 5, teste.checkRegister_Logica("utilizador","email@gmail.com","pass","pass"));
    }

    @Test
    void checkRegister_Logica_PasswordLonga() {
        assertEquals( 6, teste.checkRegister_Logica("utilizador","email@gmail.com","013245678901324567891","013245678901324567891"));
    }

    @Test
    void checkRegister_Logica_EmailInvalido() {
        assertEquals(7, teste.checkRegister_Logica("utilizador","email@gmail","password","password"));
    }
    @Test
    void checkRegister_Logica_UsernameInvalido() {
        assertEquals(8, teste.checkRegister_Logica("_ utilizador __","email@gmail.com","password","password"));
    }
    @Test
    void checkRegister_Logica_PasswordInvalida() {
        assertEquals(9, teste.checkRegister_Logica("utilizador","email@gmail.com","password __","password __"));
    }

    @Test
    void checkRegister_Logica_UsernameRepetido() {
        assertEquals(10, teste.checkRegister_Logica("Fernando_Santos","email@gmail.com","password","password"));
    }

    @Test
    void checkRegister_Logica_EmailRepetido() {
        assertEquals(11, teste.checkRegister_Logica("utilizador","teste@gmail.com","password","password"));
    }


    @Test
    void checkRegister_Logica_CasoSucesso() {
        assertEquals(0, teste.checkRegister_Logica("utilizador","email@gmail.com","password","password"));
    }


    @Test
    public void criaconta()
    {
        assertEquals(0,teste.criaconta("utilizadornovo","emailnovo@gmail.com","password"));
        assertEquals(Login.getUserList().get(Login.getUserList().size()-1).getUsername(),"utilizadornovo");
    }


}