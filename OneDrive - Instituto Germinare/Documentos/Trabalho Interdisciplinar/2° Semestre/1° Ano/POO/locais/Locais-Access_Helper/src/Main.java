import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Locais - Access Helper");

        //Atributos:
        int id;
        String name;
        String description;
        String image;
        String link;
        String column;
        String data = null;

        //Nome da tabela:
        String table;

        //Definindo Scanner:
        Scanner input = new Scanner(System.in);

        //Definindo Objeto para a conexão com o banco:
        Locations locations;
        Locations_Conection locais = new Locations_Conection("",0,"","","","");

        //Verificando conexão:
        if(locais.conect()){
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
                locais.desconect();
            }
            else {
                System.out.println("\nEm qual tabela irá inserir, remover, buscar ou alterar: ");
                table = input.next().toLowerCase();

                if (escolha == 1) {
                    System.out.println("\nDigite os seguintes valores: ");
                    System.out.println("\nID: ");
                    id = input.nextInt();

                    System.out.println("\nNome: ");
                    name = input.next();

                    System.out.println("\nDescrição: ");
                    input.nextLine();
                    description = input.nextLine();

                    System.out.println("\nImagem: ");
                    image = input.next();

                    System.out.println("\nLink: ");
                    link = input.next();

                    if (table.equalsIgnoreCase("restaurant")) {
                        //Adicionando dados ao objeto:
                        locations = new Restaurant(id, name, description, image, link);

                        //Passando dados:
                        System.out.println("\n" + locais.insert(table, locations.getId(), locations.getName(), locations.getDescription(), locations.getImage(), locations.getLink()));
                    }
                    else if (table.equalsIgnoreCase("culturalarea")) {
                        //Adicionando dados ao objeto:
                        locations = new CulturalArea(id, name, description, image, link);

                        //Passando dados:
                        System.out.println("\n" + locais.insert(table, locations.getId(), locations.getName(), locations.getDescription(), locations.getImage(), locations.getLink()));

                    }
                    else if (table.equalsIgnoreCase("school")) {
                        //Adicionando dados ao objeto:
                        locations = new School(id, name, description, image, link);

                        //Passando dados:
                        System.out.println("\n" + locais.insert(table, locations.getId(), locations.getName(), locations.getDescription(), locations.getImage(), locations.getLink()));

                    }
                    else if (table.equalsIgnoreCase("entertainment")) {
                        //Adicionando dados ao objeto:
                        locations = new Entertainment(id, name, description, image, link);

                        //Passando dados:
                        System.out.println("\n" + locais.insert(table, locations.getId(), locations.getName(), locations.getDescription(), locations.getImage(), locations.getLink()));

                    }
                    else if (table.equalsIgnoreCase("hospital")) {
                        //Adicionando dados ao objeto:
                        locations = new Hospital(id, name, description, image, link);

                        //Passando dados:
                        System.out.println("\n" + locais.insert(table, locations.getId(), locations.getName(), locations.getDescription(), locations.getImage(), locations.getLink()));
                    }
                }
                else if (escolha == 2) {
                    System.out.println("\nDigite a coluna que deseja alterar: ");
                    column = input.next().toLowerCase();

                    if (column.equalsIgnoreCase("name")) {
                        System.out.println("\nDigite o nome para alteração: ");
                        input.nextLine();
                        data = input.nextLine();
                    } else if (column.equalsIgnoreCase("description")) {
                        System.out.println("\nDigite a descrição para alteração: ");
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

                    if (table.equalsIgnoreCase("restaurant")) {
                        System.out.println("\n" + locais.alter(table, id, data, column));

                    } else if (table.equalsIgnoreCase("culturalarea")) {
                        System.out.println("\n" + locais.alter(table, id, data, column));

                    } else if (table.equalsIgnoreCase("school")) {
                        System.out.println("\n" + locais.alter(table, id, data, column));

                    } else if (table.equalsIgnoreCase("entertainment")) {
                        System.out.println("\n" + locais.alter(table, id, data, column));

                    } else if (table.equalsIgnoreCase("hospital")) {
                        System.out.println("\n" + locais.alter(table, id, data, column));
                    }
                }
                else if (escolha == 3) {
                    //Definindo o ResultSet:
                    ResultSet rs = locais.search(table);

                    System.out.println(titulo(table));

                    try {
                        while (rs.next()) {
                            String lista = "ID: " + rs.getInt("ID") + " - " + " Name: " + rs.getString("NAME") + " - " + "Description: " +
                                    rs.getString("DESCRIPTION") + " - " + "Image: " + rs.getString("IMAGE") +
                                    " - " + "Link: " + rs.getString("LINK");

                            System.out.println(lista);
                        }
                    } catch (SQLException sqle) {
                        System.out.println("ERRO!!!" + sqle.getMessage());
                    }
                }
                else if (escolha == 4) {
                    System.out.println("Digite o ID da tabela: ");
                    id = input.nextInt();

                    ResultSet rs = locais.searchPK(table, id);

                    System.out.println(titulo(table));

                    try {
                        while (rs.next()) {
                            String lista = "ID: " + rs.getInt("ID") + " - " + " Name: " + rs.getString("NAME") + " - " + "Description: " +
                                    rs.getString("DESCRIPTION") + " - " + "Image: " + rs.getString("IMAGE") +
                                    " - " + "Link: " + rs.getString("LINK");

                            System.out.println(lista);
                        }
                    } catch (SQLException sqle) {
                        System.out.println("ERRO!!!" + sqle.getMessage());
                    }
                }
                else {
                    System.out.println("\nDigite o ID: ");
                    id = input.nextInt();

                    if (table.equalsIgnoreCase("restaurant")) {
                        System.out.println("\n" + locais.remove(id, table));
                    } else if (table.equalsIgnoreCase("culturalarea")) {
                        System.out.println("\n" + locais.remove(id, table));
                    } else if (table.equalsIgnoreCase("school")) {
                        System.out.println("\n" + locais.remove(id, table));
                    } else if (table.equalsIgnoreCase("entertainment")) {
                        System.out.println("\n" + locais.remove(id, table));
                    } else if (table.equalsIgnoreCase("hospital")) {
                        System.out.println("\n" + locais.remove(id, table));
                    }
                }
            }

        }while(escolha != 0);
    }
    public static String titulo(String table){
        return "\nTABELA " + table.toUpperCase();
    }
}