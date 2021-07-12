# OrlaJK
Projeto de gestão de atendimento e alocação de clientes em empreendimento alimentício de médio e grande porte.

O projeto foi desenvolvido, a princípio, como parte de um trabalho de conclusão de uma matéria no curso de Análise e Desenvolvimento de Sistemas. No momento está sendo utilizado para prática dos conceitos de Design Patterns e arquiteturas de larga aceitação pelo mercado, como o Clean Code, o Domain Driven Design, o MVC e outras práticas.

No momento o projeto conta com as classes vinculadas a recepção e salão em maior avanço, assim como as entidades e os repositórios.

O projeto utiliza como plano de fundo a biblioteca Spring, com a utilização do Spring Data JPA (vinculado a um banco de dados MySQL), o Spring Web (Utilizando Thymeleaf para a montagem das páginas em HTML) e o Spring Security para o controle de usuários e acessos.

Por fim, para a comunicação com o banco de dados foram aplicadas técnicas para melhor aproveitamento do hibernate, sem que haja uma dependência do tipo de banco de dados.

As páginas web possuem um mínimo de Javascript para funcionar e contam com o usuo do Bootstrap para estilo, com algumas pequenas inclusões de CSS.
