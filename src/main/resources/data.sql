INSERT INTO USUARIO (usu_txt_apelido, usu_txt_email, usu_txt_senha, usu_txt_role)
SELECT 'admin', 'admin@admin.com.br', '$2a$10$B1Off0ppL3AlKLwni195M.21mppG8a8SCZV7u7tmnp7icnNKrf/ly', 'ADMIN'
    WHERE NOT EXISTS (
    SELECT 1 FROM USUARIO WHERE usu_txt_apelido = 'admin'
);

INSERT INTO USUARIO (usu_txt_apelido, usu_txt_email, usu_txt_senha, usu_txt_role)
SELECT 'usuario', 'usuario@usuario.com.br', '$2a$10$ZtnItGAUfjTJn1HxpGac2u04JU5Pkun4fC0W9/ul8eQ3Fg21vgqZ6', 'USUARIO'
    WHERE NOT EXISTS (
    SELECT 1 FROM USUARIO WHERE usu_txt_apelido = 'usuario'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'The Witcher 3: Wild Hunt',
       'Uma jornada épica de Geralt de Rívia, o caçador de monstros, enquanto ele busca sua filha adotiva em um mundo aberto e repleto de perigos.',
       'CD Projekt Red', 2015, 0.0, 0,
       'https://i.imgur.com/qbeAk58.jpeg',
       'https://i.imgur.com/stJiDny.jpeg',
       'https://youtu.be/c0i88t0Kacs?si=vJ7htr9VjxMvGn_0'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'The Witcher 3: Wild Hunt'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'The Legend of Zelda: Breath of the Wild',
       'Link embarca em uma aventura para derrotar Ganon e salvar o Reino de Hyrule, explorando um vasto mundo aberto.',
       'Nintendo', 2017, 0.0, 0,
       'https://i.imgur.com/UsYtmqd.jpeg',
       'https://i.imgur.com/BKSw9fe.jpeg',
       'https://youtu.be/1rPxiXXxftE?si=VdPZt5UJQva0AtIE'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'The Legend of Zelda: Breath of the Wild'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'Cyberpunk 2077',
       'Em um futuro distópico, você assume o papel de V, um mercenário em busca de um chip com memórias digitais, enquanto explora Night City.',
       'CD Projekt Red', 2020, 0.0, 0,
       'https://sm.ign.com/ign_br/cover/c/cyberpunk-/cyberpunk-2077_q3fy.jpg',
       'https://i.imgur.com/Qlel129.jpeg',
       'https://youtu.be/8X2kIfS6fb8?si=vilyEcAlXDpHa__j'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'Cyberpunk 2077'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'Minecraft',
       'Um jogo de construção de mundo aberto, onde os jogadores podem explorar, minerar recursos e criar o que sua imaginação permitir.',
       'Mojang Studios', 2011, 0.0, 0,
       'https://i.imgur.com/aS3JRWe.jpeg',
       'https://i.imgur.com/cKCr48a.png',
       'https://youtu.be/MmB9b5njVbA?si=whTia4EMhmbEVfOS'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'Minecraft'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'Red Dead Redemption 2',
       'A história de Arthur Morgan, membro da gangue Van der Linde, em uma jornada pelo Velho Oeste, enfrentando dilemas morais e a decadência da sua gangue.',
       'Rockstar Games', 2018, 0.0, 0,
       'https://i.imgur.com/EkvApIe.jpeg',
       'https://i.imgur.com/YIlBWxS.jpeg',
       'https://youtu.be/gmA6MrX81z4?si=hvdBtbjo_43Vz2Qm'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'Red Dead Redemption 2'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'God of War',
       'Kratos, o deus da guerra, deve enfrentar novos desafios e deuses nórdicos com seu filho Atreus em um mundo mitológico cheio de aventuras.',
       'Santa Monica Studio', 2018, 0.0, 0,
       'https://i.imgur.com/OLgSCrh.jpeg',
       'https://i.imgur.com/L6mieXs.jpeg',
       'https://youtu.be/K0u_kAWLJOA?si=DqgcmHTPuKFJGVnV'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'God of War'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'Fortnite',
       'Um jogo de batalha real, onde jogadores competem para ser o último sobrevivente em uma ilha cheia de recursos e inimigos.',
       'Epic Games', 2017, 0.0, 0,
       'https://i.imgur.com/828hOyN.jpeg',
       'https://i.imgur.com/NCi4YpG.jpeg',
       'https://youtu.be/66eAN56g7D8?si=BI1dCGBhcsbjiKsw'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'Fortnite'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'Spider-Man: Miles Morales',
       'Miles Morales assume o manto de Spider-Man e deve defender a cidade de Nova York de uma nova ameaça, com poderes únicos.',
       'Insomniac Games', 2020, 0.0, 0,
       'https://i.imgur.com/K3ARsJp.jpeg',
       'https://i.imgur.com/8ZRQD06.jpeg',
       'https://youtu.be/qjRzm9A7DU4?si=jpcc2SYhahP47EZa'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'Spider-Man: Miles Morales'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'Hades',
       'Um rogue-like onde você controla Zagreus, filho de Hades, tentando escapar do submundo e derrotar os deuses do Olimpo.',
       'Supergiant Games', 2020, 0.0, 0,
       'https://i.imgur.com/Lf3EeLF.jpeg',
       'https://i.imgur.com/ln2V3A9.jpeg',
       'https://youtu.be/91t0ha9x0AE?si=kv4GkWaK2fgiXFV9'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'Hades'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'Overwatch',
       'Um jogo de tiro em equipe, com heróis únicos e habilidades distintas, onde jogadores se enfrentam em batalhas de equipe em mapas variados.',
       'Blizzard Entertainment', 2016, 0.0, 0,
       'https://i.imgur.com/j3Svr41.jpeg',
       'https://i.imgur.com/2zO2Ivi.jpeg',
       'https://youtu.be/FqnKB22pOC0?si=f6XFUgPkfOkCaedc'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'Overwatch'
);

INSERT INTO JOGO (jog_txt_nome, jog_txt_descricao, jog_txt_empresa, jog_int_ano, jog_dp_nota, jog_int_quantidade_avaliacoes, jog_txt_link_capa, jog_txt_link_banner, jog_txt_link_trailer)
SELECT 'GTA V',
       'Um jogo de ação e aventura em mundo aberto, onde jogadores podem explorar a cidade fictícia de Los Santos e se envolver em atividades criminosas.',
       'Rockstar Games', 2013, 0.0, 0,
       'https://i.imgur.com/ziBrrYa.jpeg',
       'https://i.imgur.com/mVw3gS8.jpeg',
       'https://youtu.be/QkkoHAzjnUs?si=ioqZrH5ic4c2X0aj'
    WHERE NOT EXISTS (
    SELECT 1 FROM JOGO WHERE jog_txt_nome = 'GTA V'
);