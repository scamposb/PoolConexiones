# PoolConexiones

Proyecto creado para generar una lista de conexiones abiertas a una BD y controlar de este modo la concurrencia. Evitando colapsos por tener demasiadas abiertas.
Se ha pretendido gestionar de dos maneras distintas:

-La parte del PoolManager se gestiona con un monitor implementado junto con el patr�n de dise�o Singleton. Actualmente funcionando.
-La parte del PoolManagerJDBC esta implementada con la tecnolog�a que proporciona la clase DataSource de java y el servidor Tomcat. Extraido del tutorial del siguiente enlace https://www.mulesoft.com/tcat/tomcat-mysql. No est� operativa por el momento.