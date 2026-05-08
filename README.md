# 📘 Guía de Integración Frontend – Angular

> Documento dirigido al equipo de frontend para integrar con la API de microservicios.

---

## 🚀 Cómo Levantar el Proyecto

### Prerrequisitos
- JDK 17+
- Maven 3.8+
- Docker Desktop (para MySQL)
- Docker Compose

### 1. Levantar MySQL (Docker)
```bash
cd microservicios
docker-compose up -d
```
Esto crea 4 bases de datos MySQL en los puertos:
- 3307 → db_usuarios
- 3308 → db_clientes
- 3309 → db_aplicaciones
- 3310 → db_menus

### 2. Levantar servicios (orden recomendado)

| # | Servicio | Puerto | Comando |
|---|----------|--------|---------|
| 1 | Eureka Server | 8761 | `mvn spring-boot:run` (en eureka-server) |
| 2 | API Gateway | 8080 | `mvn spring-boot:run` (en api-gateway) |
| 3 | MS-Usuarios | 8081 | `mvn spring-boot:run` (en ms-usuarios) |
| 4 | MS-Clientes | 8082 | `mvn spring-boot:run` (en ms-clientes) |
| 5 | MS-Aplicaciones | 8083 | `mvn spring-boot:run` (en ms-aplicaciones) |
| 6 | MS-Menus | 8084 | `mvn spring-boot:run` (en ms-menus) |

**URL Base:** `http://localhost:8080`

---

## 🔐 Autenticación JWT

### Usuarios de prueba (auto-creados al iniciar ms-usuarios)

| Usuario | Password | Estado |
|---------|----------|--------|
| admin | admin123 | ACTIVO |
| jefa | user123 | ACTIVO |
| lparedes | pass123 | ACTIVO |
| mflores | pass123 | ACTIVO |
| rmendoza | pass123 | INACTIVO |

### Flujo de login
```
1. POST /auth/login  →  recibe JWT token
2. Guardar token en localStorage o sessionStorage
3. Enviar token en CADA petición como header:
   Authorization: Bearer <token>
```

