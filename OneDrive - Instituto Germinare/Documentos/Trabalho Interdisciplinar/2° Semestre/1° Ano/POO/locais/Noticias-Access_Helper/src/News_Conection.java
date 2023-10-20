import java.sql.*;

public class News_Conection extends News implements Conection{
    //Gerencia e executa os comandos sql:
    private PreparedStatement pstmt;
    private ResultSet rs;

    //Conectar com o banco:
    private Connection conn;

    //Construtor
    public News_Conection(int id, String headline, String image, String link){
        super(id, headline, image, link);
    }

    //Método para conectar com o banco:
    public boolean conect() {
        try {
            //Informando qual driver de conexão será utilizado pelo DriveManager:
            Class.forName("org.postgresql.Driver");

            //Crianda a conexão com o BD:
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://dpg-ckc6t4siibqc73a1u2og-a.oregon-postgres.render.com:5432/access_helper_db", "admin", "1bUxOfOgOeO9vmFnkpqAI3sKWRTqnBKg");

            return true;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return false;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    //Método para inserir valores ao banco:
    public boolean insert(int id, String headline, String image, String link){
        try {
            //Conectando com o banco:
            conect();

            pstmt = conn.prepareStatement("INSERT INTO NEWS (ID, HEADLINE, IMAGE, LINK) VALUES(?,?,?,?)");

            //Setando o valor dos parâmetros:
            pstmt.setInt(1, id);
            pstmt.setString(2, headline);
            pstmt.setString(3, image);
            pstmt.setString(4, link);
            //Executando o comando sql do preparedStatement:
            pstmt.execute();

            //Retorno do boolean:
            return true;
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }finally {
            //Fechando a conexão com o banco:
            desconect();
        }
    }

    //Alterar Qualquer dado:
    public boolean alter(int id, String data, String column){
        try {
            conect(); //Conectando ao Banco.

            pstmt = conn.prepareStatement("UPDATE NEWS SET " + column + " = ? WHERE ID = ?");

            //Setando parâmetros:
            pstmt.setString(1, data);
            pstmt.setInt(2, id);

            int count = pstmt.executeUpdate();

            //Verifica se a alteração deu certo:
            if(count > 0 ){
                return true;
            }
            //Verifica se a alteração não ocorreu:
            else{
                return false;
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
            return false;
        }finally {
            //Fechando a conexão com o banco:
            desconect();
        }
    }

    //Método Buscar:
    public ResultSet search(){
        try {
            //Conectando com o Banco:
            conect();

            //Instanciando o objeto preparedStatemente(pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM NEWS ORDER BY ID");

            //Executando o comando sql do objeto preparedStatemente e armazenadno no ResultSet
            rs = pstmt.executeQuery();

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Desconectando do banco:
            desconect();
        }
        return rs; //Retornando o ResultSet.
    }

    //Método para Buscar por ID:
    public ResultSet searchPK(int id){
        try {
            //Conectando com o banco.
            conect();

            //Instanciando o objeto preparedStatemente(pstmt)
            pstmt = conn.prepareStatement("SELECT * FROM NEWS WHERE ID = ?");

            //Passando o parâmetro:
            pstmt.setInt(1, id);

            //Executando o comando sql do objeto preparedStatemente e armazenadno no ResultSet
            rs = pstmt.executeQuery();

        }catch(SQLException sqle){
            sqle.printStackTrace();
            return null;
        }finally {
            //Desconectando do banco:
            desconect();
        }
        return rs; //Retornando o ResultSet.
    }

    //Método para remover algum dado:
    public boolean remove(int id) {
        try {
            conect(); //Conectando ao Banco.
            String remove = "DELETE FROM NEWS WHERE ID = ? ";

            //Instanciando o objeto preparedStatemente (pstmt):
            pstmt = conn.prepareStatement(remove);

            //Setando o valor do parâmetro.
            pstmt.setInt(1, id);

            //Executando o comando sql preparedStatement.
            int count = pstmt.executeUpdate();

            //Verifica se a alteração deu certo:
            if (count > 0) {
                return true;
            }
            //Verifica se a alteração não ocorreu:
            else {
                return false;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        } finally {
            //Fechando a conexão com o banco:
            desconect();
        }
    }

    //Método Desconectar:
    public void desconect() {
        try {
            if (this.conn != null && !this.conn.isClosed()) {
                this.conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
