        package com.example.literalura.principal;

        import com.example.literalura.model.DadosResultados;
        import com.example.literalura.model.Livro;
        import com.example.literalura.repository.LivroRepository;
        import com.example.literalura.service.ConsumoApi;
        import com.example.literalura.service.ConverteDados;
        import java.util.Scanner;

        public class Principal {
            private Scanner leitura = new Scanner(System.in);
            private ConsumoApi consumoApi = new ConsumoApi();
            private ConverteDados conversor = new ConverteDados();
            private final String ENDERECO = "https://gutendex.com/books/";


            private LivroRepository repositorio;


            public Principal(LivroRepository repositorio) {
                this.repositorio = repositorio;
            }

            public void exibeMenu() {
                var opcao = -1;
                while (opcao != 0) {
                    var menu = """
                            *************************************************
                                                ===== SEJA BEM-VINDO AO LITERALURA! =====
                                                *************************************************
                                                Escolha o número de sua opção:
                            
                                                1 - Buscar livro pelo título.
                            
                                                2 - Mostrar os livros que foram registrados no BD.
                            
                                                3 - Listar Autores registrados.
                            
                                                4 - Listar livros por idiomas.
                            
                                                5 - Listar os autores vivos de determinado ano.
                            
                                                0 - Sair
                                                *************************************************
                            """;
                    System.out.println(menu);
                    opcao = leitura.nextInt();
                    leitura.nextLine();

                    switch (opcao) {
                        case 1:
                            buscarLivroWeb();
                            break;

                        case 2:
                            listarLivrosRegistrados();
                            break;

                        case 3:
                            listarAutoresRegistrados();
                            break;

                        case 4:
                            listarLivrosPorIdioma();
                            break;

                        case 5:
                            listarAutoresVivosNoAno();
                            break;

                        case 0:
                            System.out.println(" Saindo do LiterAlura... Até logo!");
                            break;
                        default:
                            System.out.println("Opção inválida! Tente novamente.");

                    }
                }
            }

            private void buscarLivroWeb() {
                System.out.println("Digite o livro que gostaria de envcontrar: ");
                var nomeLivro = leitura.nextLine();

                System.out.println("Buscando na internet por livro: " + nomeLivro + "...\n");

                var enderecoBusca = ENDERECO + "?search=" + nomeLivro.replace(" ", "%20");

                var json = consumoApi.obterDados(enderecoBusca);
                var dados = conversor.obterDados(json, DadosResultados.class);

                if (dados.resultados().isEmpty()) {
                    System.out.println("Que pena! Não foram encontrados dados com o nome desse livro :( ");
                } else {
                    var dadosLivro = dados.resultados().get(0);
                    Livro livro = new Livro(dadosLivro);
                    repositorio.save(livro);


                    System.out.println("\n------- LIVRO SALVO NO BANCO COM SUCESSO! -------");
                    System.out.println("Título: " + livro.getTitulo());
                    System.out.println("Autor: " + livro.getAutor());
                    System.out.println("Idioma: " + livro.getIdioma());
                    System.out.println("Downloads: " + livro.getNumeroDownloads());
                    System.out.println("-------------------------------------------------\n");
                }
            }

            private void listarLivrosRegistrados() {
                System.out.println("\n====== LIVROS REGISTRADOS NO BANCO ======");
                var livros = repositorio.findAll();

                if (livros.isEmpty()) {
                    System.out.println("Não há nenhum livro nos registros ainda!");
                } else {
                    livros.forEach(l -> {
                        System.out.println("Título: " + l.getTitulo());
                        System.out.println("Autor: " + l.getAutor());
                        System.out.println("Idioma: " + l.getIdioma());
                        System.out.println("Downloads: " + l.getNumeroDownloads());
                        System.out.println("-----------------------------------------");
                    });
                }
            }

            private void listarAutoresRegistrados() {
                System.out.println("\n====== AUTORES REGISTRADOS NO BANCO ======");
                var livros = repositorio.findAll();

                if (livros.isEmpty()) {
                    System.out.println("Não há Autores nos registros ainda!");
                } else {
                    livros.stream()
                            .map(Livro::getAutor)
                            .distinct()
                            .forEach(autor -> System.out.println("- " + autor));
                    System.out.println("==========================================");
                }
            }

            private void listarLivrosPorIdioma() {
                System.out.println("\nDigite o idioma que deseja buscar... (ex: en, pt etc): ");
                var idiomaBusca = leitura.nextLine();

                var livros = repositorio.findAll();

                var livrosFiltrados = livros.stream()
                        .filter(l -> l.getIdioma().equalsIgnoreCase(idiomaBusca))
                        .toList();

                if (livrosFiltrados.isEmpty()) {
                    System.out.println("Não há livros no registro com esse idioma!");

                } else {
                    System.out.println("\n====== LIVROS EM '" + idiomaBusca.toUpperCase() + "' ======");
                    livrosFiltrados.forEach(l -> {
                        System.out.println("Título: " + l.getTitulo());
                        System.out.println("Autor: " + l.getAutor());
                        System.out.println("-----------------------------------------");
                    });
                }
            }
                private void listarAutoresVivosNoAno(){

                    System.out.println("\nDigite o ano que deseja pesquisar (ex: 1850): ");
                    var ano = leitura.nextInt();
                    leitura.nextLine();

                    var livros = repositorio.findAll();

                    var autoresVivos = livros.stream()
                            .filter(l -> l.getAnoNascimentoAutor() != null && l.getAnoNascimentoAutor() <= ano)
                            .filter(l -> l.getAnoFalecimentoAutor() == null || l.getAnoFalecimentoAutor() > ano)
                            .map(Livro::getAutor)
                            .distinct()
                            .toList();

                    if (autoresVivos.isEmpty()) {
                        System.out.println("Nenhum autor vivo encontrado no ano de " + ano + "!");
                    } else {
                        System.out.println("\n====== AUTORES VIVOS NO ANO DE " + ano + " ======");
                        autoresVivos.forEach(autor -> System.out.println("- " + autor));
                        System.out.println("==========================================");
                    }

                }
            }
