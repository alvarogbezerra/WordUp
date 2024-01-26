# WordUp
Trabalho da disciplina de Sistemas Operacionais

Nosso projeto consiste em um jogo de adivinhação onde os jogadores irão competir para saber quem acertará a palavra primeiro (os jogadores irão chutar a palavra completa, para ganhar a pontuação que eles tem mais o número de letras da palavra a ser adivinhada). Sabendo que a palavra é uma das capitais brasileiras.

Threads serão utilizadas para gerenciar o tempo limite para responder cada pergunta,  e também a criação dos jogadores. O semáforo será utilizado para sincronizar as threads que exibem a pergunta e gerencia o temporizador.

Manual do jogo:  

DICA : é uma capital brasileira;

O jogo só termina quando alguém ganha ;
Ao iniciar o jogo, digite o nome na ordem que será feitas as jogadas;
O jogador 1 escolhe chutar a letra ou a palavra; 
Se ele escolher chutar a letrar e acertar ele ganha 10 pontos, se ele errar ele perde 5 pontos;
No caso de chutar a palavra seus pontos será o numero de letras da palavra mais os pontos que ele tem;
No caso de seus pontos serem negativos, pois erro muitas letras, ele ganha apenas o total de numero da palavra;
Então será a vez do jogador 2 escolher chutar a letra ou palavra;
Para cada chute eles terão 15s para realiza-lo.

CONSIDERE: capitais com nomes composto digite-os juntos e ignore a acentuação
