Aplicación que cumple con los requisitos mínimos:
Aplicación Angular que hace peticiones REST a un servidor Spring que se conecta a una base
de datos, para manejar datos de los Cuerpos de las Fuerzas Armadas.
En Angular :
  Aplicación de tres pantallas: la principal: path: ''
                                la de details: path: 'details/:id'
                                la de añadir cuerpo: path: 'add-cuerpo'
  Capaz de listar todos los elementos.
  Capaz de añadir nuevos Cuerpos.
En Spring :
  API REST que responde a verbo POST para crear elementos, al verbo GET para recuperar todos los elementos de una BBDD MySQL con una tabla de 7 campos.

Aplicación que cumple con las siguientes funcionalidades adicionales:
  Cumple con la funcionalidad de Consulta: 
    En Angular: Pantalla de consulta de los datos de un elemento.
    En Spring: Verbo GET para recuperar un elemento de la BBDD por id.    
  Cumple con la funcionalidad de Modificación:
    En Angular: Formulario de modificación de los elementos.
    En Spring: Verbo PUT para actualizar un elemento en la BBDD.
               Verbo PUT para actualizar sólo el campo pais.
  Cumple con la funcionalidad de Eliminación: 
    En Angular: Botón de eliminación de un elemento (en listado y/0 pantalla de consulta).
    En Spring: Verbo DELETE para eliminar un elemento de la BBDD.
  Cumple con la funcionalidad de que uno de los campos tengan valores predeterminados: 
    En Angular: Widget de selección para los valores de las pantallas de creación/modificación.
    En Spring: Controlador para los valores, con GET para recuperarlos de la BBDD.
  Cumple con la funcionanidad de búsqueda:
    En Angular: Paginación y ordenación y criterio de búsqueda.
    En Spring: Verbo GET con paginación, ordenación y filtrado.
               Verbo GET que hace búsqueda de cuerpos que contengan un término de búsqueda específico y devuelve una lista de cuerpos que coincidan con el término                 de búsqueda.
               Verbo GET que maneja solicitudes GET a /cuerpos/paises. 
               Devuelve una lista de todos los países distintos presentes en la base de datos de cuerpos.
  Cumple con Test Unitarios: 
    - Pruebas unitarias escritas utilizando JUnit y JsonTest de Spring Boot para verificar la serialización y deserialización de objetos Cuerpo a JSON. 
      cuerpoSerializationTest(): Esta prueba verifica si un objeto Cuerpo se serializa 
      cuerpoDeserializationTest(): Esta prueba verifica si un JSON dado se deserializa correctamente a un objeto Cuerpo.correctamente a JSON.
      cuerpoListSerializationTest(): Esta prueba verifica si una lista de objetos Cuerpo se serializa correctamente a JSON.
      cuerpoListDeserializationTest(): Esta prueba verifica si un JSON dado que representa una lista de objetos Cuerpo se deserializa correctamente.
      shouldReturnACuerpoWhenDataIsSaved: Realiza una solicitud GET para obtener la información del cuerpo con ID 999999 y luego compara los valores recibidos con        los esperados.
      shouldNotReturnACuerpoWithAnUnknownId:  Realiza una solicitud GET para obtener la información del cuerpo con ID 1000 y espera recibir un código de estado 404       (NOT FOUND).
      shouldCreateANewCuerpo: Primero crea un nuevo objeto Cuerpo, lo envía al servidor a través de una solicitud POST y luego verifica que la creación haya sido         exitosa y que los valores recibidos coincidan con los esperados.
      shouldReturnAllCuerposWhenListIsRequested: Realiza una solicitud GET para obtener la lista de cuerpos y luego compara los valores recibidos con los esperados.
      shouldReturnAPageOfCuerpos: Realiza una solicitud GET para obtener una página de cuerpos con paginación y luego verifica que la página recibida                     contenga el número esperado de cuerpos.
      shouldReturnASortedPageOfCuerpos: Realiza una solicitud GET para obtener una página de cuerpos con paginación y ordenamiento y luego verifica que la página         recibida contenga el cuerpo esperado en la posición esperada.
      shouldReturnASortedPageOfCuerposWithNoParametersAndUseDefaultValues: Realiza una solicitud GET para obtener una página de cuerpos sin especificar ningún            parámetro y luego verifica que la página recibida contenga los cuerpos esperados ordenados por defecto.
      shouldUpdateAnExistingCuerpo: Primero recupera un cuerpo militar existente, luego actualiza sus datos y realiza una solicitud PUT al servidor para guardar          los cambios. Finalmente, verifica que los datos del cuerpo actualizado coincidan con los esperados.
      shouldDeleteAnExistingCuerpo: Primero recupera un cuerpo militar existente (o crea uno nuevo si no hay ninguno), luego realiza una solicitud DELETE al              servidor para eliminarlo y finalmente verifica que la eliminación haya sido exitosa.
      shouldNotDeleteACuerpoThatDoesNotExist: Realiza una solicitud DELETE para eliminar un cuerpo con un ID que no existe y espera recibir un código de estado 404       (NOT FOUND).
      shouldUpdatePaisOfExistingCuerpo, prueba la funcionalidad de actualizar el país de un Cuerpo Militar existente. 
      shouldCreateNewCuerpoDos: prueba la funcionalidad de crear un nuevo Cuerpo Militar. 

  Con estos Test Unitarios, se cumple que sean dos mínimo por verbo.
      

  Cumple con Diseño y usabilidad.
    
  
  


[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/6iGMrP35)
