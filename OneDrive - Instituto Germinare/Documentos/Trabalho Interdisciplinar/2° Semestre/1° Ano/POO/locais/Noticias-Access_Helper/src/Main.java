import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("NOTÍCIAS - ACCESS HELPER");

        //Atributos;
        int id;
        String headline;
        String image;
        String link;
        String column;
        String data = null;

        //Definindo Scanner:
        Scanner input = new Scanner(System.in);

        //Definindo Objeto para a Conexão:
        News_Conection news = new News_Conection(0,"","","");

        //Verificando conexão:
        if(news.conect()){
            System.out.println("\nConexão feita com sucesso!");
        }
        else{
            System.out.println("\nConexão falha!");
        }

        //Variável para verificar escolha:
        int escolha = 0;

        //Variavéis booleanas para utilizar na verificação com try-catch:
        boolean veri_menu;

        do {
            do {
                try {
                    System.out.println("""
                            \nO que deseja fazer:
                            [0] - Sair
                            [1] - Inserir
                            [2] - Alterar
                            [3] - Buscar Tudo
                            [4] - Buscar por ID
                            [5] - Remover
                            """);
                    escolha = input.nextInt();

                    //Verificando se a escolha está dentro das opções:
                    if(escolha <= 5 && escolha >= 0 ){
                        veri_menu = true;
                    }else {
                        throw new Exception();
                    }
                } catch(Exception ime){
                    System.out.println("\nDigite uma das opções acima!!");
                    veri_menu = false;
                    input.nextLine();
                }
            }while(!veri_menu);

            if (escolha == 0) {
                System.out.println("\nSaindo e Desconectando...");
                //Desconectando:
                news.desconect();
            }
            else{
                if(escolha == 1){
                    System.out.println("\nDigite os seguintes valores: ");
                    System.out.println("\nID: ");
                    id = input.nextInt();

                    System.out.println("\nHeadline: ");
                    input.nextLine();
                    headline = input.nextLine();

                    System.out.println("\nImagem: ");
                    image = input.next();

                    System.out.println("\nLink: ");
                    link = input.next();

                    //Adicionando dados:
                    news = new News_Conection(id, headline, image, link);

                    System.out.println("\n" + news.insert(news.getId(), news.getHeadline(), news.getImage(), news.getLink()));
                }
                else if (escolha == 2) {
                    System.out.println("\nDigite a coluna que deseja alterar: ");
                    column = input.next().toLowerCase();

                    if (column.equalsIgnoreCase("headline")) {
                        System.out.println("\nDigite a headline para alteração: ");
                        data = input.nextLine();
                    } else if (column.equalsIgnoreCase("image")) {
                        System.out.println("\nDigite a imagem para alteração: ");
                        data = input.nextLine();
                    } else if (column.equalsIgnoreCase("link")) {
                        System.out.println("\nDigite o link para alteração: ");
                        data = input.nextLine();
                    }

                    System.out.println("\nDigite o ID: ");
                    id = input.nextInt();

                    System.out.println("\n" + news.alter(id, data, column));
                }
                else if (escolha == 3) {
                    //Definindo o ResultSet:
                    ResultSet rs = news.search();

                    System.out.println("TABELA - NEWS");
                    try {
                        while (rs.next()) {
                            String lista = "ID: " + rs.getInt("ID") + " - " + "Headline: " +
                                    rs.getString("HEADLINE") + " - " + "Image: " + rs.getString("IMAGE") +
                                    " - " + "Link: " + rs.getString("LINK");

                            System.out.println(lista);
                        }
                    } catch (SQLException sqle) {
                        System.out.println("ERRO!!!" + sqle.getMessage());
                    }
                }
                else if (escolha == 4) {
                    System.out.println("Digite o ID da notícia: ");
                    id = input.nextInt();

                    ResultSet rs = news.searchPK(id);

                    System.out.println("TABELA - NEWS");

                    try {
                        while (rs.next()) {
                            String lista = "ID: " + rs.getInt("ID") + " - " + "Headline: " +
                                    rs.getString("HEADLINE") + " - " + "Image: " + rs.getString("IMAGE") +
                                    " - " + "Link: " + rs.getString("LINK");

                            System.out.println(lista);
                        }
                    } catch (SQLException sqle) {
                        System.out.println("ERRO!!!" + sqle.getMessage());
                    }
                }
                else{
                    System.out.println("\nDigite o ID: ");
                    id = input.nextInt();

                    //Passando os paramêtros para a remoção:
                    System.out.println("\n" + news.remove(id));
                }
            }
        }while(escolha != 0);
    }
}