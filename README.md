# Proyecto_Poo_Farmacia
## Integrantes: 
Juan Gualotuña, Juan Falconi, Heyer Tinoco
# Documentación de Software

## Introducción
El presente proyecto representa la culminación de nuestros esfuerzos en el aprendizaje de los conceptos teóricos y prácticos de Programación Orientada a Objetos en el semestre actual. 

## Requisitos del Sistema
El sistema debe tener un login que me permita ingresar como administrador o como cajero.
El cajero podrá:
• Realizar una transacción de compra, cada transacción debe ser guardada en conjunto con el
cajero que hizo la transacción.
• Al final de la transacción de compra deberá generar una nota de venta (similar a una factura) en
pdf.
• Cuando compre determinado producto se deberá reducir del stock.
El administrador podrá:
• Ingresar productos a stock.
• Revisar las ventas realizadas por todos los vendedores e individualmente.
• Agregar usuarios cajeros.

## Manual de Usuario:
https://www.youtube.com/watch?v=lnzEc9vdVUQ

## Interfaz de Usuario
### Pantalla de Inicio
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/89c2edec-3760-43f8-b734-b1b13dbb14f4)

Descripción de la pantalla de inicio y sus elementos.

Descripción de las opciones disponibles en el menú principal.

## Funcionalidades del Administrador
### Menu Principal Admin
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/63209134-94b6-4066-b614-3bb60efd4243)

Podemos ver que tenemos cuatro opciones en el menu de Administrador

### Ingresar Productos
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/3879dd11-841d-43f5-b100-797eb6624e8f)

Dentro de ingresar productos el administrador debera ingresar datos en todos los campos de manera obligatoria.

### Actualizar Stock
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/b2c72009-4113-4c8b-affd-0219e5e21814)

Dentro de Actualizar Stock el usuario debe primero buscar el producto por ID despues se muestra la informacion de dicho producto con el stock actual
el usuario debe ingresar el stock actualizado.
### Revisar Ventas
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/ad70be90-e5be-44dd-8464-0e6296692685)

Dentro de Revisar Ventas prodemos observar las ventas realizadas por todos los cajeros.

### Revisar Ventas Por Cajeros
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/fdadbae5-e79d-4696-b89d-822ca96a1d7f)

Podemos seleccionar el cajero al cual queremos revisar sus ventas.

### Revisar Ventas Cajero 1
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/b13aff50-fb2c-4570-a70f-cefb0d697ac5)
Aqui podemos revisar todo lo que a realizado el cajero 1
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/2c7f518f-3f1f-4562-ac08-605eb0dfa3d9)

### Revisar Ventas Cajero 2
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/38072089-4579-4b96-9b5e-8c7501adc0a9)
Aqui podemos revisar todo lo que a realizado el cajero 2
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/bdd2b2e5-255e-49fc-92b0-8cac9fc26fef)

### Crear Usuario
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/bb310268-0655-4f01-907f-f43eebc8e67e)

Dentro de esta funcionalidad podemos crear un nuevo usuario y este posteriormente puede realizar login en la aplicacion
### Funcionalidades del Cajero
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/ffaea1dd-ce52-4271-8455-a559809d4766)

Crear Factura
### Factura

![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/102696740/fbbe4cd5-4a1c-42fe-aedc-a2e3aaf716e1)
Aqui tenemos el sistema de facturacion. En el menu de la derecha esta la opcion de busqueda, donde podemos buscar por nombre: 
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/117741739/e3d4bcf2-efd2-4da3-8fcd-0253aeda48fb)
Y nos saldra todos los similares a ese nombre. 
Y tambien por código: 
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/117741739/43ae446b-be33-4427-b3e6-93d2e72682d2)
Donde nos saldra un producto en especifico. 

En cuanto a la facturacion, no se activara hasta que presionemos "Crear Factura"
Asi que una vez presionado, podemos agregar productos a la factura
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/117741739/ffab4695-0dff-4252-a385-a31c188efa87)

Cuando presionemos enviar, se creara la factura en la base de datos, con  todo calculado: 
![image](https://github.com/OrlandH/Proyecto_Poo_Farmacia/assets/117741739/4dbe19ea-3f85-41ca-b0af-8f26b38bdf1f)

## Conclusión
Resumen final 
Este sistema de gestión de farmacia desarrollado como parte de nuestro aprendizaje en Programación Orientada a Objetos a lo largo de este semestre.
La finalidad principal es mejorar la eficiencia y precisión en la gestión de la farmacia, agilizando el proceso de ventas y manteniendo un registro detallado de todas las operaciones. 


