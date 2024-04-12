FROM mysql:latest

# variáveis de ambiente para o banco de dados
ENV MYSQL_DATABASE=operacoes_bancarias
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=1234567
ENV MYSQL_ROOT_PASSWORD=1234567
EXPOSE 3306

# volume para persistir os dados do banco de dados
VOLUME /var/lib/mysql
CMD ["mysqld"]