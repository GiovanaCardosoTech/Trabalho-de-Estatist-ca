import java.sql.ResultSet;

public interface Conection {
    //Método para Conectar com o Banco:
    boolean conect();
    //Método para inserir dados:
    boolean insert(String table, int id, String name, String description, String image, String link);
    //Método para Alterar:
    boolean alter(String table, int id, String data, String column);
    //Método para buscar pela tabela inteira:
    ResultSet search(String table);
    //Método para pesquisar por dado:
    ResultSet  searchPK(String table, int id);
    //Método para remover algum dado:
    boolean remove(int id, String table);
    //Método para Desconectar com o Banco:
    void desconect();
}
