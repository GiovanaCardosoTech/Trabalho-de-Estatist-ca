import java.sql.ResultSet;

public interface Conection {
    //Método para Conectar com o Banco:
    boolean conect();
    //Método para inserir dados:
    boolean insert(int id, String headline, String image, String link);
    //Método para Alterar:
    boolean alter(int id, String data, String column);
    //Método para buscar pela tabela inteira:
    ResultSet search();
    //Método para pesquisar por dado:
    ResultSet  searchPK(int id);
    //Método para remover algum dado:
    boolean remove(int id);
    //Método para Desconectar com o Banco:
    void desconect();
}
