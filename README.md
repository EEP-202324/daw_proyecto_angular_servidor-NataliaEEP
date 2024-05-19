Aplicación web para Aula y el Ministerio de Defensa, que permite visualizar los diferentes cuerpos disponibles, la titulación requerida para ingresar, los requisitos de edad, un PDF con información adicional, una foto del cuerpo y el país de destino (este último se podría cambiar por la ciudad española de destino posible). <br>
Esta aplicación está desarrollada utilizando Angular, Spring Boot y MySql. <br>
Aplicación que cumple con los requisitos mínimos: <br>
Aplicación Angular que hace peticiones REST a un servidor Spring que se conecta a una base  <br>
de datos, para manejar datos de los Cuerpos de las Fuerzas Armadas.  <br>
En Angular :  <br>
  - Aplicación de tres pantallas: * la principal: path: '' <br>
                                  * la de details: path: 'details/:id'  <br>
                                  * la de añadir cuerpo: path: 'add-cuerpo'  <br>
  - Capaz de listar todos los elementos.  <br>
  - Capaz de añadir nuevos Cuerpos.  <br>
En Spring :  <br>
  - API REST que responde a verbo POST para crear elementos, al verbo GET para recuperar todos los elementos de una BBDD MySQL con una tabla de 7 campos.  <br>

Aplicación que cumple con las siguientes funcionalidades adicionales:  <br>
  - Cumple con la funcionalidad de Consulta:  <br>
    * En Angular: Pantalla de consulta de los datos de un elemento.  <br>
    * En Spring: Verbo GET para recuperar un elemento de la BBDD por id.  <br>   
 - Cumple con la funcionalidad de Modificación:  <br>
    * En Angular: Formulario de modificación de los elementos.  <br>
    * En Spring: * Verbo PUT para actualizar un elemento en la BBDD.  <br>
                 * Verbo PUT para actualizar sólo el campo pais.  <br>
  - Cumple con la funcionalidad de Eliminación:  <br>
    * En Angular: Botón de eliminación de un elemento (en listado y/0 pantalla de consulta).  <br>
    * En Spring: Verbo DELETE para eliminar un elemento de la BBDD.  <br>
  - Cumple con la funcionalidad de que uno de los campos tengan valores predeterminados:  <br>
    * En Angular: Widget de selección para los valores de las pantallas de creación/modificación.  <br>
    * En Spring: Controlador para los valores, con GET para recuperarlos de la BBDD.  <br>
  - Cumple con la funcionanidad de búsqueda:  <br>
    * En Angular: Paginación y ordenación y criterio de búsqueda.  <br>
    * En Spring: * Verbo GET con paginación, ordenación y filtrado.  <br>
                 * Verbo GET que hace búsqueda de cuerpos que contengan un término de búsqueda específico y devuelve una lista de cuerpos que coincidan con el                         término de búsqueda.  <br>
                 * Verbo GET que maneja solicitudes GET a /cuerpos/paises.  <br>
                 Devuelve una lista de todos los países distintos presentes en la base de datos de cuerpos.  <br>
  Cumple con Test Unitarios:  <br>
    - Pruebas unitarias escritas utilizando JUnit y JsonTest de Spring Boot para verificar la serialización y deserialización de objetos Cuerpo a JSON.  <br>
      * cuerpoSerializationTest(): Esta prueba verifica si un objeto Cuerpo se serializa  <br>
      * cuerpoDeserializationTest(): Esta prueba verifica si un JSON dado se deserializa correctamente a un objeto Cuerpo.correctamente a JSON.  <br>
      * cuerpoListSerializationTest(): Esta prueba verifica si una lista de objetos Cuerpo se serializa correctamente a JSON.  <br>
      * cuerpoListDeserializationTest(): Esta prueba verifica si un JSON dado que representa una lista de objetos Cuerpo se deserializa correctamente.  <br>
      * shouldReturnACuerpoWhenDataIsSaved: Realiza una solicitud GET para obtener la información del cuerpo con ID 999999 y luego compara los valores recibidos          con los esperados.  <br>
      * shouldNotReturnACuerpoWithAnUnknownId:  Realiza una solicitud GET para obtener la información del cuerpo con ID 1000 y espera recibir un código de estado         404 (NOT FOUND).  <br>
      * shouldCreateANewCuerpo: Primero crea un nuevo objeto Cuerpo, lo envía al servidor a través de una solicitud POST y luego verifica que la creación haya sido       exitosa y que los valores recibidos coincidan con los esperados.  <br>
      * shouldReturnAllCuerposWhenListIsRequested: Realiza una solicitud GET para obtener la lista de cuerpos y luego compara los valores recibidos con los               esperados.  <br>
      * shouldReturnAPageOfCuerpos: Realiza una solicitud GET para obtener una página de cuerpos con paginación y luego verifica que la página recibida                   contenga el número esperado de cuerpos.  <br>
      * shouldReturnASortedPageOfCuerpos: Realiza una solicitud GET para obtener una página de cuerpos con paginación y ordenamiento y luego verifica que la página       recibida contenga el cuerpo esperado en la posición esperada.  <br>
      * shouldReturnASortedPageOfCuerposWithNoParametersAndUseDefaultValues: Realiza una solicitud GET para obtener una página de cuerpos sin especificar ningún          parámetro y luego verifica que la página recibida contenga los cuerpos esperados ordenados por defecto.  <br>
      * shouldUpdateAnExistingCuerpo: Primero recupera un cuerpo militar existente, luego actualiza sus datos y realiza una solicitud PUT al servidor para guardar        los cambios. Finalmente, verifica que los datos del cuerpo actualizado coincidan con los esperados.  <br>
      * shouldDeleteAnExistingCuerpo: Primero recupera un cuerpo militar existente (o crea uno nuevo si no hay ninguno), luego realiza una solicitud DELETE al            servidor para eliminarlo y finalmente verifica que la eliminación haya sido exitosa.  <br>
      * shouldNotDeleteACuerpoThatDoesNotExist: Realiza una solicitud DELETE para eliminar un cuerpo con un ID que no existe y espera recibir un código de estado         404 (NOT FOUND).  <br>
      * shouldUpdatePaisOfExistingCuerpo, prueba la funcionalidad de actualizar el país de un Cuerpo Militar existente.  <br>
      * shouldCreateNewCuerpoDos: prueba la funcionalidad de crear un nuevo Cuerpo Militar.  <br>

  Con estos Test Unitarios, se cumple que sean dos mínimo por verbo.  <br>
      

  Cumple con Diseño y usabilidad.  <br>
    
  ENLACE AL VIDEO EN STREAM: <BR>
  https://eepmad-my.sharepoint.com/:v:/g/personal/ncarolina-martinez1_eep-igroup_com/EQPzjY31gc9KuJAvnR2GkhoBzb-FufcfkdgHN9JxGUEbZQ?e=3zkWrd&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D


[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/6iGMrP35)
