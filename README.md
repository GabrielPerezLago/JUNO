<h1 align="center">
     Juno
</h1>

## Introduccion
Juno es un proyecto orientado a la gestion de escuelas infatiles privadas, permitiendo la gestion de:

- Plazas y Solicitudes
- Informes evolutivos de los alumnos 
- Control de horarios del centro
- Control de fichages y jornadas de los empleados

## Tecnologías

### Base de Datos

- Postgres Local : "Temporalmente"

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


### Documentacion 

#### Auth

| Method | Endpoint | Response | Error Response 
|---|---|---|---| 
| POST | /auth/signin | 200 | 403 |
| POST | /auth/signup | 200 | 403 |

##### Inicio de Sesión
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
---
*Response Body*
```
  {
    token: "dasfsdfa..."
    refreshToken: "ASD232DA...."
  }
```





