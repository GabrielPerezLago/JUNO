<h1 align="center" style="font-size: 5rem; color: rgb(149, 99, 241);">
     <i>Juno</i>
     
</h1>
<div align="center" style="display: flex; justify-content: center;">
<img src="./Docs/images/juno.png" width="100" alt="Juno Logo" />
</div>

## Introduccion
Juno es un proyecto orientado a la gestion de escuelas infatiles privadas, permitiendo la gestion de:

- Plazas y Solicitudes
- Informes evolutivos de los alumnos 
- Control de horarios del centro
- Control de fichages y jornadas de los empleados

## Tecnologías
<div align="center" style="display: flex; justify-content: center;">
<img src="./Docs/images/spring.svg" width="100" alt="Spring Logo" style="padding: 1rem"/>
<img src="./Docs/images/flutter.png" alt="Flutter Logo" width="100" style="padding: 1rem"/>
<img src="./Docs/images/postgres.png" width="100" height="100" alt="PostgreSql Logo" style="padding: 1rem" />
</div>

___
___
### Base de Datos

- PostgreSQL

### Backend

- Java
  - Spring
    - SpringBoot
    - SpringSecurity
  - ApiRest
  - JWT
  - Lombok
  
### Frontend Multiplatform (Movile & Desktop)
 - Flutter 
   - MaterialDesign
   - GoRouter

### Deploy 
- Docker 
  - docker-compose
  - Dokploy
- Cloudflare
---
---
---
## Uso e Instalación

Es necesario un archivo **.env** para levantar el beckend:

- .env.example -> Incluye las variables de entorono necesarias en el sistema

### Docker Compose 
  El archivo *docker-compose.yaml* construye la aplicacion (Api Incluida ) contruyendo desde la base de codigo que se encuentre el el repositorio.


## API 

*JUNO* tambien cuenta con acceso a la api publica en al nuve pudiendo hacer peticiones HTTPS a la api desde cualquier lugar.


```
https://juno.gabriel.living
```

#### TEST  
 ```
 https://juno.gabriel.living/test
 ```


## Documentacion  API

### Auth

| Method | Endpoint | Response   | Error Response 
|---|---|---|---| 
| POST | /auth/signin | 200 | 401 |
| POST | /auth/signup | 200 | 401 |
| POST | /auth/signin/token | 200 | 401 |

### Registro ( /auth/signup )
*Body Request*
```
{
  "nombre": "example",
  "apellidos": "example example",
  "dni": "00000000F",
  "email": "example@juno.es",
  "password" : "example@.",
  "telefono": "+34 222 444 555"
}
```
*Campos añadidos*
```
{
  "nacimiento" : "2025/03/05",
  "idCentro": 1,
  "idAula" 1,
}
```

*Response Body*
```
  {
    token: "dasfsdfa..."
    refreshToken: "ASD232DA...."
  }
```


___

### Inicio Sesión
*Query Params Request*

[
  email, 
  password
]


```
/auth/signin?email=example@juno.es&password=example@.

```

*Response Body*
```
  {
    token: "dasfsdfa..."
    refreshToken: "ASD232DA...."
  }
```

___

### Incio Sesión por Token
*HEADER REQUEST*
 ##### $ -> hace referencia a un token real
```
Authorization : "Bearer $token"

```


*Response Body*
```
  {
    token: "dasfsdfa..."
    refreshToken: "ASD232DA...."
  }
```