### Ejemplo de login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"nombreUsuario": "admin", "password": "admin123"}'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tipo": "Bearer",
  "nombreUsuario": "admin",
  "mensaje": "Login exitoso"
}
```

---

## 📚 Documentación API (Swagger)

### SwaggerUI del Gateway (Todos los servicios)
```
http://localhost:8080/swagger-ui.html
```

### SwaggerUI por servicio individual
| Servicio | URL |
|----------|-----|
| MS-Usuarios | http://localhost:8081/swagger-ui.html |
| MS-Clientes | http://localhost:8082/swagger-ui.html |
| MS-Aplicaciones | http://localhost:8083/swagger-ui.html |
| MS-Menus | http://localhost:8084/swagger-ui.html |

---

## 📁 Estructura de Archivos Sugerida en Angular

```
src/
├── app/
│   ├── core/
│   │   ├── interceptors/
│   │   │   └── auth.interceptor.ts       ← Agrega el token a cada request
│   │   ├── guards/
│   │   │   └── auth.guard.ts             ← Protege rutas privadas
│   │   └── services/
│   │       └── auth.service.ts           ← Login, logout, manejo de token
│   ├── models/
│   │   ├── auth.model.ts
│   │   ├── usuario.model.ts
│   │   ├── persona.model.ts
│   │   ├── acceso-menu.model.ts
│   │   ├── cliente.model.ts
│   │   ├── aplicacion.model.ts
│   │   ├── cliente-aplicacion.model.ts
│   │   └── menu.model.ts
│   └── services/
│       ├── usuario.service.ts
│       ├── persona.service.ts
│       ├── acceso-menu.service.ts
│       ├── cliente.service.ts
│       ├── aplicacion.service.ts
│       ├── cliente-aplicacion.service.ts
│       └── menu.service.ts
```

---

## 🧩 Modelos (Models)

### `auth.model.ts`
```typescript
export interface LoginRequest {
  nombreUsuario: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  tipo: string;        // "Bearer"
  nombreUsuario: string;
  mensaje: string;
}
```

---

### `persona.model.ts`
```typescript
export interface Persona {
  idPersona?: number;
  nombre: string;
  apellido: string;
  email: string;
  telefono?: string;
  tipoDocumento?: string;   // 1 caracter: 'D' = DNI, 'C' = CE, etc.
  docIdentidad?: string;
  direccion?: string;
  distrito?: string;
  provincia?: string;
  departamento?: string;
  fechaReg?: string;        // ISO datetime
  fechaMod?: string;
}
```

---

### `usuario.model.ts`
```typescript
export interface Usuario {
  idUsuario?: number;
  idPersona: number;        // FK hacia Persona
  estado: string;           // 'ACT' | 'INA'
  nombreUsuario: string;
  password?: string;        // Solo al crear/actualizar, nunca mostrar
  fechaReg?: string;
  fechaMod?: string;
}
```

---

### `acceso-menu.model.ts`
```typescript
export interface AccesoMenu {
  id?: number;
  idUsuario: number;        // FK hacia Usuario
  idMenu: string;           // FK hacia Menu (en MS-Menus)
  estado: string;           // 'ACT' | 'INA'
  fechaReg?: string;
  fechaMod?: string;
}
```

---

### `cliente.model.ts`
```typescript
export interface Cliente {
  idCliente?: number;
  idPersona: number;        // FK hacia Persona (en MS-Usuarios)
  razonSocial: string;
  tipoCliente: string;      // 2 caracteres: 'PN' = Natural, 'PJ' = Jurídica
  sector?: string;
  representanteLegal?: string;
  estado: string;           // 'ACT' | 'INA'
  fechaReg?: string;
  fechaMod?: string;
}
```

---

### `aplicacion.model.ts`
```typescript
export interface Aplicacion {
  idAplicacion: string;     // String ID, ej: 'APP001'
  nombre: string;
  estado: string;           // 'ACT' | 'INA'
  fechaReg?: string;
  fechaMod?: string;
}
```

---

### `cliente-aplicacion.model.ts`
```typescript
export interface ClienteAplicacion {
  id?: number;
  idCliente: number;        // FK hacia Cliente
  idAplicacion: string;     // FK hacia Aplicacion
  periodoUso: string;       // 1 caracter: 'M' = Mensual, 'A' = Anual
  fechaIni: string;         // 'YYYY-MM-DD'
  fechaFin: string;         // 'YYYY-MM-DD'
  estado: string;           // 'ACT' | 'INA'
  precioContrato: number;
  dominio?: string;
  url?: string;
  fechaReg?: string;
  fechaMod?: string;
}
```

---

### `menu.model.ts`
```typescript
export interface Menu {
  idMenu: string;           // String ID, ej: 'MNU001'
  idAplicacion: string;    // FK hacia Aplicacion
  nombre: string;
  nivelMenu: string;       // '1' = principal, '2' = submenu
  idMenuPadre?: string;    // null si es menú raíz
  orden?: number;
  icono?: string;          // nombre del icono, ej: 'home', 'settings'
  nivelOrden?: number;
  ruta?: string;          // ruta Angular, ej: '/dashboard'
  fechaReg?: string;
  fechaMod?: string;
}
```

---

## ⚙️ Servicios Core

### `auth.service.ts`
```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginRequest, LoginResponse } from '../models/auth.model';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private readonly BASE_URL = 'http://localhost:8080';
  private readonly TOKEN_KEY = 'jwt_token';
  private readonly USER_KEY  = 'current_user';

  constructor(private http: HttpClient) {}

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.BASE_URL}/auth/login`, credentials)
      .pipe(
        tap(response => {
          localStorage.setItem(this.TOKEN_KEY, response.token);
          localStorage.setItem(this.USER_KEY, response.nombreUsuario);
        })
      );
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_KEY);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getCurrentUser(): string | null {
    return localStorage.getItem(this.USER_KEY);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
```

---

### `auth.interceptor.ts`
```typescript
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.authService.getToken();

    if (token) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
      return next.handle(authReq);
    }

    return next.handle(req);
  }
}
```

> Registrar el interceptor en `app.module.ts`:
> ```typescript
> providers: [
>   { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
> ]
> ```

---

### `auth.guard.ts`
```typescript
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
```

---

## 🔗 Lista Completa de Endpoints

> ⚠️ Todos los endpoints (excepto `/auth/login`) requieren el header:
> ```
> Authorization: Bearer <token>
> ```

---

### 🔑 Autenticación

| Método | Endpoint | Auth | Body | Descripción |
|--------|----------|------|------|-------------|
| POST | `/auth/login` | ❌ | `LoginRequest` | Obtener token JWT |
| GET | `/auth/validate?token=...` | ❌ | — | Verificar si un token es válido |

---

### 👤 Personas  `/api/usuarios/personas`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios/personas` | Listar todas las personas |
| GET | `/api/usuarios/personas/{id}` | Obtener persona por ID |
| POST | `/api/usuarios/personas` | Crear persona |
| PUT | `/api/usuarios/personas/{id}` | Actualizar persona |
| DELETE | `/api/usuarios/personas/{id}` | Eliminar persona |

---

### 👥 Usuarios  `/api/usuarios/usuarios`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios/usuarios` | Listar todos los usuarios |
| GET | `/api/usuarios/usuarios/{id}` | Obtener usuario por ID |
| POST | `/api/usuarios/usuarios` | Crear usuario (password se hashea automáticamente) |
| PUT | `/api/usuarios/usuarios/{id}` | Actualizar usuario |
| DELETE | `/api/usuarios/usuarios/{id}` | Eliminar usuario |

