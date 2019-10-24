A ideia inicial passa por trabalhar com sockets em um sistema de cliente e servidor.

1) Uso de Sockets para conectar um cliente e um Servidor
  a) Cliente --> utiliza de estruturas de dados do Jedis para seguir um utilizador,enviar um post ,olhar as mensagens publicadas por um utilizador que seguimos e imprimir a lista de utilizadores dos sistema
  b) Servidor --> a ideia principal era receber todas as publicacoes do utilizador actual e atualizar cada vez que ele vai escrevendo, em tempo real.

comandos para executar: 
javac -cp ".:/home/camila/Transferências/jedis-3.1.0.jar" Client.java
javac -cp ".:/home/camila/Transferências/jedis-3.1.0.jar" Server.java
java -cp ".:/home/camila/Transferências/jedis-3.1.0.jar" Server.java
java -cp ".:/home/camila/Transferências/jedis-3.1.0.jar" Client.java
