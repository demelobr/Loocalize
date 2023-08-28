# Loocalize
Loocalize é um projeto da disciplina de Introdução à Programação 2 no curso de Ciência da Computação - UFRPE.

Ideias de projeto podem vir de:
- https://www.localiza.com/brasil/pt-br
- https://www.movida.com.br/
- https://www.unidas.com.br/

## Integrantes do grupo
- Bruno de Melo Costa - demelobr10@gmail.com
- Damaris Rodrigues da Silva Fonte - damarisilva03@gmail.com
- Louise de Athayde Vieira - lvathayd@gmail.com
- José Alexandre Lourenço Gonçalves - alexandrelourenco951@gmail.com
- Millena Reis de Lima Cavalcanti - millenareis98@gmail.com

## Descrição geral do projeto
Loocalize é um sistema de gerenciamento de frota de veículos que auxilia a empresa no seu processo de locação. O sistema está voltado para seus colaboradores administradores, colaboradores normais e clientes, cada um deles tendo possibilidades específicas, podendo desde gerenciar colaboradores, frota de veículos a locações dos mesmos.

- Os usuários do sistema serão os Colaboradores(Adm), Colaboradores e Clientes da locadora de veículos.
- Os serviços necessários para o funcionamento do sistema vão desde CRUD's a pesquisas sobre funcionários, clientes, veículos e locações do sistema, permitindo assim o gerenciamento da frota de veículos da empresa.

## Requisitos do projeto
- **REQ 1**: O sistema terá as seguintes classes básicas: Coloborador, Cliente, Veículo, Promoção, Locação, que irão compor a infraestrutura necessária para o sistema.
- **REQ 2**: Haverá três tipos de usuários: **Colaborador(Adm)**, **Colaboradore** e **Cliente**. 
- **REQ 3**: O sistema deverá realizar o cadastro e login dos usuários e direcioná-los para seus respectivos fluxos de tela.
- **REQ 4**: Os Colaboradores(Adm) serão responsáveis pelo gerenciamento, CRUD, dos Colaboradores e Clientes do sistema.
- **REQ 5**: Os Colaboradores serão responsáveis pelo gerenciamento, CRUD, dos veículos da frota da empresa e pelas promoções disponíveis.
- **REQ 6**: Os Clientes terão a possibilidade de alugar veículos disponíveis no catálogo, cumprindo algumas regras de negócio como idade mínima por exemplo.
- **REQ 7**: Os Colaboradores(Adm) são os únicos com acesso para manipular todos os Colaboradores e Clientes cadastrados no sistema.
- **REQ 8**: Os Colaboradores são os únicos com acesso para manipular todos os veículos e promoções cadastradas no sistema.
- **REQ 9**: Os Clientes serão os únicos com acesso à possibilidade de locar um dos veículos disponíveis no catálogo.

## Cronograma
- **Loocalize - MVP v1.0: [REQ1, REQ2, REQ3]** Na primeira versão do MVP, projetamos que iremos entregar toda a implementação das classes básicas, seus respectivos repositórios, a tela de cadastro e login com a possibilidade de validar o funcionamento do registro de usuários e o login deles no sistema, apontando um direcionamento do fluxo de telas dependendo do tipo de usuário que esteja logando.
- **Loocalize - MVP v2.0: [REQ4, REQ5]** Na segunda versão do MVP, projetamos que iremos entregar o fluxo de telas funcional com seus respectivos CRUD's dos usuários: administradores e colaboradores.
- **Loocalize - MVP v3.0: [REQ6, REQ7, REQ8, REQ9]** Na terceira versão do MVP, projetamos que iremos entregar o fluxo de telas funcional para o usuário cliente, deixando bem amarrado as possibilidades e alcance de cada tipo de usuário do sistema.
