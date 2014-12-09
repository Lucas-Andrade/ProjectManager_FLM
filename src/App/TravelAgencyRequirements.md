Travel Agency
Objectivo

Pretende-se implementar uma aplicação para gestão de um catálogo de produtos de uma agência de viagens. A realização deste projecto está dividida em três etapas (etapa 1 a etapa 3). Os objectivos de cada etapa são descritos por adições enunciados autónomos com adições ao enunciado corrente.

Descrição do domínio

Utilizadores

A interacção com o sistema de gestão de produtos de uma agência de viagens é realizada por utilizadores. 
		- Cada utilizador é identificado por um nome curto único (username) e possui uma palavra-chave para o autenticar. 
		- Cada utilizador possui ainda um endereço de email único e um nome completo.


Produtos

Os produtos oferecidos pela agência são caracterizados por um nome, uma descrição e um preço, podendo ser produtos simples (quartos de hotel, passeios pela cidade ou actividades radicais), programas ou packs.

Nos produtos simples:
	- um quarto de hotel tem um determinado números de estrelas (que não pode ultrapassar as 5), 
	- um passeio pela cidade é caracterizado pela origem e pelo destino e uma actividade radical distingue-se pelo meio onde é concretizada (aquático, terreste ou aéreo).

Um programa é constituído por um conjunto de produtos heterogéneos. 
	- Estes podem ser simples ou outros programas. 
	- O preço total de um programa é a soma dos preços dos produtos que o compõem.

Um pack é um conjunto de Produtos homogéneo, em que todos os produtos são do mesmo tipo e que podem incluir também packs desse mesmo tipo (exº Pack de hotéis).


Um produto pode expirar, por tempo (numa data), por número de unidades vendidas, ou ambas. 
	- Após a expiração, a produto fica inactivo mas não é removida do sistema.


A agência tem um vasto catálogo de produtos oferecidos e não existem quaisquer restrições ao aumento da sua base de oferta.


Os produtos são adicionados ao sistema, pelos seus proprietários (owners), com os seguintes dados:

Identificador único (gerado automáticamente)
	Nome
	Descrição
	Preço
	Data de expiração (opcional)
	Número máximo de vendas (opcional)
	Aplicação de gestão


Na primeira etapa, a gestão do sistema é realizada através de comandos efectuados numa aplicação consola. 
Todos os comandos têm a seguinte estrutura genérica:
								{method} {path} {parameter list}
								
	O método (method) indica o tipo de acção a realizar (não confundir com o conceito de método em Java).
		- Os comandos de obtenção de informação estão associados ao método GET.
		- Os comandos de criação de informação utilizam o método POST.
	O path representa o recurso sobre o qual é realizado o comando.
	Cada parâmetro é definido pelo par nome=valor.
	A lista de parâmetros (parameter-list) é uma sequência de pares nome=valor, separados por &. 
	Por exemplo:
	 					name=Hotel1&description=Hotel1 Description 
	 representa a lista contendo o parâmetro name com o valor "Hotel1" e o parâmetro description com o valor "Hotel1 Description".

Apresentam-se em seguida os comandos necessários na primeira etapa.

Gestão de utilizadores

POST /users - cria um novo utilizador, dado os seguintes parâmetros
	username - nome único do utilizador;
	password - palavra-chave do utilizador;
	email - email único do utilizador;
	fullname - nome completo (opcional)

GET /users - retorna a lista de utilizadores.

GET /users/{username} - retorna informação sobre o utilizador com nome de utilizador username.



Gestão de Produtos

POST /products/{productType}- cria uma nova produto, dado os seguintes parâmetros
	productType - tipo do produto a criar (hotel, radicalExperience, program, etc)
	name - Nome do produto
	description - descrição da produto
	price - preço do produto
	expirationDate - data de expiração da produto (opcional)
	maxSells - número máximo de vendas da produto (opcional).
	products - lista dos identificadores dos produtos que compõem a produto (apenas definido para produtos compostos)
Os parâmetros anteriotres representam as características de todos os produtos. Caracteristicas específicas de um produto, devem ser passadas como parâmetros adicionais (exº para um hotel ...&stars=4).

Retorna o identificador do produto.

GET /products - retorna a catálogo de todos os produtos da agência, com clara distinção entre produtos simples e programas

GET /products/details/{pid} - retorna os detalhes da produto com identificador nome pid.

GET /products/owner/{owner}- retorna todas produtos de um proprietário

GET /products/price/{price}/below- retorna todas os produtos que estão abaixo de determinado preço, por ordem decrescente


NOTAS:

Os comandos que requerem autenticação (todos os métodos POST) recebem os parâmetros loginName e loginPassword com os dados relativos às credenciais do utilizador.

Na primeira etapa, deve ser suportado o armazenamento de dados em memória. No entanto, outro modo de armazenamento deve, no máximo, implicar a alteração de uma linha de código fonte, mas desejavelmente, nenhuma!