> ⚠️ Al crear un usuario, el `password` se guarda con BCrypt. Nunca mostrar el campo `password` en el frontend.

---

### 🔓 Accesos a Menús  `/api/usuarios/accesos-menus`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/usuarios/accesos-menus` | Listar todos los accesos |
| GET | `/api/usuarios/accesos-menus/{id}` | Obtener acceso por ID |
| GET | `/api/usuarios/accesos-menus/usuario/{idUsuario}` | Accesos de un usuario específico |
| POST | `/api/usuarios/accesos-menus` | Asignar acceso de menú a usuario |
| DELETE | `/api/usuarios/accesos-menus/{id}` | Revocar acceso |

---

### 🏢 Clientes  `/api/clientes/clientes`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/clientes/clientes` | Listar todos los clientes |
| GET | `/api/clientes/clientes/{id}` | Obtener cliente por ID |
| POST | `/api/clientes/clientes` | Crear cliente |
| PUT | `/api/clientes/clientes/{id}` | Actualizar cliente |
| DELETE | `/api/clientes/clientes/{id}` | Eliminar cliente |

---

### 💻 Aplicaciones  `/api/aplicaciones/aplicaciones`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/aplicaciones/aplicaciones` | Listar todas las aplicaciones |
| GET | `/api/aplicaciones/aplicaciones/{id}` | Obtener aplicación por ID |
| POST | `/api/aplicaciones/aplicaciones` | Crear aplicación |
| PUT | `/api/aplicaciones/aplicaciones/{id}` | Actualizar aplicación |
| DELETE | `/api/aplicaciones/aplicaciones/{id}` | Eliminar aplicación |

---

### 📋 Contratos Cliente-Aplicación  `/api/aplicaciones/cliente-aplicacion`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/aplicaciones/cliente-aplicacion` | Listar todos los contratos |
| GET | `/api/aplicaciones/cliente-aplicacion/{id}` | Obtener contrato por ID |
| GET | `/api/aplicaciones/cliente-aplicacion/cliente/{idCliente}` | Contratos de un cliente |
| GET | `/api/aplicaciones/cliente-aplicacion/aplicacion/{idAplicacion}` | Contratos de una aplicación |
| POST | `/api/aplicaciones/cliente-aplicacion` | Crear contrato |
| PUT | `/api/aplicaciones/cliente-aplicacion/{id}` | Actualizar contrato |
| DELETE | `/api/aplicaciones/cliente-aplicacion/{id}` | Eliminar contrato |

---

### 📂 Menús  `/api/menus/menus`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/menus/menus` | Listar todos los menús |
| GET | `/api/menus/menus/{id}` | Obtener menú por ID |
| GET | `/api/menus/menus/aplicacion/{idAplicacion}` | Menús de una aplicación |
| GET | `/api/menus/menus/padre/{idMenuPadre}` | Submenús de un menú padre |
| GET | `/api/menus/menus/nivel/{nivelMenu}` | Menús por nivel (1 o 2) |
| POST | `/api/menus/menus` | Crear menú |
| PUT | `/api/menus/menus/{id}` | Actualizar menú |
| DELETE | `/api/menus/menus/{id}` | Eliminar menú |

---

## 🧪 Ejemplo completo de servicio Angular

### `cliente.service.ts`
```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente.model';

@Injectable({ providedIn: 'root' })
export class ClienteService {

  private readonly API = 'http://localhost:8080/api/clientes/clientes';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.API);
  }

  getById(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.API}/${id}`);
  }

  create(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(this.API, cliente);
  }

  update(id: number, cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.API}/${id}`, cliente);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}
```

> El token JWT **se agrega automáticamente** en cada petición gracias al `AuthInterceptor`. No es necesario agregarlo manualmente en cada servicio.

---

## ⚠️ Códigos de Respuesta HTTP

| Código | Significado |
|--------|-------------|
| 200 | OK — operación exitosa |
| 201 | Created — recurso creado |
| 204 | No Content — eliminado correctamente |
| 401 | Unauthorized — token inválido, expirado o no enviado |
| 404 | Not Found — recurso no encontrado |
| 503 | Service Unavailable — microservicio caído |

---

## 📌 Notas Importantes

- El token JWT expira en **24 horas**. Cuando recibas un `401`, redirigir al login.
- El campo `password` del modelo `Usuario` **nunca** debe mostrarse en la UI. Solo enviarlo al crear o actualizar.
- Las fechas vienen en formato **ISO 8601**: `"2024-05-06T10:30:00"`.
- Los IDs de `Aplicacion` y `Menu` son **strings** (ej: `"APP001"`, `"MNU001"`), no números.
- Los IDs de `Persona`, `Usuario`, `Cliente`, `ClienteAplicacion` y `AccesoMenu` son **números enteros**.