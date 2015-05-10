# PoolConexiones

Proyecto creado para generar una lista de conexiones abiertas a una BD y controlar de este modo la concurrencia. Evitando colapsos por tener demasiadas abiertas.
Se ha gestionado mediante un monitor en lenguaje java.

La parte del PoolManagerJDBC esta implementada con la tecnología que proporciona la clase DataSource de java y el servidor Tomcat. Extraido del tutorial del siguiente enlace https://www.mulesoft.com/tcat/tomcat-mysql